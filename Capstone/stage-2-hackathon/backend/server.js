const express = require("express");
const cors = require("cors");

const app = express();

const securityQuestionRoutes = require("./routes/securityQuestionsRoutes");

const corsOptions = {
    origin: "http://localhost:3000",
    methods: ["GET", "POST"]
};

app.use(cors(corsOptions))
app.use(express.json());

app.use("/security-questions", securityQuestionRoutes);

const PORT = 5001;

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});

module.exports = app;