
fetch(`http://localhost:8888/api/products`)
    .then(response => response.json())
    .then(products => {
        const listContainer = document.getElementById("list-products");
        const prevBtn = document.getElementById('prev-page');
        const nextBtn = document.getElementById('next-page');
        const pageNumbers = document.getElementById('page-numbers');
        const productsPerPage = 8;
        let currentPage = 1;
        let filteredProducts = products;

        // ⚙️ HÀM HIỂN THỊ SẢN PHẨM
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

                // nút xem thêm
                const btn = productDiv.querySelector('.product-phone');
                btn.addEventListener('click', function() {
                    window.location.href = `Phone.html?id=${product.productId}`;
                });
                listContainer.appendChild(productDiv);
            });

            renderPageNumbers(totalPages);
            updateBtnState(totalPages);
        }

        //  HÀM PHÂN TRANG
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

        //  ĐƯA filterByBrand RA NGOÀI (toàn cục)
        window.filterByBrand = function(brand) {
            if (brand === 'all') {
                filteredProducts = products;
                currentPage = 1;
                renderProducts(currentPage, filteredProducts);
            } else {
                fetch(`http://localhost:8888/api/products/getProductByBrand/${brand}`)
                    .then(response => response.json())
                    .then(data => {
                        filteredProducts = data;
                        renderProducts(currentPage = 1, filteredProducts);
                    })
                    .catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));
            }
        };

        // lọc theo giá tiền khoản min đến max
        window.filterByPrice = function(priceMin, priceMax) {
            fetch(`http://localhost:8888/api/products/getProductByPrice?min=${priceMin}&max=${priceMax}`)
                .then(response => response.json())
                .then(data => {
                    filteredProducts = data;
                    renderProducts(currentPage = 1, filteredProducts);
                })
                .catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));
            
        };


        // lọc theo bộ nhớ trong (Ram)
        window.filterByRam = function(ram) {
            fetch(`http://localhost:8888/api/products/getProductByRam/${ram}`)
                .then(response => response.json())
                .then(data => {
                    filteredProducts = data;
                    renderProducts(currentPage = 1, filteredProducts);
                })
                .catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));
            
        };
// lọc sản phẩm theo rom
        window.filterByRom =function(rom){
            fetch(`http://localhost:8888/api/products/getProductByRom/${rom}`)
                .then(response => response.json())
                .then(data => {
                    filteredProducts = data;
                    renderProducts(currentPage = 1, filteredProducts);
                })
                .catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));
        }
// locj san pham theo creen size
        window.filterByScreenSize=function(min, max){
            fetch(`http://localhost:8888/api/products/getProductByScreenSize?min=${min}&max=${max}`)
                .then(response => response.json())
                .then(data =>{
                    filteredProducts = data;
                    renderProducts(currentPage = 1, filteredProducts);
                })
                .catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));
        }
// lọc sản phẩm theo màu sắc
        window.filterByColor=function(color){
            fetch(`http://localhost:8888/api/products/getProductByColor/${color}`)
                .then(response => response.json())
                .then(data =>{
                    filteredProducts = data;
                    renderProducts(currentPage = 1, filteredProducts);
                })
                .catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));
        }

        // 🚀 Khởi tạo lần đầu
        renderProducts(currentPage);
    })
    .catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));
