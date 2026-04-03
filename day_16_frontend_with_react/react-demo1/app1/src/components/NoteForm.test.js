import { render, screen, fireEvent } from "@testing-library/react";
import NoteForm from "./NoteForm";

test("adds note on submit", () => {
  const addNote = jest.fn();
  const testValue = "Test Note";

  render(<NoteForm addNote={addNote} />);

  fireEvent.change(screen.getByPlaceholderText(/enter note/i), {
    target: { value: testValue }
  });

  fireEvent.click(screen.getByText(/add/i));

  expect(addNote).toHaveBeenCalledWith(
  expect.objectContaining({title: testValue})
  );
});
