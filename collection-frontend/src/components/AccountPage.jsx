import { useEffect, useState } from "react";
import AccountForm from "./AccountForm";
import AccountList from "./AccountList";
import "../styles/css/AccountPage.css";
import API from "../api/axios";

function AccountPage() {

  const [accounts, setAccounts] = useState([]);
  const [editAccount, setEditAccount] = useState(null);
  const [showForm, setShowForm] = useState(false);

  // 🔥 FETCH DATA
  const fetchAccounts = async () => {
    try {
      const res = await API.get("/accounts");
      setAccounts(res.data);
    } catch (error) {
      console.error("Error fetching accounts:", error);

      if (error.response?.status === 401 || error.response?.status === 403) {
        alert("Session expired ❗");
        localStorage.clear();
        window.location.reload();
      }
    }
  };

  useEffect(() => {
    fetchAccounts();
  }, []);

  // 🔥 HANDLE EDIT
  const handleEdit = (account) => {
    setEditAccount(account);
    setShowForm(true);
  };

  // 🔥 TOGGLE FORM
  const toggleForm = () => {
    if (showForm) {
      setEditAccount(null);
    }
    setShowForm(!showForm);
  };

  return (
    <div className="page-container">

      {/* 🔥 TABLE FIRST */}
      <AccountList
        accounts={accounts}
        refresh={fetchAccounts}
        setEditAccount={handleEdit}
      />

      {/* 🔥 BUTTON CENTER */}
      <div className="btn-container">
        <button className="create-btn" onClick={toggleForm}>
          {showForm ? "Close Form ❌" : "+ Create Account"}
        </button>
      </div>

      {/* 🔥 FORM (INLINE LIKE COLLECTOR) */}
      {showForm && (
        <>
          <hr />

          <AccountForm
            refresh={() => {
              fetchAccounts();
              setShowForm(false); // close after submit
            }}
            editAccount={editAccount}
            setEditAccount={setEditAccount}
            closeForm={() => {
              setShowForm(false);
              setEditAccount(null);
            }}
          />
        </>
      )}

    </div>
  );
}

export default AccountPage;