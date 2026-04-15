import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080",
});

export const createOrder = (data) => API.post("/order", data);