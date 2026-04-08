const {
  getQuestions,
  saveResponses
} = require("../services/SecurityQuestionsService");


exports.getAllQuestions = async (request, response) => {
  try {
    const questions = await getQuestions();
    response.json(questions);
  } catch (error) {
    response.status(500).json({
      error: "Failed to fetch questions"
    });
  }
};

exports.saveSecurityResponses = async (request, response) => {
  try {
    const data = request.body;
    if (!data.responses || data.responses.length !== 5) {
      return response.status(400).json({
        error: "All questions are mandatory"
      });
    }

    const savedPayload = await saveResponses(data);

    response.status(200).json(savedPayload);

  } catch (error) {
    response.status(400).json({
      error: error.message
    });
  }
};