import React, { useState } from "react";
import QuestionRow from "./QuestionRow";
import HideAnswersCheckbox from "./HideAnswersCheckbox";

const SecurityQuestionsForm = ({ questions, onSubmit, setModal }) => {
    const [responses, setResponses] = useState(
        Array.from({ length: 5 }, () => ({ questionId: "", answer: "", confirmAnswer: "" }))
    );
    const [hideAnswers, setHideAnswers] = useState(false);

    const handleChange = (index, field, value) => {
        const updated = [...responses];
        updated[index] = { ...updated[index], [field]: value };
        setResponses(updated);
    };

    const handleSubmit = () => {
        for (const r of responses) {
            if (!r.questionId || !r.answer || !r.confirmAnswer) {
                setModal({ show: true, message: "All fields are required" });
                return;
            }
            if (r.answer !== r.confirmAnswer) {
                setModal({ show: true, message: "Answers do not match" });
                return;
            }
            if (r.answer.length < 5 || r.answer.length > 255) {
                setModal({ show: true, message: "Answer must be between 5-255 characters" });
                return;
            }
        }

        onSubmit(responses);
    };

    const selectedIds = responses.map((r) => r.questionId);

    return (
        <div>
            {responses.map((r, i) => (
                <div key={i} className="question-row">
                    <QuestionRow
                        index={i}
                        questions={questions}
                        selectedIds={selectedIds}
                        response={r}
                        onChange={handleChange}
                        hideAnswers={hideAnswers}
                    />
                </div>
            ))}

            <HideAnswersCheckbox hide={hideAnswers} setHide={setHideAnswers} />

            <p>Minimum 5 characters, maximum 255 characters for answers.</p>

            <button onClick={handleSubmit}>Update</button>
        </div>
    );
};

export default SecurityQuestionsForm;