/* Object -> stringify -> Parse -> Modify -> Object
const user = {name: "John", age: 20};

let jsonStr = JSON.stringify(user);
let temp = JSON.parse(jsonStr);
temp.isAdult = temp.age >= 18;

let finalObj = temp;

console.log(finalObj);
*/

/*Using reduce funtion to count freq
const users = [
    { name: "A", role: "admin"},
    { name: "B", role: "user"},
    { name: "C", role: "admin"}
];

const freq = users.reduce((result, user) => {
    result[user.role] = (result[user.role] || 0) + 1;
    return result;
}, {});
console.log(freq);
*/

/* sorting, localeCompare -> compares strings alphabetically, acts as a tie breaker in this scenario
const users = [
    { name: "A", age: 20},
    { name: "B", age: 20},
    { name: "C", age: 18}
];

users.sort((a, b) => {
    if(a.age !== b.age) return a.age - b.age;
    return a.name.localeCompare(b.name);
});

console.log(users);
*/
// Grouping users as per role
/*
const users = [
    { name: "A", role: "admin", salary: 4000},
    { name: "B", role: "user", salary: 4000 },
    { name: "C", role: "user", salary: 5000 },
    { name: "D", role: "admin", salary: 5000.1}
];

function groupByRole(users) {
    const grouped = users.reduce((result, user) => {
        if (!result[user.role]) result[user.role] = [];
        result[user.role].push(user);
        return result;
    }, {});
    console.log(grouped);
}

function groupBySalary(users) {
    const groupedBySalary = users.reduce((result, user) => {
        if (!result[user.salary]) result[user.salary] = [];
        result[user.salary].push(user);
        return result;
    }, {});
    console.log(groupedBySalary);
}
groupBySalary(users);
*/

function diff(a, b){
    const result = {};

    for (let key in b){
        if (a[key] !== b[key]){
            result[key] = { old: a[key], new: b[key]};
        }
    }

    return result;
}

const a = {name: "A", age: 20};
const b = {name: "A", age: 21};

console.log(diff(a, b));