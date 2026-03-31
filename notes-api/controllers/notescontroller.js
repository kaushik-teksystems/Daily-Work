const {getNotes, saveNotes} = require('../services/notesService')

exports.getAllNotes = async(request, response) => {
    const notes = await getNotes();

    response.json(notes);
    // console.log(request.method);
};

exports.getNoteById = async (request, response) => {
    const notes = await getNotes();
    const note = notes.find(n => n.id == request.params.id);

    if(!note) response.status(404);

    response.json(note);
};

exports.createNote = async (request, response) => {
    const {title, content} = request.body;

    if(!title || !content) response.send(400).json({error: 'Title & contnt required'});

    const notes = await getNotes();

    const newNote = {
        id: Date.now(),
        title,
        content
    };

    notes.push(newNote);
    await saveNotes(notes);

    response.status(201).json(newNote);
};

exports.deleteNote = async (request, response) => {
    const notes = await getNotes();

    const note = notes.find(n => n.id === request.params.id);
    if(!note) response.status(404).end();

    const filtered = notes.filter(n => n.id != request.params.id);

    await saveNotes(filtered);
    response.json({message: 'Deleted'});
};