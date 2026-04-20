import React, { useEffect, useState } from "react";
import { getOrders } from "../services/api";
import "../App.css";

function OrderHistory() {
  const [orders, setOrders] = useState([]);
  const [selectedOrder, setSelectedOrder] = useState(null);

  useEffect(() => {
    fetchOrders();
  }, []);

  const fetchOrders = async () => {
    try {
      const response = await getOrders();
      setOrders(response.data);
    } catch (error) {
      console.error("Error fetching orders:", error);
    }
  };

  if (!orders || orders.length === 0) {
    return <p>No orders yet</p>;
  }

  const sortedOrders = [...orders].sort(
    (a, b) => new Date(b.createdAt) - new Date(a.createdAt)
  );

  const mostRecentId = sortedOrders[0]?.id;

  return (
    <>
      <div className="table-container">
        <table className="table">
          <thead>
            <tr>
              <th>Order ID</th>
              <th>Customer</th>
              <th>Address</th>
              <th>Items</th>
              <th>Total</th>
              <th>Status</th>
              <th>Created At</th>
            </tr>
          </thead>

          <tbody>
            {sortedOrders.map(order => (
              <tr
                key={order.id}
                className={order.id === mostRecentId ? "highlight" : ""}
              >
                <td>{order.id}</td>
                <td>{order.customerName}</td>
                <td>{order.address}</td>
                <td
                  className="clickable"
                  onClick={() => setSelectedOrder(order)}
                >
                  {order.orderLines?.length || 0} items
                </td>
                <td>
                  ₹{" "}
                  {order.orderLines?.reduce(
                    (sum, item) => sum + item.price * item.quantity,
                    0
                  ) || 0}
                </td>
                <td>{order.status}</td>
                <td>
                  {order.createdAt
                    ? new Date(order.createdAt).toLocaleString()
                    : "-"}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {selectedOrder && (
        <div className="modal-overlay">
          <div className="modal-box large">
            <h3>Order #{selectedOrder.id} Items</h3>

            <table className="table">
              <thead>
                <tr>
                  <th>Product</th>
                  <th>Quantity</th>
                  <th>Price</th>
                  <th>Subtotal</th>
                </tr>
              </thead>

              <tbody>
                {selectedOrder.orderLines?.map((item, index) => (
                  <tr key={index}>
                    <td>{item.product}</td>
                    <td>{item.quantity}</td>
                    <td>₹ {item.price}</td>
                    <td>₹ {item.price * item.quantity}</td>
                  </tr>
                ))}
              </tbody>
            </table>

            <button onClick={() => setSelectedOrder(null)}>Close</button>
          </div>
        </div>
      )}
    </>
  );
}

export default OrderHistory;