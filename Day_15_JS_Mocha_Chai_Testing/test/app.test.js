const {describe, it} = require('mocha');
const add = require('../app');

const {expect} = require('chai');

describe ('testing.maths.operations', () => {
    beforeEach(() => {
        console.log('before each');
    })

    it('normal.add.positives', () => {
        const result = add(2,3); //both Arrange & Act
        expect(result).to.equal(5);
    })
    it('normal.add.negatives', () => {
        const result1 = add(-2, -3);
        expect(result1).to.equal(-5);
    })
});

