import { useState } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";

function NoteForm({ addNote, showAlert }) {
  const navigate = useNavigate();
  const [note, setNote] = useState({
    title: "",
    content: "",
    hours: 0,
    minutes: 0
  });

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!note.title.trim()) {
      showAlert("Input Required", "Title cannot be empty!");
      return;
    }

    const noteData = {
      title: note.title,
      content: note.content,
      timeRequired: `${note.hours}h ${note.minutes}m`
    };

    axios.post("http://localhost:3001/notes", noteData)
      .then((response) => {
        addNote(response.data);

        setNote({
          title: "",
          content: "",
          hours: 0,
          minutes: 0
        });
        navigate("/notes");
      })
      .catch(err => console.error("Error saving note: ", err));
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Title"
        value={note.title}
        onChange={(e) => setNote({ ...note, title: e.target.value })}
      />
      <input
        type="text"
        placeholder="Content"
        value={note.content}
        onChange={(e) => setNote({ ...note, content: e.target.value })}
      />
      <div className="time-container">
        <label>Time:</label>
        <div className="time-group">
          <input
            type="number"
            className="time-input"
            value={note.hours}
            min="0"
            onChange={(e) => setNote({ ...note, hours: e.target.value })}
          />
          <span>h</span>
        </div>
        <div className="time-group">
          <input
            type="number"
            className="time-input"
            value={note.minutes}
            min="0"
            max="59"
            onChange={(e) => setNote({ ...note, minutes: e.target.value })}
          />
          <span>m</span>
        </div>
      </div>
      <button>Add</button>
    </form>
  );
}

export default NoteForm;
