const userId = localStorage.getItem("userId")
fetch(`http://localhost:8082/UserDatabase/users/${userId}`)
    .then(respons)