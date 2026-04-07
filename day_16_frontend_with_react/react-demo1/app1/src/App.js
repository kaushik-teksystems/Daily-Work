import { useEffect, useState } from "react";
import { BrowserRouter, Routes, Route, NavLink } from "react-router-dom";
import axios from 'axios';
import NoteForm from "./components/NoteForm";
import NoteList from "./components/NoteList";
import Modal from "./components/modal";
import './App.css';

function App() {
  const [notes, setNotes] = useState([]);

  const [modal, setModal] = useState({
    show: false,
    title: "",
    message: "",
    type: "alert",
    onConfirm: null
  });

  useEffect(() => {
    axios.get("http://localhost:3001/notes")
      .then(response => setNotes(response.data))
      .catch(err => console.error("Could not fetch notes: ", err));
  }, []);

  const addNote = (newNote) => {
    setNotes([...notes, newNote]);
  };

  const closeModal = () => {
    setModal({ ...modal, show: false })
  };

  const showAlert = (title, message) => {
    setModal({
      show: true,
      title: title,
      message: message,
      type: "alert",
      onConfirm: closeModal
    });
  };

  const toggleComplete = (id) => {
    axios.put(`http://localhost:3001/notes/${id}`, { status: "closed" })
      .then(() => {
        setNotes(notes.map(n => n.id === id ? { ...n, status: "closed" } : n));
      })
      .catch(err => {
        setModal({
          show: true,
          title: "Update Failed",
          message: err.response?.data?.error || "Update failed",
          type: "alert",
          onConfirm: closeModal
        });
      });
  };

  const deleteNote = (id) => {
    setModal({
      show: true,
      title: "Delete Note",
      message: "Are you sure you want to emove this note?",
      type: "confirm",
      onConfirm: () => {
        axios.delete(`http://localhost:3001/notes/${id}`)
          .then(() => {
            setNotes(notes.filter((n) => n.id !== id));
            closeModal();
          })
          .catch(err => console.error(err));
      }
    });
  };

  return (
    <BrowserRouter>

      <div className="main-wrapper">
        <nav className="navbar">
          <NavLink to="/" className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}>Create Note</NavLink>
          <NavLink to="/notes" className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}>Notes</NavLink>
        </nav>

        <div className="App">
          <h1>Notes App</h1>

          <Routes>
            <Route path="/" element={
              <div className="form-container-width">
                <NoteForm addNote={addNote} showAlert={showAlert}
                />
              </div>
            } />
            <Route path="/notes" element={
              <div className="list-section table-container-width">
                <label>Current Notes</label>
                <NoteList
                  notes={notes}
                  deleteNote={deleteNote}
                  toggleComplete={toggleComplete}
                />
              </div>
            } />
          </Routes>
        </div>

        <Modal
          isOpen={modal.show}
          title={modal.title}
          message={modal.message}
          type={modal.type}
          onConfirm={modal.onConfirm}
          onCancel={closeModal}
        />
      </div>
    </BrowserRouter>
  );
}

export default App;
