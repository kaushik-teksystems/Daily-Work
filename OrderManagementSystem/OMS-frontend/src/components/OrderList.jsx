import React from "react";
import OrderItem from "./OrderItem";

function OrderList({ items, onDelete, onSubmit }) {

  const total = items.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0
  );

  return (
    <>
      <div className="item-list">
        {items.length === 0 ? (
          <p style={{ color: "#6b7280" }}>No items added yet</p>
        ) : (
          items.map((item, index) => (
            <OrderItem
              key={index}
              item={item}
              index={index}
              onDelete={onDelete}
            />
          ))
        )}
      </div>

      <div className="summary">
        <div className="total">
          <span>Total</span>
          <span>₹ {total}</span>
        </div>

        <button
          className="btn buy"
          onClick={onSubmit}
        >
          Buy Now
        </button>
      </div>
    </>
  );
}

export default OrderList;