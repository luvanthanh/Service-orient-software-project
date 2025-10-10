// Javascript lấy dữ liệu từ API và hiển thị sản phẩm

fetch("http://localhost:8080/MyPhoneStore/phones")
.then(response => response.json())
.then(products => {
    const listContainer = document.getElementById("list-products");
    const prevBtn = document.getElementById('prev-page');
    const nextBtn = document.getElementById('next-page');
    const pageNumbers = document.getElementById('page-numbers');
    const buttons = document.querySelectorAll('.top-list-box'); // 👈 lấy các nút brand

    const productsPerPage = 8;
    let currentPage = 1;
    let filteredProducts = products; // 👈 mặc định hiển thị tất cả

    // ================== HÀM HIỂN THỊ SẢN PHẨM ==================
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
                <a href="Phone.html?id=${product.id}">
                    <img src="${product.imageUrl}" alt="${product.productName}">
                    <div class="product-name">${product.productName}</div>
                    <div class="configuration-product">
                        <span class="configuration-product-button">${product.screenSize} inches</span>
                        <span class="configuration-product-button">${product.ram} GB</span>
                        <span class="configuration-product-button">${product.rom} GB</span>
                    </div>
                    <div class="describe-product">${product.description}</div>
                    <div class="price-product">${product.formattedPrice}₫</div>
                </a>
                <button class="product-phone">Xem thêm</button>
            `;
            const btn = productDiv.querySelector('.product-phone');
            btn.addEventListener('click', function() {
                window.location.href = `Phone.html?id=${product.id}`;
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

    // ================== XỬ LÝ LỌC THEO BRAND ==================
    buttons.forEach(btn => {
        btn.addEventListener('click', () => {
            const brand = btn.textContent.trim();

            // Nếu là ALL thì hiển thị toàn bộ
            if (brand === "ALL") {
                filteredProducts = products;
            } else {
                // Lọc theo brand (so sánh không phân biệt hoa thường)
                filteredProducts = products.filter(p =>
                    p.brand.toLowerCase() === brand.toLowerCase()
                );
            }

            // Reset lại về trang 1 sau khi lọc
            currentPage = 1;
            renderProducts(currentPage);
        });
    });

    // ================== KHỞI TẠO LẦN ĐẦU ==================
    renderProducts(currentPage);
})
.catch(error => console.error("Lỗi khi load dữ liệu từ API:", error));



// Lọc theo RAM
  document.querySelectorAll("#ram-filter li").forEach(item => {
    item.addEventListener("click", () => {
      // Lấy giá trị RAM (vd: "4 GB" -> 4)
      const ramValue = item.textContent.replace(" GB", "").trim();

      // Gọi API
      fetch(`http://localhost:8080/MyPhoneStore/phones?ram=${ramValue}`)
        .then(res => res.json())
        .then(products => {
          const listDiv = document.getElementById("product-list");
          listDiv.innerHTML = "";

          if (products.length === 0) {
            listDiv.innerHTML = "<p>Không có sản phẩm nào phù hợp.</p>";
            return;
          }

          // Hiển thị danh sách sản phẩm
          products.forEach(p => {
            listDiv.innerHTML += `
              <div class="product-card">
                <img src="${p.imageUrl}" alt="${p.productName}">
                <h3>${p.productName}</h3>
                <p>RAM: ${p.ram} GB</p>
                <p>Giá: ${p.formattedPrice}</p>
              </div>
            `;
          });
        })
        .catch(err => console.error("Lỗi khi gọi API:", err));
    });
  });

