import { useState } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";

function NoteForm({ addNote, showAlert }) {
  const navigate = useNavigate();
  const [note, setNote] = useState({
    title: "",
    content: "",
    hours: 0,
    minutes: 0,
    priority: 2
  });

  const priorityLabels = { 1: "Low", 2: "Medium", 3: "High" };

  const handleTitleChange = (e) => {
    const val = e.target.value;
    if (val.length === 100) {
      showAlert("Limit Reached", "If more details needed in title, please pass it in the content field.");
    }
    setNote({ ...note, title: val });
  }

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!note.title.trim()) {
      showAlert("Input Required", "Title cannot be empty!");
      return;
    }

    const noteData = {
      title: note.title,
      content: note.content,
      priority: note.priority,
      timeRequired: `${note.hours}h ${note.minutes}m`
    };

    axios.post("http://localhost:3001/notes", noteData)
      .then((response) => {
        addNote(response.data);

        setNote({
          title: "",
          content: "",
          hours: 0,
          minutes: 0,
          priority: 2
        });
        navigate("/notes");
      })
      .catch(err => console.error("Error saving note: ", err));
  };

  return (
    <form onSubmit={handleSubmit}>

      <div className="input-container">
        <input
          type="text"
          placeholder="Title"
          value={note.title}
          maxLength="100"
          onChange={handleTitleChange}
        />
        <div className={`char-counter-bottom ${note.title.length >= 90 ? "limit-near" : ""}`}>
          {note.title.length}/100
        </div>
      </div>

      <div className="input-container">
        <textarea
          placeholder="Content"
          value={note.content}
          maxLength="300"
          rows="4"
          onChange={(e) => setNote({ ...note, content: e.target.value })}
        />
        <div className={`char-counter-bottom ${note.content.length >= 250 ? "limit-near" : ""}`}>
          {note.content.length}/300
        </div>
      </div>

      <div className="priority-container">
        <label className="priority-label">
          Priority: <span className={`priority-text-${note.priority}`}>{priorityLabels[note.priority]}</span>
        </label>
        <input
          type="range"
          min="1"
          max="3"
          step="1"
          value={note.priority}
          onChange={(e) => setNote({ ...note, priority: parseInt(e.target.value) })}
          className="priority-slider"
        />
      </div>
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
