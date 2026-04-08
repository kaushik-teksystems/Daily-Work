const request = require("supertest");
const chai = require("chai");
const fs = require("fs").promises;
const app = require("../server");

const { expect } = chai;
const RESPONSE_FILE = "./data/responses.json";

describe("Security Questions API", () => {

  beforeEach(async () => {
    await fs.writeFile(RESPONSE_FILE, JSON.stringify([], null, 2));
  });

  it("GET /security-questions should return all questions", async () => {
    const response = await request(app).get("/security-questions");
    expect(response.status).to.equal(200);
    expect(response.body).to.be.an("array");
    expect(response.body.length).to.be.at.least(5);
    expect(response.body[0]).to.have.property("id");
    expect(response.body[0]).to.have.property("question");
  });

  it("POST /security-questions should save valid responses", async () => {
    const payload = {
      responses: [
        { questionId: "q1", answer: "Answer1", confirmAnswer: "Answer1" },
        { questionId: "q2", answer: "Answer2", confirmAnswer: "Answer2" },
        { questionId: "q3", answer: "Answer3", confirmAnswer: "Answer3" },
        { questionId: "q4", answer: "Answer4", confirmAnswer: "Answer4" },
        { questionId: "q5", answer: "Answer5", confirmAnswer: "Answer5" }
      ]
    };

    const response = await request(app)
      .post("/security-questions")
      .send(payload);

    expect(response.status).to.equal(200);
    expect(response.body).to.have.property("responses");
    expect(response.body.responses.length).to.equal(5);
  });

  it("POST should fail if duplicate questions are selected", async () => {
    const payload = {
      responses: [
        { questionId: "q1", answer: "Answer1", confirmAnswer: "Answer1" },
        { questionId: "q1", answer: "Answer2", confirmAnswer: "Answer2" },
        { questionId: "q3", answer: "Answer3", confirmAnswer: "Answer3" },
        { questionId: "q4", answer: "Answer4", confirmAnswer: "Answer4" },
        { questionId: "q5", answer: "Answer5", confirmAnswer: "Answer5" }
      ]
    };

    const response = await request(app)
      .post("/security-questions")
      .send(payload);

    expect(response.status).to.equal(400);
    expect(response.body).to.have.property("error", "Duplicate questions selected");
  });

  it("POST should fail if answers do not match", async () => {
    const payload = {
      responses: [
        { questionId: "q1", answer: "Answer1", confirmAnswer: "Mismatch" },
        { questionId: "q2", answer: "Answer2", confirmAnswer: "Answer2" },
        { questionId: "q3", answer: "Answer3", confirmAnswer: "Answer3" },
        { questionId: "q4", answer: "Answer4", confirmAnswer: "Answer4" },
        { questionId: "q5", answer: "Answer5", confirmAnswer: "Answer5" }
      ]
    };

    const response = await request(app)
      .post("/security-questions")
      .send(payload);

    expect(response.status).to.equal(400);
    expect(response.body).to.have.property("error", "Answers do not match");
  });

  it("POST should fail if less than 5 responses are sent", async () => {
    const payload = {
      responses: [
        { questionId: "q1", answer: "Answer1", confirmAnswer: "Answer1" },
        { questionId: "q2", answer: "Answer2", confirmAnswer: "Answer2" }
      ]
    };

    const response = await request(app)
      .post("/security-questions")
      .send(payload);

    expect(response.status).to.equal(400);
    expect(response.body).to.have.property("error", "All questions are mandatory");
  });

  it("POST should fail if answer is empty", async () => {
    const payload = {
      responses: [
        { questionId: "q1", answer: "", confirmAnswer: "" },
        { questionId: "q2", answer: "Answer2", confirmAnswer: "Answer2" },
        { questionId: "q3", answer: "Answer3", confirmAnswer: "Answer3" },
        { questionId: "q4", answer: "Answer4", confirmAnswer: "Answer4" },
        { questionId: "q5", answer: "Answer5", confirmAnswer: "Answer5" }
      ]
    };

    const response = await request(app)
      .post("/security-questions")
      .send(payload);

    expect(response.status).to.equal(400);
    expect(response.body).to.have.property("error", "Answer and Confirm Answer are required");
  });

});