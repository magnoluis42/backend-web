function request(data, action, url) {
    return new Promise(function (response) {
        let request = new XMLHttpRequest();
        let json = JSON.stringify(data);

        request.open(action, url, true);
        request.setRequestHeader('Content-type', 'application/json');

        request.onload = () => response(request);
        request.onerror = () => response(request);

        if ((action == 'POST' || action == 'PUT') && data) {
            request.send(json);
        } else {
            request.send();
        }
    });
}
document.querySelector('#submitButton').addEventListener('click', (e) => {

    e.preventDefault();


    let send = {
        name: document.querySelector('#name').value,
        email: document.querySelector('#email').value,
        password: document.querySelector('#password').value
    };

    request(send, 'POST', 'http://localhost:8080/user/save').then((requestUser) => {

        let response = requestUser.response;
        console.log(response)
    });

});