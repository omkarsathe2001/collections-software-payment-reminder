import { useEffect, useState } from "react";
import CustomerForm from "./CustomerForm";
import CustomerList from "./CustomerList";
import "../styles/css/CustomerPage.css";
import API from "../api/axios";

function CustomerPage() {

  const [customers, setCustomers] = useState([]);
  const [editCustomer, setEditCustomer] = useState(null);
  const [showForm, setShowForm] = useState(false); // 🔥 NEW

  // 🔥 FETCH CUSTOMERS
  const fetchCustomers = async () => {
    try {
      const res = await API.get("/customers");

      console.log("DATA:", res.data);

      if (Array.isArray(res.data)) {
        setCustomers(res.data);
      } else if (res.data?.data && Array.isArray(res.data.data)) {
        setCustomers(res.data.data);
      } else {
        setCustomers([]);
      }

    } catch (error) {
      console.error("Error fetching customers:", error);

      if (error.response?.status === 401 || error.response?.status === 403) {
        alert("Session expired ❗");
        localStorage.clear();
        window.location.reload();
      }
    }
  };

  useEffect(() => {
    fetchCustomers();
  }, []);

  // 🔥 EDIT → OPEN FORM
  const handleEdit = (customer) => {
    setEditCustomer(customer);
    setShowForm(true);
  };

  // 🔥 TOGGLE FORM
  const toggleForm = () => {
    if (showForm) {
      setEditCustomer(null); // reset edit
    }
    setShowForm(!showForm);
  };

  return (
    <div className="page-container">

      {/* 🔥 TABLE FIRST */}
      <CustomerList
        customers={customers}
        refresh={fetchCustomers}
        setEditCustomer={handleEdit}
      />

      {/* 🔥 BUTTON CENTER */}
      <div className="btn-container">
        <button className="create-btn" onClick={toggleForm}>
          {showForm ? "Close Form " : "Create Customer"}
        </button>
      </div>

      {/* 🔥 FORM */}
      {showForm && (
        <>
          <hr />

          <CustomerForm
            refresh={() => {
              fetchCustomers();
              setShowForm(false); // close after submit
            }}
            editCustomer={editCustomer}
            setEditCustomer={setEditCustomer}
          />
        </>
      )}

    </div>
  );
}

export default CustomerPage;