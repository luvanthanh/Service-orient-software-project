fetch("http://localhost:8083/NewsDatabase/news")
    .then(response => response.json())
    .then(news => {
        let newsSection = document.getElementById("section_one_news");
        newsSection.innerHTML = " ";
        news.slice(0,3).forEach(item =>{
            newsSection.innerHTML += `
                <a href = "News.html?id=${item.newsId}"><img src="${item.newsImage}" alt="${item.newsTitle}" class="news-image"> </a>
            `;
        });
    })
    .catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));