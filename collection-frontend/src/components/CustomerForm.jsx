import { useState, useEffect } from "react";
import "../styles/css/CustomerForm.css";
import { toast } from "react-toastify";
import API from "../api/axios";

function CustomerForm({ refresh, editCustomer, setEditCustomer }) {

  const [form, setForm] = useState({
    name: "",
    email: "",
    phone: "",
    address: ""
  });

  const [errors, setErrors] = useState({});

  useEffect(() => {
    if (editCustomer) {
      setForm(editCustomer);
    }
  }, [editCustomer]);

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });

    setErrors({
      ...errors,
      [e.target.name]: ""
    });
  };

  // ✅ USE onSubmit
  const handleSubmit = async (e) => {
    e.preventDefault(); // 🔥 VERY IMPORTANT

    try {

      if (editCustomer) {
        await API.put(`/customers/${editCustomer.customerId}`, form);
        toast.success("Updated ✅");
      } else {
        await API.post("/customers", form);
        toast.success("Added ✅");
      }

      setForm({
        name: "",
        email: "",
        phone: "",
        address: ""
      });

      setEditCustomer(null);
      refresh();

    } catch (error) {
      console.error(error);

      if (error.response?.data) {
        setErrors(error.response.data);
      } else {
        toast.error("Something went wrong ❌");
      }
    }
  };

  return (
    <div className="customer-form-container">

      <div className="form-card">
        <h2>{editCustomer ? "Edit Customer" : "Add Customer"}</h2>

        {/* 🔥 FORM START */}
        <form onSubmit={handleSubmit}>

          <div className="form-group">
            <label>Name</label>
            <input
              name="name"
              value={form.name}
              onChange={handleChange}
              className={errors.name ? "input-error" : ""}
            />
            {errors.name && <p className="error">{errors.name}</p>}
          </div>

          <div className="form-group">
            <label>Email</label>
            <input
              name="email"
              value={form.email}
              onChange={handleChange}
              className={errors.email ? "input-error" : ""}
            />
            {errors.email && <p className="error">{errors.email}</p>}
          </div>

          <div className="form-group">
            <label>Phone</label>
            <input
              name="phone"
              value={form.phone}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>Address</label>
            <input
              name="address"
              value={form.address}
              onChange={handleChange}
            />
          </div>

          {/* 🔥 BUTTON */}
          <button type="submit" className="submit-btn">
            {editCustomer ? "Update Customer" : "Add Customer"}
          </button>

        </form>
        {/* 🔥 FORM END */}

      </div>

    </div>
  );
}

export default CustomerForm;