import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  ResponsiveContainer
} from "recharts";

function DPDChart({ data }) {

  if (!data || data.length === 0) {
    return <p>Loading chart...</p>;
  }

  return (
    <div style={{ width: "100%", height: 300 }}>
      <h3>DPD Distribution</h3>

      <ResponsiveContainer>
        <BarChart data={data}>
          <CartesianGrid strokeDasharray="4 3" />
          <XAxis dataKey="name" />
          <YAxis />
          <Tooltip />
          <Bar dataKey="value" fill="#8884d8" />
        </BarChart>
      </ResponsiveContainer>
    </div>
  );
}

export default DPDChart;