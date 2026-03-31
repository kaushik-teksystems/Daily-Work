const fs = require('fs').promises;
const FILE = './data/notes.json';

async function getNotes() {
    const data = await fs.readFile(FILE, 'utf-8');
    return JSON.parse(data);
}

async function saveNotes(notes) {
    await fs.writeFile(FILE, JSON.stringify(notes, null, 2));
}

module.exports = {getNotes, saveNotes};