const express = require('express');
const app = express();

const notesRoutes = require('./routes/notesRoutes');
console.log('errrrrorrrr', notesRoutes);

app.use(express.json());
app.use('/notes', notesRoutes); //middleware
app.listen(3001, ()=>{
    console.log("Server Started");
})

module.exports = app;
