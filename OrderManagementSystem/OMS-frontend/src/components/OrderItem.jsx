import React from "react";

function OrderItem({ item, index, onDelete }) {
  return (
    <div className="item-card">

      <div className="item-info">
        <span className="item-name">{item.product}</span>
        <span className="item-meta">
          ₹ {item.price} × {item.quantity}
        </span>
      </div>

      <div className="item-price">
        ₹ {item.price * item.quantity}
      </div>

      <button className="delete" onClick={() => onDelete(index)}>
        ✕
      </button>

    </div>
  );
}

export default OrderItem;