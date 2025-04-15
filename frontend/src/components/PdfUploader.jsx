import React, { useState } from "react";

function PdfUploader() {
  const [file, setFile] = useState(null);
  const [summary, setSummary] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async () => {
    if (!file) return;
    setLoading(true);
    setSummary("");

    const formData = new FormData();
    formData.append("file", file);

    try {
      const response = await fetch("http://localhost:8080/api/upload", {
        method: "POST",
        body: formData
      });

      const data = await response.json();
      setSummary(data.summary);
    } catch (error) {
      setSummary("요약 실패: " + error.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: "2rem", maxWidth: "700px", margin: "auto" }}>
      <h2>📄 PDF 논문 요약기</h2>
      <input
        type="file"
        accept="application/pdf"
        onChange={(e) => setFile(e.target.files[0])}
      />
      <button onClick={handleSubmit} disabled={loading || !file} style={{ marginTop: "1rem" }}>
        {loading ? "요약 중..." : "PDF 업로드 및 요약"}
      </button>
      {summary && (
        <div style={{ marginTop: "2rem", background: "#f0f0f0", padding: "1rem" }}>
          <h4>📌 요약 결과</h4>
          <p>{summary}</p>
        </div>
      )}
    </div>
  );
}

export default PdfUploader;
