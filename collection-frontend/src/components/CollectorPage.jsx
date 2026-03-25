import { useEffect, useState } from "react";
import CollectorForm from "./CollectorForm";
import CollectorList from "./CollectorList";
import "../styles/css/CollectorPage.css";
import API from "../api/axios";

function CollectorPage() {

  const [collectors, setCollectors] = useState([]);
  const [editCollector, setEditCollector] = useState(null);
  const [showForm, setShowForm] = useState(false); // 🔥 NEW

  const fetchCollectors = async () => {
    try {
      const res = await API.get("/api/collectors");
      setCollectors(res.data);
    } catch (error) {
      console.error("Error fetching collectors:", error);

      if (error.response?.status === 401 || error.response?.status === 403) {
        alert("Session expired ❗");
        localStorage.clear();
        window.location.reload();
      }
    }
  };

  useEffect(() => {
    fetchCollectors();
  }, []);

  // 🔥 HANDLE EDIT
  const handleEdit = (collector) => {
    setEditCollector(collector);
    setShowForm(true); // open form
  };

  // 🔥 TOGGLE FORM
  const toggleForm = () => {
    if (showForm) {
      setEditCollector(null); // reset edit
    }
    setShowForm(!showForm);
  };

  return (
    <div className="page-container">

      {/* 🔥 TABLE FIRST */}
      <CollectorList
        collectors={collectors}
        refresh={fetchCollectors}
        setEditCollector={handleEdit}
      />

      {/* 🔥 BUTTON CENTER */}
      <div className="btn-container">
        <button className="create-btn" onClick={toggleForm}>
          {showForm ? "Close Form" : " Create Collector"}
        </button>
      </div>

      {/* 🔥 FORM */}
      {showForm && (
        <>
          <hr />

          <CollectorForm
            refresh={() => {
              fetchCollectors();
              setShowForm(false); // close after submit
            }}
            editCollector={editCollector}
            setEditCollector={setEditCollector}
          />
        </>
      )}

    </div>
  );
}

export default CollectorPage;