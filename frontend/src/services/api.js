const API_BASE_URL = "http://localhost:8080/api";

export const testApi = async () => {
  try {
    const response = await fetch(`${API_BASE_URL}/test`);
    const data = await response.text();
    return data;
  } catch (error) {
    console.error("API 호출 실패:", error);
    return "오류 발생!";
  }
};
