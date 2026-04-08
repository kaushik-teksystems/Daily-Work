const express = require("express");
const router = express.Router();

const {
  getAllQuestions,
  saveSecurityResponses
} = require("../controllers/SecurityQuestionsController");


router.get("/", getAllQuestions);

router.post("/", saveSecurityResponses);

module.exports = router;