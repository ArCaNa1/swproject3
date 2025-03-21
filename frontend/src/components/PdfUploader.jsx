import React, { useState } from "react";
import { pdfjs } from "react-pdf";
import * as XLSX from "xlsx";
import { saveAs } from "file-saver";

// ✅ pdf.worker.js의 경로를 public 폴더에서 직접 가져오기
pdfjs.GlobalWorkerOptions.workerSrc = `${window.location.origin}/pdf.worker.js`;

const PdfUploader = ({ onUpload }) => {
  const [text, setText] = useState("");

  // 📌 PDF 파일 선택 이벤트 핸들러
  const handleFileUpload = async (event) => {
    const file = event.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.readAsArrayBuffer(file);
    reader.onload = async () => {
      const typedarray = new Uint8Array(reader.result);
      const pdf = await pdfjs.getDocument(typedarray).promise;
      let extractedText = "";

      for (let i = 1; i <= pdf.numPages; i++) {
        const page = await pdf.getPage(i);
        const textContent = await page.getTextContent();
        extractedText += textContent.items.map((item) => item.str).join(" ") + "\n";
      }

      setText(extractedText);
      onUpload(extractedText);
    };
  };

  // 📌 엑셀 파일 생성 및 다운로드
  const exportToExcel = () => {
    const ws = XLSX.utils.aoa_to_sheet([[text]]);
    const wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, "PDF_Content");
    const excelBuffer = XLSX.write(wb, { bookType: "xlsx", type: "array" });

    const blob = new Blob([excelBuffer], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });
    saveAs(blob, "extracted_pdf_data.xlsx");
  };

  return (
    <div>
      <h2>📄 PDF 업로드 및 내용 추출</h2>
      <input type="file" accept="application/pdf" onChange={handleFileUpload} />
      <button onClick={exportToExcel} disabled={!text}>📥 엑셀로 저장</button>
      <pre>{text}</pre>
    </div>
  );
};

export default PdfUploader;
