import React, { useState } from "react";
import QuestionRow from "./QuestionRow";
import HideAnswersCheckbox from "./HideAnswersCheckbox";

const SecurityQuestionsForm = ({ questions, onSubmit }) => {
    const [responses, setResponses] = useState(
        Array.from({ length: 5 }, () => ({
            questionId: "",
            answer: "",
            confirmAnswer: "",
            touchedAnswer: false,
            touchedConfirm: false
        }))
    );

    const [hideAnswers, setHideAnswers] = useState(false);

    const handleChange = (index, field, value) => {
        const updated = [...responses];
        updated[index] = { ...updated[index], [field]: value };
        setResponses(updated);
    };

    const handleBlur = (index, field) => {
        const updated = [...responses];
        updated[index][field] = true; 
        setResponses(updated);
    };

    const getValidation = (r) => {
        if (!r.touchedAnswer && !r.touchedConfirm) return "idle";

        if (!r.answer) return "empty";
        if (r.answer.length < 5) return "short";
        if (r.answer.length > 255) return "long";

        if (r.touchedConfirm) {
            if (!r.confirmAnswer) return "confirmEmpty";
            if (r.answer !== r.confirmAnswer) return "mismatch";
        }

        return "valid";
    };

    const isFormValid = () =>
        responses.every(
            (r) =>
                r.questionId &&
                r.answer &&
                r.confirmAnswer &&
                r.answer.length >= 5 &&
                r.answer.length <= 255 &&
                r.answer === r.confirmAnswer
        );

    return (
        <div className="form-container">
            {responses.map((r, i) => (
                <QuestionRow
                    key={i}
                    index={i}
                    questions={questions}
                    selectedIds={responses.map((r) => r.questionId)}
                    response={r}
                    onChange={handleChange}
                    onBlur={handleBlur}   
                    hideAnswers={hideAnswers}
                    validation={getValidation(r)}
                />
            ))}

            <HideAnswersCheckbox hide={hideAnswers} setHide={setHideAnswers} />

            <button disabled={!isFormValid()}>
                Update
            </button>
        </div>
    );
};

export default SecurityQuestionsForm;