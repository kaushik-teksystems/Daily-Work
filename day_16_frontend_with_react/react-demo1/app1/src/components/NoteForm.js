import { useState } from "react";
import axios from 'axios';

function NoteForm({ addNote }) {
  const [note, setNote] = useState({
    title:"",
    status: "Open"
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
      status:"open"
    });
  };

  const handleStatusChange = (e) => {
    setNote({ ...note, status: e.target.value });
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
          name="status"
          value="open"
          checked={note.status === "open"}
          onChange={handleStatusChange}
        />
        Open
      </label>
      <label>
        <input
          type="radio"
          name="status"
          value="closed"
          checked={note.status === "closed"}
          onChange={handleStatusChange}
        />
        Closed
      </label>
      <button>Add</button>
    </form>
  );
}

export default NoteForm;
