import React from "react";

const QuestionRow = ({ index, questions, selectedIds, response, onChange, hideAnswers }) => {
    const filteredQuestions = questions.filter(
        (q) => !selectedIds.includes(q.id) || q.id === response.questionId
    );

    return (
        <div className="question-row">
            <select
                value={response.questionId || ""}
                onChange={(e) => onChange(index, "questionId", e.target.value)}
            >
                <option value="">Select Question</option>
                {filteredQuestions.map((q) => (
                    <option key={q.id} value={q.id}>
                        {q.question}
                    </option>
                ))}
            </select>
            <div className="answer-fields">
                <input
                    type={hideAnswers ? "password" : "text"}
                    placeholder="Answer"
                    value={response.answer || ""}
                    maxLength={255}
                    onChange={(e) => onChange(index, "answer", e.target.value)}
                />

                <input
                    type={hideAnswers ? "password" : "text"}
                    placeholder="Confirm Answer"
                    value={response.confirmAnswer || ""}
                    maxLength={255}
                    onChange={(e) => onChange(index, "confirmAnswer", e.target.value)}
                />
            </div>
        </div>
    );
};

export default QuestionRow;