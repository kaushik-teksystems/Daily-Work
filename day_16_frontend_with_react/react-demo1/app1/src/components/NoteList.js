import NoteItem from "./NoteItem";

function NoteList({ notes, deleteNote, toggleComplete }) {
  
  const sortedNotes = [...notes].sort((a, b) => b.priority - a.priority);

  return (
    <div className="table-wrapper">
      <table className="notes-table">
        <thead>
          <tr>
            <th>Sl. no.</th>
            <th>Title & Content</th>
            <th>Completion Time</th>
            <th>Priority</th>
            <th>Status</th>
            <th>Created On</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {sortedNotes.map((note, index) => (
            <tr key={note.id} className={note.status === 'closed' ? 'row-completed' : ''}>
              <td>{index + 1}</td>
              <td>
                <div className="table-title">{note.title}</div>
                <div className="table-desc">{note.content}</div>
              </td>
              <td>{note.timeRequired}</td>
              <td className="col-priority">
                <span className={`priority-badge level-${note.priority}`}>{note.priority === 3 ? "High" : note.priority === 2 ? "Medium" : "Low"}
                </span>
              </td>
              <td>
                <span className={`status-badge ${note.status}`}>
                  {note.status}
                </span>
              </td>
              <td className="table-date">{note.createdAt ? note.createdAt.split(',')[0] : 'No Date'}</td>
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
