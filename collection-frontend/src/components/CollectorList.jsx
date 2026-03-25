import "../styles/css/CustomerList.css";
import { toast } from "react-toastify";
import { FaEdit, FaTrash } from "react-icons/fa";
import API from "../api/axios"; // ✅ ADD

function CollectorList({ collectors = [], refresh, setEditCollector }) {

  const deleteCollector = async (id) => {

    if (!window.confirm("Are you sure?")) return;

    try {
      await API.delete(`/api/collectors/${id}`);
      toast.success("Deleted ✅");
      refresh();

    } catch (error) {
      console.error(error);
      toast.error("Delete failed ❌");
    }
  };

  return (
    <div className="customer-table-container">

      <h2>Collector List</h2>

      <table className="customer-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>City</th>
            <th>Status</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {collectors.length === 0 ? (
            <tr>
              <td colSpan="7">No Collectors Found ❗</td>
            </tr>
          ) : (
            collectors.map((c) => (
              <tr key={c.collectorId}>
                <td>{c.collectorId}</td>
                <td>{c.name}</td>
                <td>{c.email}</td>
                <td>{c.phone}</td>
                <td>{c.region}</td>
                <td>{c.status}</td>

                <td>
                  <div className="action-icons">

                    <FaEdit
                      className="icon edit-icon"
                      onClick={() => setEditCollector(c)}
                    />

                    <FaTrash
                      className="icon delete-icon"
                      onClick={() => deleteCollector(c.collectorId)}
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

export default CollectorList;