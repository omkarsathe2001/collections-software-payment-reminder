import { useState, useEffect } from "react";
import CustomerPage from "./components/CustomerPage";
import Dashboard from "./components/Dashboard";
import CollectorDashboard from "./components/CollectorDashboard";
import Navbar from "./components/Navbar";
import Login from "./components/Login";
import AccountPage from "./components/AccountPage";
import CollectorPage from "./components/CollectorPage";
import HomeSlider from "./components/HomeSlider"; // ✅ ADD THIS
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./App.css";
import ReminderLogsPage from "./components/ReminderLogsPage";

function App() {

  const [page, setPage] = useState("home");
  const [section, setSection] = useState("dashboard");

  const [isLoggedIn, setIsLoggedIn] = useState(
    !!localStorage.getItem("token")
  );

  useEffect(() => {
    if (localStorage.getItem("token")) {
      setIsLoggedIn(true);
      setPage("app");
    }
  }, []);

  const handleLogin = () => {
    setIsLoggedIn(true);
    setPage("app");
  };

  const handleLogout = () => {
    localStorage.removeItem("user");
    localStorage.removeItem("token");
    setIsLoggedIn(false);
    setPage("home");
    setSection("dashboard");
  };

  return (
    <div>

      <ToastContainer position="top-right" autoClose={2000} />

      <Navbar
        isLoggedIn={isLoggedIn}
        onLoginClick={() => setPage("login")}
        onLogout={handleLogout}
        setSection={setSection}
      />

      {/* 🏠 HOME PAGE */}
      {page === "home" && <HomeSlider />}

      {/* 🔐 LOGIN */}
      {page === "login" && !isLoggedIn && (
        <Login onLogin={handleLogin} />
      )}

      {/* 🔐 APP */}
      {page === "app" && isLoggedIn && (
        <>
          {section === "dashboard" && <Dashboard />}
          {section === "customer" && <CustomerPage />}
          {section === "account" && <AccountPage />}
          {section === "collector" && <CollectorPage />}
          {section === "collector_dashboard" && <CollectorDashboard />}
          {section === "logs" && <ReminderLogsPage />}
        </>
      )}

    </div>
  );
}

export default App;