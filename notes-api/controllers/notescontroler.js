exports.getAllNotes = async(Request, response) => {
    console.log(request.method);

    response.send(200);
}