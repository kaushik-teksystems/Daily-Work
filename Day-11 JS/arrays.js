function printPyramid(){
    for(let i = 0; i < 4; i++){
        for(let j = 4; j > 0; j--){
            process.stdout.write("*"); 
        }
        console.log()
    }
}
printPyramid()


/*function filterDemo1(){
    let numbers = [1, 2, 3, 4, 5];
    const incremented = numbers.filter((value) => value % 2 == 0);
    console.log(incremented);
    console.log(numbers);
}
filterDemo1()*/




/*const callback = (value, index) => {
    console.log(value, index);
    return value +1;
}
function demo1(){
    let numbers = [1, 2, 3, 4, 5];
    const incremented = numbers.map(callback);
    console.log(Incremented);
    console.log(numbers);
    }

demo1()*/
