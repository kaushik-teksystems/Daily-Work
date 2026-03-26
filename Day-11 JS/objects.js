const users=[
    {"id":1,"user":'A', "active":true},
    {"id":2,"user":'B', "active":false},
    {"id":3,"user":'C', "active":true}
];
users.forEach((user) => {
    user.active = !user.active;
});
console.log(users);

let count=0;
console.log(users[0].name);
function CountActive(){
for(let i=0;i<users.length;i++){
    if(users[i].active==true)
        {
            count+=1;
        }
}
return count;
}
console.log(CountActive());