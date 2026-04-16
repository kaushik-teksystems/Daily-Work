package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderLine;
import com.example.demo.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private OrderService orderService;

	private Order testOrder;
	private OrderLine testOrderLine;

	@BeforeEach
	void setup() {
		testOrderLine = new OrderLine();
		testOrderLine.setId(1L);
		testOrderLine.setProduct("TWS");
		testOrderLine.setPrice(new BigDecimal(999.99));
		testOrderLine.setQuantity(1);

		testOrder = new Order();
		testOrder.setId(1);
		testOrder.setCustomerName("Kaushik");
		testOrder.setAddress("5th B Cross");
		testOrder.setOrderLines(Arrays.asList(testOrderLine));
		testOrderLine.setOrder(testOrder);
	}

	@Test
	void testCreateOrder_Success() throws Exception {
		when(orderService.addOrder(any(Order.class))).thenReturn(1);
		mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(testOrder))).andExpect(status().isCreated())
				.andExpect(content().string("1"));
		verify(orderService, times(1)).addOrder(any(Order.class));
	}

	@Test
	void testCreateOrder_Invalid_whenProductNameIsMissing() throws Exception {
		testOrderLine.setProduct(null);
		testOrder.setOrderLines(Arrays.asList(testOrderLine));

		mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(testOrder))).andExpect(status().isBadRequest());

		verify(orderService, never()).addOrder(any(Order.class));
	}

	@Test
	void testCreateOrder_Invalid_WhenPriceIsNegative() throws Exception {
		testOrderLine.setPrice(new BigDecimal(-10.0));
		testOrder.setOrderLines(Arrays.asList(testOrderLine));

		mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(testOrder)))

				.andExpect(status().isBadRequest());

		verify(orderService, never()).addOrder(any(Order.class));
	}

	@Test
	void testCreateOrder_Invalid_whenAddressIsInvalid() throws Exception {
		testOrder.setAddress(null);

		mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(testOrder)))

				.andExpect(status().isBadRequest());

		verify(orderService, never()).addOrder(any(Order.class));

	}

	@Test
	void testCreateOrder_Invalid_NoOrderLines() throws Exception {
		testOrder.setOrderLines(null);

		mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(testOrder))).andExpect(status().isBadRequest());

		verify(orderService, never()).addOrder(any(Order.class));
	}

	@Test
	void testCreateOrder_Invalid_WhenQuantityIsZero() throws Exception {
		testOrderLine.setQuantity(0);
		testOrder.setOrderLines(Arrays.asList(testOrderLine));

		mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(testOrder))).andExpect(status().isBadRequest());
		verify(orderService, never()).addOrder(any(Order.class));

	}

	@Test
	void testGetOrders_Success() throws Exception {
		List<Order> orders = Arrays.asList(testOrder);
		when(orderService.getOrder()).thenReturn(orders);

		mockMvc.perform(get("/order").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1)).andExpect(jsonPath("$[0].customerName").value("Kaushik"));

		verify(orderService, times(1)).getOrder();
	}

	@Test
	void testGetOrders_Empty() throws Exception {
		when(orderService.getOrder()).thenReturn(Arrays.asList());

		mockMvc.perform(get("/order").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").isEmpty());

		verify(orderService, times(1)).getOrder();
	}

	@Test
	void testGetOrderById_Success() throws Exception {
		when(orderService.getOrderById(1)).thenReturn(Optional.of(testOrder));

		mockMvc.perform(get("/order/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.customerName").value("Kaushik"))
				.andExpect(jsonPath("$.address").value("5th B Cross"))
				.andExpect(jsonPath("$.orderLines[0].product").value("TWS"))
				.andExpect(jsonPath("$.orderLines[0].price").value(999.99))
				.andExpect(jsonPath("$.orderLines[0].quantity").value(1));

		verify(orderService, times(1)).getOrderById(1);
	}

	@Test
	void testGetOrderById_NotFound() throws Exception {
		when(orderService.getOrderById(999)).thenReturn(Optional.empty());

		mockMvc.perform(get("/order/999").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

		verify(orderService, times(1)).getOrderById(999);
	}

	@Test
	void testDeleteOrder_Success() throws Exception {
		doNothing().when(orderService).deleteOrder(1);
		mockMvc.perform(delete("/order/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

		verify(orderService, times(1)).deleteOrder(1);
	}

	@Test
	void testDeleteOrder_Invalid_whenNonExistentOrderIdIsPassed() throws Exception {
		doThrow(new IllegalArgumentException("Order NOT Found")).when(orderService).deleteOrder(8889);

		mockMvc.perform(delete("/order/8889").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(content().string("Order NOT Found"));

		verify(orderService, times(1)).deleteOrder(8889);
	}

}
