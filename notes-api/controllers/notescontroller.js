const { getNotes, saveNotes } = require('../services/notesService')

exports.getAllNotes = async (request, response) => {
    const notes = await getNotes();

    response.json(notes);
    // console.log(request.method);
};

exports.getNoteById = async (request, response) => {
    const notes = await getNotes();
    const note = notes.find(n => n.id == request.params.id);

    if (!note) response.status(404);

    response.json(note);
};

exports.createNote = async (request, response) => {
    const { title, content } = request.body;

    if (!title || !content) return response.status(400).json({ error: 'Title & contnt required' });

    const notes = await getNotes();

    const newNote = {
        id: Date.now(),
        title,
        content,
        status: "created",
        createdAt: new Date().toLocaleString('en-IN', { timeZone: 'Asia/Kolkata' })
    };

    notes.push(newNote);
    await saveNotes(notes);

    response.status(201).json(newNote);
};

exports.deleteNote = async (request, response) => {
    const notes = await getNotes();

    const note = notes.find(n => n.id == request.params.id);
    if (!note) return response.status(404).end();

    const filtered = notes.filter(n => n.id != request.params.id);

    await saveNotes(filtered);
    response.json({ message: 'Deleted' });
};

exports.updateNote = async (request, response) => {
    if (request.body.hasOwnProperty("createdAt")) {
        return response.status(400).json({
            error: "createdAt is a read-only property, and can't be overwritten"
        });
    }

    const notes = await getNotes();

    const existingNote = notes.find(n => String(n.id) === String(request.params.id));
    if (!existingNote) return response.status(404).end();

    const currentStatus = (existingNote.status || "").toString().trim().toLowerCase();
    if (currentStatus === 'closed') {
        return response.status(400).json({ error: "Can't update a closed note, try adding a seprarate note." });
    }

    const updated = notes.map(n =>
        n.id == request.params.id ? { ...n, ...request.body } : n
    );

    await saveNotes(updated);
    response.json({ message: 'updated' });
};
