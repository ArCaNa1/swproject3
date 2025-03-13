import { useState, useEffect } from "react";
import { testApi } from "./services/api";

function App() {
  const [message, setMessage] = useState("");

  useEffect(() => {
    testApi().then(setMessage);
  }, []);

  return (
    <div className="flex flex-col items-center justify-center min-h-screen">
      <h1 className="text-2xl font-bold">AI 논문 요약</h1>
      <p className="mt-4 text-lg">{message}</p>
    </div>
  );
}

export default App;
