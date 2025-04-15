// ✅ SummaryCard.jsx (요약 결과를 예쁘게 보여주는 카드)
import React from "react";

function SummaryCard({ title, summary, conclusion }) {
  return (
    <div style={{
      background: "#f9f9f9",
      padding: "1rem",
      marginTop: "1rem",
      borderRadius: "8px",
      boxShadow: "0 2px 8px rgba(0, 0, 0, 0.1)"
    }}>
      <h2 style={{ color: "#333", fontSize: "1.2rem" }}>{title || "제목 없음"}</h2>

      <div style={{ marginTop: "1rem" }}>
        <h4 style={{ marginBottom: "0.25rem", color: "#555" }}>📌 요약</h4>
        <p style={{ margin: 0 }}>{summary || "요약 내용이 없습니다."}</p>
      </div>

      <div style={{ marginTop: "1rem" }}>
        <h4 style={{ marginBottom: "0.25rem", color: "#555" }}>🔚 결론</h4>
        <p style={{ margin: 0 }}>{conclusion || "결론이 없습니다."}</p>
      </div>
    </div>
  );
}

export default SummaryCard;
