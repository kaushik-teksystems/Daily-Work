const {describe, it, xit} = require('mocha');
const app = require('../server');
const request = require('supertest');
const {expect} = require('chai');

describe('GET /notes', () => {
    
    xit('should return all notes123', async () => {
        console.log(request);

        const response = await request(app).get('/notes/');
        expect(response.status).to.equal(200);
        expect(response.body).to.be.an('array');
    });

    xit('should return 404 for non existent noteID', async () => {
        console.log(request);

        const response = await request(app).get('/notes/123');
        expect(response.status).to.equal(404);
    });

    xit('test create new note', async () => {
        const response = await request(app).post('/notes/').send({'title': 'task1', 'content': 'Adding a new note'});
        expect(response.status).to.equal(201);
    });

    xit('should return Bad Request When title & content are invalid', async () => {
        const response = await request(app).post('/notes/').send({'title': '', 'content':''});
        expect(response.status).to.equal(400);
    });

    it('test should return  Bad Request when title is empty string', async() => {
        const response = await request(app).post('/notes/').send({'title':'', 'content':'Testing POST req when title is empty'});
        expect(response.status).to.equal(400);
    })

    it('test should return Bad Request with status 400 when content is empty string', async() => {
        const response = await request(app).post('/notes/').send({'title':'Kaushik', 'content':''});
        expect(response.status).to.equal(400);
    })

    xit('test successful deletetion', async() => {
        const response = await request(app).delete('/notes/1114');
        expect(response.status).to.equal(200);
    });

    it('test should return 404 when note is missing with the given id', async() => {
        const response = await request(app).delete('/notes/123');
        expect(response.status).to.equal(404);
    })

})