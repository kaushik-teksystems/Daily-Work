import { useEffect, useState } from "react";
import { BrowserRouter, Routes, Route, NavLink } from "react-router-dom";
import axios from 'axios';
import NoteForm from "./components/NoteForm";
import NoteList from "./components/NoteList";
import Modal from "./components/modal";
import './App.css';

function App() {
  const [notes, setNotes] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [modal, setModal] = useState({
    show: false,
    title: "",
    message: "",
    type: "alert",
    onConfirm: null
  });

  useEffect(() => {
    axios.get("http://localhost:8080/notes")
      .then(response => setNotes(response.data))
      .catch(err => console.error("Could not fetch notes: ", err));
  }, []);

  const filteredNotes = notes.filter(note =>
    note.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
    note.content.toLowerCase().includes(searchTerm.toLowerCase())
  );

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
    axios.put(`http://localhost:8080/notes/${id}`, { status: "closed" })
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
      message: "Are you sure you want to remove this note?",
      type: "confirm",
      onConfirm: () => {
        axios.delete(`http://localhost:8080/notes/${id}`)
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
      <h1>Notes App</h1>
      <div className="main-wrapper">
        <nav className="navbar">
          <NavLink to="/" className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}>Create Note</NavLink>
          <NavLink to="/notes" className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}>Notes</NavLink>
        </nav>

        <div className="content-area">
          <Routes>
            <Route path="/" element={
              <div className="dashboard-layout">

                <div className="side-panel">
                  <label class="dash-labels">Add Note</label>
                  <NoteForm addNote={addNote} showAlert={showAlert}
                  />
                </div>

                <div className="main-feed">
                  <div className="feed-header">
                    <label className="dash-labels">Recently Added</label>
                    <input
                      type="text"
                      placeholder="Search notes..."
                      className="search-input"
                      value={searchTerm}
                      onChange={(e) => setSearchTerm(e.target.value)}
                    />
                  </div>
                  <div className="feed-scroll">
                    <NoteList notes={filteredNotes} deleteNote={deleteNote} toggleComplete={toggleComplete} />
                  </div>
                </div>
              </div>
            } />

            <Route path="/notes" element={
              <div className="full-table-view">
                <div className="feed-header">
                <h1>All Notes</h1>
                <input 
                  type="text"
                  placeholder="Search from DB..."
                  className="search-input"
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                />
                </div>
                <NoteList
                  notes={filteredNotes}
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
