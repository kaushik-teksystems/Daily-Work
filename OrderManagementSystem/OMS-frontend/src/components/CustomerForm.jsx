import React from "react";

function CustomerForm({ customer, setCustomer }) {
  return (
    <>
      <input
        className="input"
        placeholder="Customer Name"
        value={customer.name}
        onChange={(event) => setCustomer({ ...customer, name: event.target.value })}
      />
      <input
        className="input"
        placeholder="Address"
        value={customer.address}
        onChange={(event) => setCustomer({ ...customer, address: event.target.value })}
      />
    </>
  );
}

export default CustomerForm;