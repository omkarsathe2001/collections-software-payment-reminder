import { useState, useEffect } from "react";
import "../styles/css/AccountForm.css";
import { toast } from "react-toastify";
import API from "../api/axios";

function AccountForm({ refresh, editAccount, setEditAccount, closeForm }) {

  const [loading, setLoading] = useState(false);
  const [customers, setCustomers] = useState([]);

  const [form, setForm] = useState({
    accountNumber: "",
    loanAmount: "",
    emiAmount: "",
    dueDate: "",
    status: "ACTIVE",
    customerId: ""
  });

  // 🔥 Fetch customers
  useEffect(() => {
    API.get("/customers")
      .then(res => setCustomers(res.data))
      .catch(err => console.error(err));
  }, []);

  // 🔥 Set edit data
  useEffect(() => {
    if (editAccount) {
      setForm({
        ...editAccount,
        customerId: editAccount.customerId || editAccount.customer?.customerId || ""
      });
    }
  }, [editAccount]);

  // 🔥 Handle input change
  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  // 🔥 Submit
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      setLoading(true);

      if (editAccount) {
        await API.put(`/accounts/${editAccount.accountId}`, form);
        toast.success("Updated ✅");
      } else {
        await API.post("/accounts", form);
        toast.success("Created ✅");
      }

      refresh();

      // ✅ Close form after success
      closeForm();

    } catch (error) {
      console.error(error);
      toast.error("Something went wrong ❌");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="account-form-container">
      <div className="account-form">

        <h2>{editAccount ? "Edit Account" : "Create Account"}</h2>

        <form onSubmit={handleSubmit}>

          <div className="form-group">
            <label>Account Number</label>
            <input
              name="accountNumber"
              value={form.accountNumber}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>Loan Amount</label>
            <input
              name="loanAmount"
              value={form.loanAmount}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>EMI Amount</label>
            <input
              name="emiAmount"
              value={form.emiAmount}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>Due Date</label>
            <input
              type="date"
              name="dueDate"
              value={form.dueDate}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>Customer</label>
            <select
              name="customerId"
              value={form.customerId}
              onChange={handleChange}
              required
            >
              <option value="">Select Customer</option>
              {customers.map(c => (
                <option key={c.customerId} value={c.customerId}>
                  {c.name}
                </option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label>Status</label>
            <select
              name="status"
              value={form.status}
              onChange={handleChange}
            >
              <option value="ACTIVE">ACTIVE</option>
              <option value="OVERDUE">OVERDUE</option>
            </select>
          </div>

          <button type="submit" className="submit-btn" disabled={loading}>
            {loading
              ? "Processing..."
              : editAccount
              ? "Update Account"
              : "Create Account"}
          </button>

        </form>

        {/* 🔥 BACK BUTTON (FIXED) */}
     

      </div>
    </div>
  );
}

export default AccountForm;