import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate, Link, useLocation } from "react-router-dom";
import OrderForm from "./components/OrderForm";
import OrderHistory from "./components/OrderHistory";

function NavBar() {
  const location = useLocation();

  return (
    <nav className="navbar">
      <Link
        to="/create-order"
        className={location.pathname === "/create-order" ? "nav-link active" : "nav-link"}
      >
        Create Order
      </Link>
      <Link
        to="/order-history"
        className={location.pathname === "/order-history" ? "nav-link active" : "nav-link"}
      >
        Order History
      </Link>
    </nav>
  );
}

function App() {
  return (
    <Router>
      <NavBar />
      <Routes>
        <Route path="/" element={<Navigate to="/create-order" />} />
        <Route path="/create-order" element={<OrderForm />} />
        <Route path="/order-history" element={<OrderHistory />} />
      </Routes>
    </Router>
  );
}

export default App;