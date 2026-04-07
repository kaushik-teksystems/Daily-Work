import NoteItem from "./NoteItem";

function NoteList({ notes, deleteNote, toggleComplete }) {
  return (
    <div className="table-wrapper">
      <table className="notes-table">
        <thead>
          <tr>
            <th>Sl. no.</th>
            <th>Title & Content</th>
            <th>Completion Time</th>
            <th>Status</th>
            <th>Created On</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
            {notes.map((note, index) => (
            <tr key={note.id} className={note.status === 'closed' ? 'row-completed' : ''}>
              <td>{index + 1}</td>
              <td>
                <div className="table-title">{note.title}</div>
                <div className="table-desc">{note.content}</div>
              </td>
              <td>{note.timeRequired}</td>
              <td>
                <span className={`status-badge ${note.status}`}>
                  {note.status}
                </span>
              </td>
              <td className="table-date">{note.createdAt.split(',')[0]}</td>
              <td>
                <div className="table-actions">
                  {note.status !== 'closed' && (
                    <button className="tick-btn" onClick={() => toggleComplete(note.id)}>✔</button>
                  )}
                  <button className="delete-btn" onClick={() => deleteNote(note.id)}>✕</button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default NoteList;
