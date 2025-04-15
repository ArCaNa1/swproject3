const API_BASE_URL = "http://localhost:8080/api";

export const uploadPdf = async (file) => {
  const formData = new FormData();
  formData.append("file", file);

  try {
    const response = await fetch(`${API_BASE_URL}/upload`, {
      method: "POST",
      body: formData,
      headers: {
        "Accept": "application/json",
      },
      mode: "cors",
    });

    if (!response.ok) {
      throw new Error(`서버 응답 실패: ${response.status}`);
    }

    return await response.json();
  } catch (error) {
    console.error("파일 업로드 실패:", error);
    return { error: "파일 업로드 실패" };
  }
};
