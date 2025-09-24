document.addEventListener("DOMContentLoaded", function () {
const products = document.querySelectorAll('.product');
const prevBtn = document.getElementById('prev-page');
const nextBtn = document.getElementById('next-page');
const pageNumbers = document.getElementById('page-numbers');
const productsPerPage = 15;
let currentPage = 1;
const totalPages = Math.ceil(products.length / productsPerPage);

function showPage(page) {
    products.forEach((product, i) => {
      product.style.display = (i >= (page - 1) * productsPerPage && i < page * productsPerPage) ? 'block' : 'none';
    });
    renderPageNumbers();
    prevBtn.disabled = page === 1;
    nextBtn.disabled = page === totalPages;
}

function renderPageNumbers() {
    pageNumbers.innerHTML = '';
    for (let i = 1; i <= totalPages; i++) {
    const btn = document.createElement('button');
    btn.textContent = i;
    btn.className = (i === currentPage) ? 'active-page' : '';
    btn.addEventListener('click', function () {
        currentPage = i;
        showPage(currentPage);
    });
    pageNumbers.appendChild(btn);
    }
}

prevBtn.addEventListener('click', function () {
    if (currentPage > 1) {
    currentPage--;
    showPage(currentPage);
    }});

nextBtn.addEventListener('click', function () {
    if (currentPage < totalPages) {
    currentPage++;
    showPage(currentPage);
    }
});

showPage(currentPage);
});