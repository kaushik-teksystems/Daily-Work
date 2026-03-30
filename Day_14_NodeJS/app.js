const fs = require('fs').promises;

//ReadFileWithPromis
// fs.readFile('data.txt', 'utf8')
//     .then(data => console.log(data))
//     .catch(err => console.error(err));

async function readFile(){
    try {
        const data = await fs.readFile('data.txt', 'utf8');
        console.log(data);
    } catch (error) {
        console.error(err);
    }
}

async function writeFile() {
    try {
        await fs.writeFile('data.txt', 'Async Await Example');
    } catch (err) {
        console.error(err);
    }
}

async function appendFile() {
    await fs.appendFile('data.txt', '\nAppending...');
}

async function deleteFile() {
    await fs.unlink('data.txt');
    
}

// readFile();
// writeFile();
// appendFile();
