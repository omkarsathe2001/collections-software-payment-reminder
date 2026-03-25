import { useState } from "react";
import "../styles/css/Login.css";
import API from "../api/axios";
import { jwtDecode } from "jwt-decode";

function Login({ onLogin }) {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!username || !password) {
      alert("Please enter username and password ❗");
      return;
    }

    try {
      const res = await API.post("/api/auth/login", {
        username,
        password
      });

      const token = res.data;

      // ✅ store token
      localStorage.setItem("token", token);

      // ✅ decode token
      const decoded = jwtDecode(token);

      // ✅ store user object (IMPORTANT)
      localStorage.setItem("user", JSON.stringify({
        username: decoded.sub,
        role: decoded.role
      }));

      onLogin();

    } catch (err) {
      console.error(err);
      alert("Invalid credentials ❌");
    }
  };

  return (
    <div className="login-container">
      <div className="login-card">
        <h2>🔐 Login</h2>

        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Enter Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />

          <input
            type="password"
            placeholder="Enter Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button type="submit">Login</button>
        </form>
      </div>
    </div>
  );
}

export default Login;