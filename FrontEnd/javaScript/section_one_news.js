fetch("http://localhost:8083/NewsDatabase/news")
    .then(response => response.json)
    .then(news => {
        

    })
    .catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));