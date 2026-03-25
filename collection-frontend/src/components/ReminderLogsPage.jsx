import { useEffect, useState } from "react";
import API from "../api/axios";
import "../styles/css/AccountList.css"; // reuse table css

function ReminderLogsPage() {

  const [logs, setLogs] = useState([]);

  useEffect(() => {
    API.get("/reminders")
      .then(res => setLogs(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div className="account-table-container">

      <h2>Reminder Logs</h2>

      <table className="account-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Account ID</th>
            <th>Date</th>
            <th>Message</th>
            <th>Type</th>
          </tr>
        </thead>

        <tbody>
          {logs.length === 0 ? (
            <tr>
              <td colSpan="5">No logs found</td>
            </tr>
          ) : (
            logs.map(log => (
              <tr key={log.reminderId}>
                <td>{log.reminderId}</td>
                <td>{log.accountId}</td>
                <td>{log.reminderDate}</td>
                <td>{log.message}</td>
                <td>{log.reminderType}</td>
              </tr>
            ))
          )}
        </tbody>
      </table>

    </div>
  );
}

export default ReminderLogsPage;