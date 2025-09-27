// JavaScript to load products from JSON and display them

fetch("../FakeJson/product.json")
.then(response => response.json())
.then(products => {
const listContainer = document.getElementById("list-products");
const prevBtn = document.getElementById('prev-page');
const nextBtn = document.getElementById('next-page');
const pageNumbers = document.getElementById('page-numbers');
const productsPerPage = 10;
let currentPage = 1;
const totalPages = Math.ceil(products.length / productsPerPage);

function renderProducts(page) {
    listContainer.innerHTML = "";
    const start = (page - 1) * productsPerPage;
    const end = start + productsPerPage;
    const productsToShow = products.slice(start, end);

    productsToShow.forEach(product => {
    const productDiv = document.createElement("div");
    productDiv.classList.add("product");
    productDiv.innerHTML = `
        <a href="${product.link}">
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
    listContainer.appendChild(productDiv);
    });
}

function renderPageNumbers() {
    pageNumbers.innerHTML = '';
    for (let i = 1; i <= totalPages; i++) {
    const btn = document.createElement('button');
    btn.textContent = i;
    btn.className = (i === currentPage) ? 'active-page' : '';
    btn.addEventListener('click', function () {
        currentPage = i;
        renderProducts(currentPage);
        renderPageNumbers();
        updateBtnState();
    });
    pageNumbers.appendChild(btn);
    }
}

function updateBtnState() {
    prevBtn.disabled = currentPage === 1;
    nextBtn.disabled = currentPage === totalPages;
}

prevBtn.addEventListener('click', function () {
    if (currentPage > 1) {
    currentPage--;
    renderProducts(currentPage);
    renderPageNumbers();
    updateBtnState();
    }
});

nextBtn.addEventListener('click', function () {
    if (currentPage < totalPages) {
    currentPage++;
    renderProducts(currentPage);
    renderPageNumbers();
    updateBtnState();
    }
});

// Khởi tạo lần đầu
renderProducts(currentPage);
renderPageNumbers();
updateBtnState();
})
.catch(error => console.error("Lỗi khi load JSON:", error));