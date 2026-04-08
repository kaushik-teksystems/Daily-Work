const fs = require("fs").promises;

const QuestionFile = "./data/questions.json";
const ResponseFile = "./data/responses.json";

async function getQuestions() {
    const questions = await fs.readFile(QuestionFile, "utf-8");
    return JSON.parse(questions);
}

async function saveResponses(data) {

    const responses = data.responses;

    const userId = Date.now().toString();

    const ids = responses.map(response => response.questionId);
    const hasDuplicate = new Set(ids).size !== ids.length;

    if (hasDuplicate) {
        throw new Error("Duplicate questions selected");
    }

    responses.forEach(response => {

        if (!response.answer || !response.confirmAnswer) {
            throw new Error("Answer and Confirm Answer are required");
        }

        if (response.answer !== response.confirmAnswer) {
            throw new Error("Answers do not match");
        }

        if (response.answer.trim().length < 5) {
            throw new Error("Minimum 5 characters required");
        }

        if (response.answer.length > 255) {
            throw new Error("Maximum 255 characters allowed");
        }

    });

    const payload = {
        userId,
        responses
    };

    let existing = [];

    try {
        const file = await fs.readFile(ResponseFile, "utf-8");
        existing = JSON.parse(file);
    } catch (error) {
        existing = [];
    }

    existing.push(payload);

    await fs.writeFile(
        ResponseFile,
        JSON.stringify(existing, null, 2)
    );

    return payload;
}

module.exports = {
    getQuestions,
    saveResponses
};