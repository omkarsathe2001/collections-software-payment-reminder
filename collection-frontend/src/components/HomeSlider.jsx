import { useState, useEffect } from "react";
import "../styles/css/HomeSlider.css";

function HomeSlider() {

  const image =
    "https://static.vecteezy.com/system/resources/thumbnails/024/269/311/small/car-house-personal-money-loan-concept-finance-business-icon-on-wooden-cube-saving-money-for-a-car-money-and-house-wooden-cubes-with-word-loan-copy-space-for-text-loan-payment-car-and-house-photo.jpg";

  return (
    <div className="home-container">

      {/* 🔥 TEXT */}
      <div className="home-header">
        <h1 className="animated-title">
          Welcome to Loan Recovery Software
        </h1>
        <p className="animated-subtitle">
          Manage customers, accounts, and collections efficiently.
        </p>
      </div>

      {/* 🔥 IMAGE */}
      <div className="slider-container">
        <img
          src={image}
          className="slider-image"
          alt="loan"
        />
      </div>

    </div>
  );
}

export default HomeSlider;