import { useState, useEffect } from "react";
import "../styles/css/CustomerForm.css";
import { toast } from "react-toastify";
import API from "../api/axios"; // ✅ ADD

function CollectorForm({ refresh, editCollector, setEditCollector }) {

  const [form, setForm] = useState({
    name: "",
    email: "",
    phone: "",
    region: "",
    status: "ACTIVE"
  });

  const [errors, setErrors] = useState({});

  useEffect(() => {
    if (editCollector) {
      setForm(editCollector);
    }
  }, [editCollector]);

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

  const handleSubmit = async (e) => {
    e.preventDefault(); // ✅ IMPORTANT

    try {

      if (editCollector) {
        await API.put(`/api/collectors/${editCollector.collectorId}`, form);
        toast.success("Updated ✅");
      } else {
        await API.post("/api/collectors", form);
        toast.success("Added ✅");
      }

      setForm({
        name: "",
        email: "",
        phone: "",
        region: "",
        status: "ACTIVE"
      });

      setEditCollector(null);
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
        <h2>{editCollector ? "Edit Collector" : "Add Collector"}</h2>

        <form onSubmit={handleSubmit}>

          <div className="form-group">
            <label>Name</label>
            <input
              name="name"
              value={form.name}
              onChange={handleChange}
              className={errors.name ? "input-error" : ""}
            />
          </div>

          <div className="form-group">
            <label>Email</label>
            <input
              name="email"
              value={form.email}
              onChange={handleChange}
              className={errors.email ? "input-error" : ""}
            />
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
            <label>City</label>
            <input
              name="region"
              value={form.region}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>Status</label>
            <select name="status" value={form.status} onChange={handleChange}>
              <option value="ACTIVE">ACTIVE</option>
              <option value="INACTIVE">INACTIVE</option>
            </select>
          </div>

          <button className="submit-btn" type="submit">
            {editCollector ? "Update Collector" : "Add Collector"}
          </button>

        </form>

      </div>
    </div>
  );
}

export default CollectorForm;