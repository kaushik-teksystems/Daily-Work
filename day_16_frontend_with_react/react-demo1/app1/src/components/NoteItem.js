function NoteItem({ note, deleteNote }) {
  return (
    <li>
      {note.title}, {note.status}
      <button onClick={() => deleteNote(note.id)}>❌</button>
      {/* {note.text} */}
    </li>
  );
}

export default NoteItem;
