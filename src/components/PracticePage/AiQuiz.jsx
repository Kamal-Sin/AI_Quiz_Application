import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { API_ENDPOINTS } from "../../utils/api";

const AiQuiz = () => {
   const [grade, setGrade] = useState("");
   const [difficulty, setDifficulty] = useState("");
   const [subject, setSubject] = useState("");
   const [topic, setTopic] = useState("");
   const [error, setError] = useState("");
   const [loading, setLoading] = useState(false);
   const navigate = useNavigate();

   const handleGenerateQuiz = async () => {
      // Validate inputs
      if (!grade || !difficulty || !subject || !topic) {
         setError("Please fill in all fields");
         return;
      }

      setLoading(true);
      setError("");

      try {
         const response = await fetch(API_ENDPOINTS.GENERATE_AI_QUIZ, {
            method: "POST",
            headers: {
               "Content-Type": "application/json",
            },
            body: JSON.stringify({
               grade,
               difficulty,
               subject,
               topic,
            }),
         });

         if (response.ok) {
            const questions = await response.json();
            // Store questions in localStorage for the quiz component to use
            localStorage.setItem("aiGeneratedQuestions", JSON.stringify(questions));
            localStorage.setItem("aiQuizInfo", JSON.stringify({
               title: `AI Generated Quiz - ${subject}: ${topic}`,
               grade,
               difficulty,
               subject,
               topic
            }));
            navigate("/ai-quiz");
         } else {
            const errorText = await response.text();
            setError(`Failed to generate quiz: ${errorText}`);
         }
      } catch (error) {
         setError("Network error. Please check your connection.");
      } finally {
         setLoading(false);
      }
   };

   return (
      <div className="d-flex flex-column justify-content-center align-items-center w-50">
         <div className="w-50 text-center">
            <h2 className="fw-bold text-danger">Want to practice with A.I Generated Quiz</h2>
         </div>
         <div className="d-flex mt-4">
            <select
               name="grade-select"
               id="grade-select"
               value={grade}
               onChange={(e) => setGrade(e.target.value)}
               className="form-select me-2"
            >
               <option value="">Select your grade...</option>
               <option value="1st grade">1st grade</option>
               <option value="2nd grade">2nd grade</option>
               <option value="3rd grade">3rd grade</option>
               <option value="4th grade">4th grade</option>
               <option value="5th grade">5th grade</option>
               <option value="6th grade">6th grade</option>
               <option value="7th grade">7th grade</option>
               <option value="8th grade">8th grade</option>
               <option value="9th grade">9th grade</option>
               <option value="10th grade">10th grade</option>
               <option value="11th grade">11th grade</option>
               <option value="12th grade">12th grade</option>
               <option value="bachelor's scholar">Bachelor's</option>
               <option value="master's scholar">Master's</option>
               <option value="phd scholar">Phd</option>
            </select>
            <select 
               name="difficulty-select" 
               id="difficulty-select" 
               value={difficulty}
               onChange={(e) => setDifficulty(e.target.value)}
               className="form-select ms-2"
            >
               <option value="">Select difficulty level</option>
               <option value="easy">Easy</option>
               <option value="medium">Medium</option>
               <option value="hard">Hard</option>
            </select>
         </div>
         <div className="mt-3 d-flex user-input">
            <input 
               type="text" 
               name="subject" 
               id="subject" 
               placeholder="Enter subject name" 
               value={subject}
               onChange={(e) => setSubject(e.target.value)}
               className="form-control me-2"
            />
            <input 
               type="text" 
               name="topic" 
               id="topic" 
               placeholder="Enter topic here" 
               value={topic}
               onChange={(e) => setTopic(e.target.value)}
               className="form-control ms-2"
            />
         </div>
         <div>
            <button 
               className="btn btn-success mt-4 mb-0 fs-4 px-4"
               onClick={handleGenerateQuiz}
               disabled={loading}
            >
               {loading ? "Generating..." : "Start"}
            </button>
         </div>
         {error && (
            <div className="mt-3">
               <span className="text-danger">{error}</span>
            </div>
         )}
      </div>
   );
};

export default AiQuiz;
