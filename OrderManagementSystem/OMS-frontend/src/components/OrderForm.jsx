import React, { useState, useEffect } from "react";
import { createOrder, getOrders } from "../services/api";
import CustomerForm from "./CustomerForm";
import OrderList from "./OrderList";
import "../App.css";
import Modal from "./Modal"

function OrderForm() {
    const [customer, setCustomer] = useState({ name: "", address: "" });
    const [product, setProduct] = useState("");
    const [quantity, setQuantity] = useState(1);
    const [price, setPrice] = useState("");
    const [items, setItems] = useState([]);
    const [modalMessage, setModalMessage] = useState("");
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        fetchOrders();
    }, []);

    const fetchOrders = async () => {
        try {
            const response = await getOrders();
            setOrders(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    const handleAddItem = () => {
        if (!product || !price || quantity <= 0) {
            setModalMessage("Please add details to all fields.")
            return;
        }

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
        if (!customer.name || !customer.address || items.length === 0) {
            setModalMessage("Please fill all details");
            return;
        }

        const payload = {
            customerName: customer.name,
            address: customer.address,
            orderLines: items
        };

        try {
            await createOrder(payload);
            setModalMessage("Order placed successfully");

            setItems([]);
            setCustomer({ name: "", address: "" });
            fetchOrders();
        } catch (error) {
            console.error(error);
            setModalMessage("Error placing order");
        }
    };

    return (
        <div className="main">

            <div className="left">
                <h2>Customer Details</h2>
                <CustomerForm customer={customer} setCustomer={setCustomer} />

                <h2>Add Product</h2>
                <input
                    className="input"
                    placeholder="Product"
                    value={product}
                    onChange={(event) => setProduct(event.target.value)}
                />
                <input
                    type="number"
                    className="input"
                    placeholder="Quantity"
                    value={quantity}
                    onChange={(event) => setQuantity(event.target.value)}
                />
                <input
                    type="number"
                    className="input"
                    placeholder="Price"
                    value={price}
                    onChange={(event) => setPrice(event.target.value)}
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