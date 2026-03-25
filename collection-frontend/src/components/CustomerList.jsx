import "../styles/css/CustomerList.css";
import { toast } from "react-toastify";
import { FaEdit, FaTrash } from "react-icons/fa";
import API from "../api/axios";

function CustomerList({ customers = [], refresh, setEditCustomer }) {

  // 🔥 DELETE CUSTOMER
  const deleteCustomer = async (id) => {
    if (!window.confirm("Are you sure?")) return;

    try {
      await API.delete(`/customers/${id}`);
      toast.success("Deleted ✅");
      refresh();
    } catch (error) {
      console.error("Delete error:", error);
      toast.error("Delete failed ❌");
    }
  };

  // 🔥 DEBUG (VERY IMPORTANT)
  console.log("CUSTOMERS DATA:", customers);

  return (
    <div className="customer-table-container">

      <h2>Customer List</h2>

      <table className="customer-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {/* 🔥 SAFE CHECK */}
          {!customers || customers.length === 0 ? (
            <tr>
              <td colSpan="6" style={{ textAlign: "center" }}>
                No customers found
              </td>
            </tr>
          ) : (
            customers.map((c, index) => (
              <tr key={c.customerId || index}>
                <td>{c.customerId}</td>
                <td>{c.name}</td>
                <td>{c.email}</td>
                <td>{c.phone}</td>
                <td>{c.address}</td>

                <td>
                  <div className="action-icons">

                    <FaEdit
                      className="icon edit-icon"
                      onClick={() => setEditCustomer(c)}
                      title="Edit"
                    />

                    <FaTrash
                      className="icon delete-icon"
                      onClick={() => deleteCustomer(c.customerId)}
                      title="Delete"
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

export default CustomerList;