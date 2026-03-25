import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  ResponsiveContainer
} from "recharts";

function CollectorPerformanceChart({ data }) {

  if (!data || data.length === 0) {
    return <p>No performance data available</p>;
  }

  return (
    <ResponsiveContainer width="100%" height={300}>
      <BarChart data={data}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="name" />
        <YAxis />
        <Tooltip />
        <Bar dataKey="cases" />
      </BarChart>
    </ResponsiveContainer>
  );
}

export default CollectorPerformanceChart;