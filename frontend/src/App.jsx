import React from "react";
import PdfUploader from "./components/PdfUploader";
import "./App.css";

function App() {
  return (
    <div className="App">
      <h1>📚 AI 논문 요약 웹앱</h1>
      <p>PDF 파일을 업로드하면 AI가 논문을 요약해드립니다.</p>
      <PdfUploader />
    </div>
  );
}

export default App;
