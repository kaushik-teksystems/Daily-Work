import React from "react";

const QuestionRow = ({
    index,
    questions,
    selectedIds,
    response,
    onChange,
    onBlur,
    hideAnswers,
    validation
}) => {

    const filteredQuestions = questions.filter(
        (q) => !selectedIds.includes(q.id) || q.id === response.questionId
    );

    const getAnswerError = () => {
        if (validation === "empty") return "Required";
        if (validation === "short") return "Min 5 characters";
        if (validation === "long") return "Max 255 characters";
        return "";
    };

    const getConfirmError = () => {
        if (validation === "confirmEmpty") return "Required";
        if (validation === "mismatch") return "Does not match";
        return "";
    };

    return (
        <div className="question-row">

            <select
                value={response.questionId || ""}
                onChange={(e) => onChange(index, "questionId", e.target.value)}
            >
                <option value="">Select Question</option>
                {filteredQuestions.map((q) => (
                    <option key={q.id} value={q.id}>{q.question}</option>
                ))}
            </select>

            <div className="answer-fields">

                <div className="input-wrapper">
                    <input
                        type={hideAnswers ? "password" : "text"}
                        placeholder="Answer"
                        value={response.answer}
                        onChange={(e) => onChange(index, "answer", e.target.value)}
                        onBlur={() => onBlur(index, "touchedAnswer")}
                        className={
                            validation === "valid"
                                ? "valid"
                                : ["empty", "short", "long"].includes(validation)
                                    ? "error"
                                    : ""
                        }
                    />

                    {getAnswerError() && (
                        <span className="error-text">{getAnswerError()}</span>
                    )}
                </div>

                <div className="input-wrapper">
                    <input
                        type={hideAnswers ? "password" : "text"}
                        placeholder="Confirm Answer"
                        value={response.confirmAnswer}
                        onChange={(e) => onChange(index, "confirmAnswer", e.target.value)}
                        onBlur={() => onBlur(index, "touchedConfirm")}
                        className={
                            validation === "valid"
                                ? "valid"
                                : ["confirmEmpty", "mismatch"].includes(validation)
                                    ? "error"
                                    : ""
                        }
                    />

                    {getConfirmError() && (
                        <span className="error-text">{getConfirmError()}</span>
                    )}
                </div>

            </div>
        </div>
    );
};

export default QuestionRow;