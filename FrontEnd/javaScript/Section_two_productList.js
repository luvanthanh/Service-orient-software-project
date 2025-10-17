// Javascript lấy dữ liệu từ API và hiển thị sản phẩm

fetch("http://localhost:8081/ProductDatabase/products")
.then(response => response.json())
.then(products => {
    const listContainer = document.getElementById("list-products");
    const prevBtn = document.getElementById('prev-page');
    const nextBtn = document.getElementById('next-page');
    const pageNumbers = document.getElementById('page-numbers');
    const productsPerPage = 8;
    let currentPage = 1;
    let filteredProducts = products;

    function renderProducts(page, data = filteredProducts) {
        listContainer.innerHTML = "";
        const totalPages = Math.ceil(data.length / productsPerPage);
        const start = (page - 1) * productsPerPage;
        const end = start + productsPerPage;
        const productsToShow = data.slice(start, end);

        productsToShow.forEach(product => {
            const productDiv = document.createElement("div");
            productDiv.classList.add("product");
            productDiv.innerHTML = `
                <a href="Phone.html?id=${product.productId}">
                    <img src="${product.productImageUrl}" alt="${product.productName}">
                    <div class="product-name">${product.productName}</div>
                    <div class="configuration-product">
                        <span class="configuration-product-button">${product.productScreenSize} inches</span>
                        <span class="configuration-product-button">${product.productRam} GB</span>
                        <span class="configuration-product-button">${product.productRom} GB</span>
                    </div>
                    <div class="describe-product">${product.productDescription}</div>
                    <div class="price-product">${product.productFormattedPrice}₫</div>
                </a>
                <button class="product-phone">Xem thêm</button>
            `;
            const btn = productDiv.querySelector('.product-phone');
            btn.addEventListener('click', function() {
                window.location.href = `Phone.html?id=${product.productId}`;
            });
            listContainer.appendChild(productDiv);
        });
        renderPageNumbers(totalPages);
        updateBtnState(totalPages);
    }

    function renderPageNumbers(totalPages) {
        pageNumbers.innerHTML = '';
        for (let i = 1; i <= totalPages; i++) {
            const btn = document.createElement('button');
            btn.textContent = i;
            btn.className = (i === currentPage) ? 'active-page' : '';
            btn.addEventListener('click', function () {
                currentPage = i;
                renderProducts(currentPage);
            });
            pageNumbers.appendChild(btn);
        }
    }

    function updateBtnState(totalPages) {
        prevBtn.disabled = currentPage === 1;
        nextBtn.disabled = currentPage === totalPages;
    }

    prevBtn.addEventListener('click', function () {
        if (currentPage > 1) {
            currentPage--;
            renderProducts(currentPage);
        }
    });

    nextBtn.addEventListener('click', function () {
        const totalPages = Math.ceil(filteredProducts.length / productsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            renderProducts(currentPage);
        }
    });

    // Lọc theo hãng
    const brandLinks = document.querySelectorAll('.top-list-box');
    brandLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const brand = link.textContent.trim().toLowerCase();
            if (brand === 'all') {
                filteredProducts = products;
            } else {
                filteredProducts = products.filter(p => p.productBrand && p.productBrand.toLowerCase() === brand);
            }
            currentPage = 1;
            renderProducts(currentPage, filteredProducts);
        });
    });

    // Khởi tạo lần đầu
    renderProducts(currentPage);
})
.catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));