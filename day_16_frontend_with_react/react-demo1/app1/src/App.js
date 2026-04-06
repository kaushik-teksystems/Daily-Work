import { useEffect, useState } from "react";
import axios from 'axios';
import NoteForm from "./components/NoteForm";
import NoteList from "./components/NoteList";
import './App.css';

function App() {
  const [notes, setNotes] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:3001/notes")
    .then(response => setNotes(response.data))
    .catch(err => console.error("Could not fetch notes: ", err));
  }, []);

  const addNote = (newNote) => {
    setNotes([...notes, newNote]);
  };

  const toggleComplete = (id) => {
    axios.put(`http://localhost:3001/notes/${id}`, { status: "closed"})
    .then(() => {
      setNotes(notes.map(n => n.id === id ? { ...n, status: "closed"} : n));
    })
    .catch(err => {
      alert(err.response?.data?.error || "Update failed");
    });
  };

  const deleteNote = (id) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this note?");

    if(confirmDelete) {
      axios.delete(`http://localhost:3001/notes/${id}`)
      .then(() => {
        setNotes(notes.filter((n) => n.id !== id));
      })
      .catch(err => console.error("Delete failed: ", err));
    }
  };

  return (
    <div className = "App">
      <h1>Notes App</h1>
      <NoteForm addNote={addNote} />
      <div clasName="list-section">
        <label>Current Tasks</label>
        <NoteList
          notes={notes}
          deleteNote={deleteNote}
          toggleComplete={toggleComplete}
        />
      </div>
    </div>
  );
}

export default App;
