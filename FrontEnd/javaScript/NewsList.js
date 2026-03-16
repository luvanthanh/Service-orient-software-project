fetch(`http://localhost:8888/api/news`)
    .then(res => {
        if (!res.ok) throw new Error("Không thể tải danh sách tin tức");
        return res.json();
    })
    .then(newsList => {
        const newsListDiv = document.getElementById("news_list");
        newsListDiv.innerHTML = "";
        newsList.data.forEach(news => {
            newsListDiv.innerHTML += `
                <a href="News.html?id=${news.data.newsId}">
                    <div class="news-item">
                        <img src="${news.data.newsImage}" alt="${news.data.newsTitle}" class="news-item-image">
                        <div class= "news-item-content">
                            <p><b>Ngày đăng:</b> ${news.data.newsDate}</p>
                            <p>-${news.data.newsContent.substring(0, 200)}...</p>
                            <p>-${news.data.newsContent1.substring(0, 200)}...</p>
                            <p>-${news.data.newsContent2.substring(0, 200)}...</p>
                        </div>
                    </div>
                </a>
            `;
        });
    })
    .catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));