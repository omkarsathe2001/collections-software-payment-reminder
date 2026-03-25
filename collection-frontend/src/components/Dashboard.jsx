import "../styles/css/Dashboard.css";
import { useEffect, useState } from "react";
import DPDChart from "./DPDChart";
import CollectorPerformanceChart from "./CollectorPerformanceChart";
import API from "../api/axios"; // ✅ IMPORTANT

function Dashboard() {

  const [stats, setStats] = useState({
    customers: 0,
    accounts: 0,
    overdue: 0,
    collectors: 0,
    dpd30: 0,
    dpd60: 0,
    dpd90: 0,
    collectorPerformance: []
  });

  const [loading, setLoading] = useState(true);

  // 🔥 FETCH DASHBOARD DATA
  useEffect(() => {
    const fetchDashboard = async () => {
      try {
        setLoading(true);

        const res = await API.get("/api/dashboard"); // ✅ TOKEN AUTO

        console.log("DASHBOARD DATA:", res.data);

        setStats(res.data);

      } catch (error) {
        console.error("Dashboard error:", error);

        // 🔥 HANDLE TOKEN EXPIRE
        if (error.response?.status === 401 || error.response?.status === 403) {
          alert("Session expired ❗ Please login again");
          localStorage.clear();
          window.location.reload();
        }

      } finally {
        setLoading(false);
      }
    };

    fetchDashboard();
  }, []);

  const dpdData = [
    { name: "0-30", value: stats.dpd30 },
    { name: "31-60", value: stats.dpd60 },
    { name: "60+", value: stats.dpd90 }
  ];

  if (loading) return <h2 className="loading">Loading Dashboard...</h2>;

  return (
    <div className="dashboard-container">

      <h1 className="dashboard-title">📊 Collection Dashboard</h1>

      {/* 🔥 CARDS */}
      <div className="card-grid">

        <div className="card blue">
          <h3>Customers</h3>
          <p>{stats.customers}</p>
        </div>

        <div className="card green">
          <h3>Accounts</h3>
          <p>{stats.accounts}</p>
        </div>

        <div className="card red">
          <h3>Overdue</h3>
          <p>{stats.overdue}</p>
        </div>

        <div className="card purple">
          <h3>Collectors</h3>
          <p>{stats.collectors}</p>
        </div>

      </div>

      {/* 📊 DPD CHART */}
      <div className="chart-section">
        <h2>DPD Distribution</h2>
        <DPDChart data={dpdData} />
      </div>

      {/* 🔥 COLLECTOR PERFORMANCE */}
      <div className="chart-section">
        <h2>Collector Performance</h2>
        <CollectorPerformanceChart data={stats.collectorPerformance} />
      </div>

    </div>
  );
}

export default Dashboard;