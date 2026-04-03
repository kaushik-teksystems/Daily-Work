import { useState } from "react";
import axios from 'axios';

function NoteForm({ addNote }) {
  const [note, setNote] = useState({
    title:"",
    content: "Open"
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!note.title.trim()){
      alert("Title cannot be empty!");
      return;
    } 
    sendPostRequest(note);
    addNote(note);
    setNote({
      title:"",
      content:"open"
    });
  };

  const handleContentChange = (e) => {
    setNote({ ...note, content: e.target.value });
  }

  const sendPostRequest = () => {
    axios.post("http://localhost:3001/notes", note);
  }

  return (
    <form onSubmit={handleSubmit}>
      <input
        placeholder="Enter note"
        value={note.title}
        onChange={(e) => setNote({...note, title:e.target.value})}
      />
      <label>
        <input
          type="radio"
          name="content"
          value="open"
          checked={note.content === "open"}
          onChange={handleContentChange}
        />
        Open
      </label>
      <label>
        <input
          type="radio"
          name="status"
          value="closed"
          checked={note.content === "closed"}
          onChange={handleContentChange}
        />
        Closed
      </label>
      <button>Add</button>
    </form>
  );
}

export default NoteForm;
