package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderLine;
import com.example.demo.repository.OrderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private Order createValidOrder() {
        Order order = new Order();
        order.setCustomerName("Kaushik");
        order.setAddress("123 Street");

        OrderLine line = new OrderLine();
        line.setProduct("Laptop");
        line.setPrice(new BigDecimal("50000"));
        line.setQuantity(2);

        order.setOrderLines(List.of(line));
        return order;
    }

    @Test
    void testAddOrder_Success() {
        Order order = createValidOrder();

        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order o = invocation.getArgument(0);
            o.setId(1);
            return o;
        });

        Integer id = orderService.addOrder(order);

        assertEquals(1, id);
        assertEquals("CREATED", order.getStatus());
        assertNotNull(order.getCreatedAt());
        assertEquals(order, order.getOrderLines().get(0).getOrder());

        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testAddOrder_EmptyOrderLines() {
        Order order = createValidOrder();
        order.setOrderLines(new ArrayList<>()); 

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.addOrder(order);
        });
        assertTrue(exception.getMessage().contains("at least one item"));
        verify(orderRepository, never()).save(any());
    }

    @Test
    void testAddOrder_NullOrderLines() {
        Order order = createValidOrder();
        order.setOrderLines(null); 

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.addOrder(order);
        });
        assertTrue(exception.getMessage().contains("at least one item"));
        verify(orderRepository, never()).save(any());
    }

    @Test
    void testGetOrderById_Success() {
        Order order = createValidOrder();
        order.setId(1);

        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        Optional<Order> result = orderService.getOrderById(1);
        assertTrue(result.isPresent());
        assertEquals("Kaushik", result.get().getCustomerName());

        verify(orderRepository, times(1)).findById(1);
    }

    @Test
    void testGetOrderById_NotFound() {
        when(orderRepository.findById(999)).thenReturn(Optional.empty());

        Optional<Order> result = orderService.getOrderById(999);
        assertFalse(result.isPresent());
        verify(orderRepository, times(1)).findById(999);
    }

    @Test
    void testGetAllOrders() {
        List<Order> orders = List.of(createValidOrder());
        when(orderRepository.findAll()).thenReturn(orders);

        Iterable<Order> result = orderService.getOrder();
        assertNotNull(result);
        assertEquals(1, ((List<Order>) result).size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testDeleteOrder() {
        doNothing().when(orderRepository).deleteById(1);

        orderService.deleteOrder(1);

        verify(orderRepository, times(1)).deleteById(1);
    }
}