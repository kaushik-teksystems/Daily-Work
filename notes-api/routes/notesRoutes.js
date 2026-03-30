const express = require('express');
const router = express.Router();

const controller = require('../controllers/notescontroler');

router.get('/', controller.getAllNotes);

module.exports = router;