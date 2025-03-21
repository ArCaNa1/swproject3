const SummaryResult = ({ summary }) => {
    if (!summary) return null;
  
    return (
      <div className="result-box">
        <h2>요약 결과</h2>
        <p>{summary}</p>
      </div>
    );
  };
  
  export default SummaryResult;