import { useEffect, useState } from "react";
import "../styles/css/AccountList.css";
import { FaEdit, FaTrash } from "react-icons/fa";
import { toast } from "react-toastify";
import API from "../api/axios";

function AccountList({ accounts = [], refresh, setEditAccount }) {

  const [customers, setCustomers] = useState([]);

  // 🔥 Fetch customers (for mapping name)
  useEffect(() => {
    API.get("/customers")
      .then(res => setCustomers(res.data))
      .catch(err => console.error(err));
  }, []);

  // 🔥 Get customer name using customerId
  const getCustomerName = (id) => {
    const customer = customers.find(c => c.customerId === id);
    return customer ? customer.name : "N/A";
  };

  // 🔥 Delete account
  const deleteAccount = async (id) => {
    if (!window.confirm("Are you sure?")) return;

    try {
      await API.delete(`/accounts/${id}`);
      toast.success("Deleted ✅");
      refresh();
    } catch (error) {
      console.error(error);
      toast.error("Delete failed ❌");
    }
  };

  return (
    <div className="account-table-container">

      <h2>Account List</h2>

      <table className="account-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Account No</th>
            <th>Customer Name</th>
            <th>Loan</th>
            <th>EMI</th>
            <th>Due Date</th>
            <th>Status</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {accounts.length === 0 ? (
            <tr>
              <td colSpan="7">No accounts found</td>
            </tr>
          ) : (
            accounts.map(acc => (
              <tr key={acc.accountId}>

                <td>{acc.accountId}</td> 
                {/* ✅ FIXED ORDER */}
                <td>{acc.accountNumber}</td>
                <td>{getCustomerName(acc.customerId)}</td>

                <td>{acc.loanAmount}</td>
                <td>{acc.emiAmount}</td>
                <td>{acc.dueDate}</td>

                <td>
                  <span className={`status ${acc.status?.toLowerCase()}`}>
                    {acc.status}
                  </span>
                </td>

                <td>
                  <div className="action-icons">

                    <FaEdit
                      className="icon edit-icon"
                      onClick={() => setEditAccount(acc)}
                    />

                    <FaTrash
                      className="icon delete-icon"
                      onClick={() => deleteAccount(acc.accountId)}
                    />

                  </div>
                </td>

              </tr>
            ))
          )}
        </tbody>
      </table>

    </div>
  );
}

export default AccountList;