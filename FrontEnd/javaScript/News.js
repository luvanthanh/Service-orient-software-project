const params = new URLSearchParams(window.location.search);
const newsId = params.get('id');


fetch(`http://localhost:8083/NewsDatabase/news/${newsId}`)
    .then(res => {
        if (!res.ok) throw new Error("Không tìm thấy tin tức");
        return res.json();
    })
    .then(news => {
        const newsDiv = document.getElementById("section_two_content");
        newsDiv.innerHTML = " ";
        newsDiv.innerHTML = `
            <div class="news-detail">
                <div class="news-detail-content">
                    <h2>${news.newsTitle}</h2>
                    <div><b>Ngày đăng:</b> ${news.newsDate}</div>
                    <a href="javascript:void(0)" onclick="window.location.href='../html/Product.html?id=3'">
                        <img src="${news.newsImage}" alt="${news.newsTitle}" class="news-detail-image">
                    </a>
                    <p>${news.newsContent}</p>
                </div>

                <div class="news-detail-content">
                    <img src="${news.newsImage1}" alt="${news.newsTitle1}" class="news-detail-image">
                    <p>${news.newsContent1}</p>
                </div>

                <div class="news-detail-content">
                    <img src="${news.newsImage2}" alt="${news.newsTitle2}" class="news-detail-image">
                    <p>${news.newsContent2}</p>
                </div>
                <div class="news-detail-content">
                    <img src="${news.newsImage3}" alt="${news.newsTitle3}" class="news-detail-image">
                    <p>${news.newsContent3}</p>
                </div>

            </div>
        `;
    })
    .catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));