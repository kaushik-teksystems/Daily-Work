import React, { useState } from "react";
import { createOrder } from "../services/api";
import OrderList from "./OrderList";
import "../App.css";
import Modal from "./Modal"

function OrderForm() {
    const [customerName, setCustomerName] = useState("");
    const [product, setProduct] = useState("");
    const [quantity, setQuantity] = useState("");
    const [price, setPrice] = useState("");
    const [items, setItems] = useState([]);
    const [modalMessage, setModalMessage] = useState("");

    const handleAddItem = () => {
        if (!product || !price || quantity <= 0) return;

        setItems([
            ...items,
            { product, quantity: Number(quantity), price: Number(price) },
        ]);

        setProduct("");
        setQuantity(1);
        setPrice("");
    };

    const handleDelete = (index) => {
        setItems(items.filter((_, i) => i !== index));
    };

    const handleSubmit = async () => {
        if (!customerName || items.length === 0){
            setModalMessage("Please fill all details");
            return;
        }

        const payload = {
            customerName,
            orderLines: items.map(({ product, quantity, price }) => ({
                product,
                quantity,
                price,
            })),
        };

        try {
            await createOrder(payload);
            setModalMessage("Order placed successfully");

            setItems([]);
            setCustomerName("");
        } catch (err) {
            console.error(err);
            setModalMessage("Error placing order");
        }
    };

    return (
        <div className="main">

            <div className="left">
                <h2>Create Order</h2>

                <input
                    className="input"
                    placeholder="Customer Name"
                    value={customerName}
                    onChange={(e) => setCustomerName(e.target.value)}
                />

                <input
                    className="input"
                    placeholder="Product"
                    value={product}
                    onChange={(e) => setProduct(e.target.value)}
                />

                <input
                    type="number"
                    className="input"
                    placeholder="Quantity"
                    value={quantity}
                    onChange={(e) => setQuantity(e.target.value)}
                />

                <input
                    type="number"
                    className="input"
                    placeholder="Price"
                    value={price}
                    onChange={(e) => setPrice(e.target.value)}
                />

                <button className="btn add" onClick={handleAddItem}>
                    + Add Item
                </button>
            </div>

            <div className="right">
                <h2>Order Summary</h2>

                <OrderList
                    items={items}
                    onDelete={handleDelete}
                    onSubmit={handleSubmit}
                />
            </div>
            <Modal
                message={modalMessage}
                onClose={() => setModalMessage("")}
            />
        </div>
    );
}

export default OrderForm;