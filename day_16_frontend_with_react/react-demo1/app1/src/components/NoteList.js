import NoteItem from "./NoteItem";

function NoteList({ notes, deleteNote, toggleComplete }) {
  return (
    <ul>
      {notes.map((note) => (
        <NoteItem 
        key={note.id} 
        note={note} 
        deleteNote={deleteNote}
        toggleComplete={toggleComplete}   
      />
      ))}
    </ul>
  );
}

export default NoteList;
