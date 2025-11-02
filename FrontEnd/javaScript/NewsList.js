fetch(`http://localhost:8083/NewsDatabase/news`)
    .then(res => {
        if (!res.ok) throw new Error("Không thể tải danh sách tin tức");
        return res.json();
    })
    .then(newsList => {
        const newsListDiv = document.getElementById("news_list");
        newsListDiv.innerHTML = "";
        newsList.forEach(news => {
            newsListDiv.innerHTML += `
                <div class="news-item">
                    <a href="News.html?id=${news.newsId}"></a>
                    <div class="news-item-links">
                        <div class= "news-item-links-product"><a href="Phone.html?id=${news.newsProductId}"> Xem thông tin sản phẩm chi tiết</a> </div>
                        <div class ="news-item-links-news"> <a href="News.html?id=${news.newsId}"> Xem thông tin sản phẩm chi tiết</a> </div>
                    </div>
                    <img src="${news.newsImage}" alt="${news.newsTitle}" class="news-item-image">
                    <div class= "news-item-content">
                        <p><b>Ngày đăng:</b> ${news.newsDate}</p>
                        <p>${news.newsContent.substring(0, 200)}...</p>
                    </div>
                </div>
            `;
        });
    })
    .catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));