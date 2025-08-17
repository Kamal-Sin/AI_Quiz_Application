// API configuration
const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';

export const API_ENDPOINTS = {
  // User endpoints
  LOGIN: `${API_BASE_URL}/user/login`,
  REGISTER: `${API_BASE_URL}/user/register`,
  GET_USER: `${API_BASE_URL}/user/getUser`,
  USER_CREATIONS: `${API_BASE_URL}/user/creations`,
  USER_ATTEMPTED: `${API_BASE_URL}/user/attempted`,
  
  // Quiz endpoints
  CREATE_QUIZ: `${API_BASE_URL}/quiz/create`,
  GET_QUIZ: `${API_BASE_URL}/quiz`,
  QUIZ_ATTEMPT: `${API_BASE_URL}/quiz/attempt`,
  QUIZ_START: `${API_BASE_URL}/quiz/start`,
  QUIZ_SUBMIT: `${API_BASE_URL}/quiz/submit`,
  GENERATE_AI_QUIZ: `${API_BASE_URL}/quiz/generate-ai`,
  
  // Questions endpoints
  GET_QUESTIONS: `${API_BASE_URL}/user/creations`,
};

export default API_BASE_URL;
