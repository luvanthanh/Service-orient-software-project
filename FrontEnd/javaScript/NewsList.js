fetch(`http://localhost:8888/api/news`)
    .then(res => {
        if (!res.ok) throw new Error("Không thể tải danh sách tin tức");
        return res.json();
    })
    .then(newsList => {
        const newsListDiv = document.getElementById("news_list");
        newsListDiv.innerHTML = "";
        newsList.forEach(news => {
            newsListDiv.innerHTML += `
                <a href="News.html?id=${news.newsId}">
                    <div class="news-item">
                        <img src="${news.newsImage}" alt="${news.newsTitle}" class="news-item-image">
                        <div class= "news-item-content">
                            <p><b>Ngày đăng:</b> ${news.newsDate}</p>
                            <p>-${news.newsContent.substring(0, 200)}...</p>
                            <p>-${news.newsContent1.substring(0, 200)}...</p>
                            <p>-${news.newsContent2.substring(0, 200)}...</p>
                        </div>
                    </div>
                </a>
            `;
        });
    })
    .catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));