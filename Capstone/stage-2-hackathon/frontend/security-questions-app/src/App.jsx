import React, { useEffect, useState } from "react";
import axios from "axios";
import SecurityQuestionsForm from "./components/SecurityQuestionsForm";
import "./App.css";

function App() {
  const [questions, setQuestions] = useState([]);
  const [modal, setModal] = useState({ show: false, message: "" });

  useEffect(() => {
    const fetchQuestions = async () => {
      try {
        const res = await axios.get("http://localhost:5001/security-questions");
        setQuestions(res.data);
      } catch (err) {
        setModal({ show: true, message: "Failed to load questions" });
      }
    };
    fetchQuestions();
  }, []);

  const handleSubmit = async (responses) => {
    try {
      const payload = { responses };
      await axios.post("http://localhost:5001/security-questions", payload);
      setModal({ show: true, message: "Responses saved successfully" });
    } catch (err) {
      setModal({
        show: true,
        message: err.response?.data?.error || "Failed to save responses",
      });
    }
  };

  return (
    <div className="layout">

      <div className="right-panel">
        <div className="right-content">
          <h1>Secure Your Account 🔐</h1>
          <p>
            Set up your security questions to protect your account.
            These help us verify your identity.
          </p>
        </div>
      </div>

      <div className="left-panel">
        <h2>Security Questions</h2>

        <SecurityQuestionsForm
          questions={questions}
          onSubmit={handleSubmit}
        />
      </div>

    </div>
  );
}

export default App;