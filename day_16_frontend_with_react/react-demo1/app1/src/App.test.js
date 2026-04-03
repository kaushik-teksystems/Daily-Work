import { render, screen, fireEvent } from "@testing-library/react";
import App from "./App";

test("adds and deletes note", () => {
  render(<App />);
  const inputEntry = "New Note";

  fireEvent.change(screen.getByPlaceholderText(/enter note/i), {
    target: { value: inputEntry }
  });

  fireEvent.click(screen.getByText(/add/i));

  expect(screen.getByText(new RegExp(inputEntry, "i"))).toBeInTheDocument();

  fireEvent.click(screen.getByText(/delete/i));

  expect(screen.queryByText(new RegExp(inputEntry, "i"))).not.toBeInTheDocument();
});
