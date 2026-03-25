import "../styles/css/Navbar.css";

function Navbar({ isLoggedIn, onLoginClick, onLogout, setSection }) {

  const user = JSON.parse(localStorage.getItem("user"));
  const role = user?.role;

  return (
    <nav className="navbar">

      <div className="navbar-left">
        <h2>🏦 Collection Software</h2>
      </div>

      {isLoggedIn && (
        <div className="navbar-center">

          <button onClick={() => setSection("dashboard")}>
            Overview
          </button>

          {/* ADMIN */}
          {role === "ADMIN" && (
            <>
              <button onClick={() => setSection("customer")}>
                Customers
              </button>

              <button onClick={() => setSection("account")}>
                Accounts
              </button>

              <button onClick={() => setSection("collector")}>
                Collectors
              </button>

              <button onClick={() => setSection("collector_dashboard")}>
                Recovery
              </button>

              <button onClick={() => setSection("logs")}>
                Logs
              </button>
            </>
          )}

          {/* COLLECTOR */}
          {role === "COLLECTOR" && (
            <button onClick={() => setSection("collector_dashboard")}>
              Collector Dashboard
            </button>
          )}

        </div>
      )}

      <div className="navbar-right">
        {!isLoggedIn ? (
          <button onClick={onLoginClick}>Login</button>
        ) : (
          <button onClick={onLogout}>Logout</button>
        )}
      </div>

    </nav>
  );
}

export default Navbar;