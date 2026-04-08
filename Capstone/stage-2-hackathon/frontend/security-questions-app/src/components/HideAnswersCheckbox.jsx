import React from "react";

const HideAnswersCheckbox = ({ hide, setHide }) => {
    return (
        <label>
            <input type="checkbox" checked={hide} onChange={() => setHide(!hide)} />
            Hide Answers
        </label>
    );
};

export default HideAnswersCheckbox;