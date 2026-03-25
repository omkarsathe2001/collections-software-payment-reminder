import { useEffect, useState } from "react";
import "../styles/css/CollectorDashboard.css";
import API from "../api/axios";
import { toast } from "react-toastify";

function CollectorDashboard() {

  const [data, setData] = useState([]);
  const [collectors, setCollectors] = useState([]);
  const [selectedCollector, setSelectedCollector] = useState({});
  const [loading, setLoading] = useState(true);

  const fetchData = async () => {
    try {
      const res = await API.get("/delinquents");
      setData(res.data);
    } catch (error) {
      toast.error("Failed to load data ❌");
    }
  };

  const fetchCollectors = async () => {
    try {
      const res = await API.get("/api/collectors");
      setCollectors(res.data);
    } catch (error) {
      toast.error("Failed to load collectors ❌");
    }
  };

  useEffect(() => {
    const load = async () => {
      setLoading(true);
      await Promise.all([fetchData(), fetchCollectors()]);
      setLoading(false);
    };
    load();
  }, []);

  const assignCollector = async (id) => {
    const collectorId = selectedCollector[id];

    if (!collectorId) {
      toast.error("Select collector ❗");
      return;
    }

    try {
      await API.put(`/delinquents/assign/${id}/${collectorId}`);
      toast.success("Assigned ✅");
      fetchData();
    } catch {
      toast.error("Assign failed ❌");
    }
  };

  const removeCollector = async (id) => {
    try {
      await API.put(`/delinquents/remove/${id}`);
      toast.success("Removed ❌");
      fetchData();
    } catch {
      toast.error("Remove failed ❌");
    }
  };

  const autoAssignCollector = async () => {
    if (!collectors.length) {
      toast.error("No collectors ❗");
      return;
    }

    try {
      for (let i = 0; i < data.length; i++) {
        const c = collectors[i % collectors.length];
        await API.put(`/delinquents/assign/${data[i].delinquentId}/${c.collectorId}`);
      }
      toast.success("Auto Assigned 🤖");
      fetchData();
    } catch {
      toast.error("Auto assign failed ❌");
    }
  };

  const getDPDClass = (dpd) => {
    if (dpd <= 30) return "dpd-green";
    if (dpd <= 60) return "dpd-yellow";
    return "dpd-red";
  };

  if (loading) return <h2 className="loading">Loading...</h2>;

  return (
    <div className="collector-container">

      <div className="header">
        <h1>👨‍💼 Collector Dashboard</h1>
        <button className="auto-btn" onClick={autoAssignCollector}>
          🤖 Auto Assign
        </button>
      </div>

      <table className="collector-table">
        <thead>
          <tr>
            <th>Account</th>
            <th>Customer</th>
            <th>DPD</th>
            <th>Status</th>
            <th>Collector</th>
            <th>Select</th>
            <th>Assign</th>
            <th>Remove</th>
          </tr>
        </thead>

        <tbody>
          {data.length === 0 ? (
            <tr>
              <td colSpan="8">No Data Found ❗</td>
            </tr>
          ) : (
            data.map((item) => (
              <tr key={item.delinquentId}>

                <td>{item.accountNumber}</td>
                <td>{item.customerName}</td>

                <td className={getDPDClass(item.daysPastDue)}>
                  {item.daysPastDue}
                </td>

                {/* 🔥 STATUS FIXED */}
                <td>
                  <span className={`status-badge ${item.status?.toLowerCase()}`}>
                    {item.status}
                  </span>
                </td>

                <td>{item.collectorName || "Not Assigned"}</td>

                {/* SELECT */}
                <td>
                  <select
                    value={selectedCollector[item.delinquentId] || ""}
                    onChange={(e) =>
                      setSelectedCollector({
                        ...selectedCollector,
                        [item.delinquentId]: e.target.value
                      })
                    }
                  >
                    <option value="">Select</option>
                    {collectors.map((c) => (
                      <option key={c.collectorId} value={c.collectorId}>
                        {c.name}
                      </option>
                    ))}
                  </select>
                </td>

                {/* ASSIGN */}
                <td>
                  <button
                    className="assign-btn"
                    onClick={() => assignCollector(item.delinquentId)}
                  >
                    Assign
                  </button>
                </td>

                {/* REMOVE */}
                <td>
                  <button
                    className="remove-btn"
                    onClick={() => removeCollector(item.delinquentId)}
                  >
                    Remove
                  </button>
                </td>

              </tr>
            ))
          )}
        </tbody>
      </table>

    </div>
  );
}

export default CollectorDashboard;