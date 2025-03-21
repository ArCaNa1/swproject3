import { useState } from "react";
import PdfUploader from "./components/PdfUploader";
import SummaryResult from "./components/SummaryResult";
import "./App.css"; // ✅ CSS 파일 추가

function App() {
  const [summary, setSummary] = useState("");

  return (
    <div className="container">
      <h1>AI 논문 요약</h1>
      <p>PDF 파일을 업로드하면 AI가 핵심 내용을 요약해줍니다.</p>

      <PdfUploader onUpload={setSummary} />
      <SummaryResult summary={summary} />
    </div>
  );
}

export default App;
