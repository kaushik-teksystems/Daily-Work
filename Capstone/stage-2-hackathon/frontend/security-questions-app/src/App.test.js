import { render, screen, fireEvent } from "@testing-library/react";
import App from "./App";

test("renders 5 question dropdowns", () => {
  render(<App />);
  const selects = screen.getAllByRole("combobox");
  expect(selects.length).toBe(5);
});

test("allows typing in answer and confirm answer fields", () => {
  render(<App />);
  const answerInputs = screen.getAllByPlaceholderText("Answer");
  const confirmInputs = screen.getAllByPlaceholderText("Confirm Answer");

  fireEvent.change(answerInputs[0], { target: { value: "TestAnswer" } });
  fireEvent.change(confirmInputs[0], { target: { value: "TestAnswer" } });

  expect(answerInputs[0].value).toBe("TestAnswer");
  expect(confirmInputs[0].value).toBe("TestAnswer");
});

test("hide answers checkbox changes input type to password", () => {
  render(<App />);
  const answerInputs = screen.getAllByPlaceholderText("Answer");
  const checkbox = screen.getByLabelText(/hide answers/i);

  expect(answerInputs[0].type).toBe("text");

  fireEvent.click(checkbox);
  expect(answerInputs[0].type).toBe("password");

  fireEvent.click(checkbox);
  expect(answerInputs[0].type).toBe("text");
});

test("shows required field error when submitting empty form", () => {
  render(<App />);
  const updateButton = screen.getByText("Update");

  fireEvent.click(updateButton);

  expect(screen.getByText(/all fields are required/i)).toBeInTheDocument();
});