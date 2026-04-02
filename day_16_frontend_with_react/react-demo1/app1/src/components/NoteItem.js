function NoteItem({ note, deleteNote }) {
  return (
    <li>
      {note.text}
      <button onClick={() => deleteNote(note.id)}>Delete</button>
    </li>
  );
}

export default NoteItem;
