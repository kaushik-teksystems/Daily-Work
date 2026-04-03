import { render, screen } from "@testing-library/react";
import NoteList from "./NoteList";

test("renders notes", () => {
  const notes = [
    { id: 1, title: "Note 1", status: "pending" },
    { id: 2, title: "Note 2", status: "done" }
  ];

  render(<NoteList notes={notes} deleteNote={() => {}} />);

  notes.forEach(note => {
  expect(screen.getByText(new RegExp(note.title, "i"))).toBeInTheDocument();
  });
});
