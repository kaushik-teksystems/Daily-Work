const addAsArrow=(numbers)=>{
    let sum=0;
    for(let i=0;i<numbers.length;i++){
        const element=numbers[i];
        sum+=element;
    }
    return sum;
}

console.log(addAsArrow([1,2,3]));
 



// const result = add(3, 4, 6, 6);
// console.log(result);
// function add(a, b){
// //     let sum = 0;
//     for(let index = 0; index < arguments.length; index++){
//         const element = arguments[index];
//         sum+=element;
//     }
//     return sum
// }
// const result = add(3, 4, 6, 6);
// console.log(result);
