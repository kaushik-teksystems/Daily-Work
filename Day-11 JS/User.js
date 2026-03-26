/*class User{
    name = "Kaushik"
}
const u1 = new User();
console.log(u1.name);

let i = true;
let st = i.toString();
*/


const user1 = {
    name: 'kaushik',
    age: 10,
    address:{
        house: 75,
        street: 'kayyar',
        pin: 567878
    }
}
console.log(user1.name);
console.log(user1['name']);
console.log(user1.address.pin);