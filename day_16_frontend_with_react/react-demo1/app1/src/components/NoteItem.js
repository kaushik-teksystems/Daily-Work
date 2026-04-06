function NoteItem({ note, deleteNote, toggleComplete }) {
  const isClosed = note.status === "closed";
  return (
    <li className={`note-item ${isClosed ? "completed" : ""}`}>
      <div className="task-content">
        <h3>{note.title}</h3>
        <p>{note.content}</p>
        <span className="note-time">{note.timeRequired}</span>
      </div>
      <div className="actions">
        {!isClosed && (
          <button className="tick-btn" onClick={() => toggleComplete(note.id)}>✔</button>
        )}
        <button className="delete-btn" onClick={() => deleteNote(note.id)}>✕</button>
      </div>
    </li>
  );
}

export default NoteItem;
