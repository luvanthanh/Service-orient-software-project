document.addEventListener("DOMContentLoaded", function () {
  const slides = document.querySelectorAll(".section-one-right .list-slides img");
  const slidesContainer = document.querySelector(".section-one-right .list-slides");
  const prevBtn = document.getElementById("prev-slide");
  const nextBtn = document.getElementById("next-slide");
  const checkoutBtn = document.querySelector(".checkout-btn");
  checkoutBtn.addEventListener("click", function(e) {
    console.log(e.target);
    e.target.add("active")});
  let currentIndex = 0;
  let intervalId;

  slidesContainer.style.width = `${slides.length * 1095}px`;

  function showSlide(index) {
    slidesContainer.style.transform = `translateX(-${index * 1090}px)`;
  }

  function nextSlide() {
    currentIndex = (currentIndex + 1) % slides.length;
    showSlide(currentIndex);
  }

  function prevSlide() {
    currentIndex = (currentIndex - 1 + slides.length) % slides.length;
    showSlide(currentIndex);
  }

  function startAutoSlide() {
    intervalId = setInterval(nextSlide, 2000);
  }

  function stopAutoSlide() {
    clearInterval(intervalId);
  }

  prevBtn.addEventListener("click", function () {
    stopAutoSlide();
    prevSlide();
    startAutoSlide();
  });

  nextBtn.addEventListener("click", function () {
    stopAutoSlide();
    nextSlide();
    startAutoSlide();
  });

  showSlide(currentIndex);
  startAutoSlide();
});
const params = new URLSearchParams(window.location.search);
const productId = params.get('id');

fetch(`http://localhost:8081/ProductDatabase/product/${productId}`)
  .then(res => {
    if (!res.ok) throw new Error("Không tìm thấy sản phẩm");
    return res.json();
  })
  .then(product => {
    const detailDiv = document.getElementById("Phone");
    detailDiv.innerHTML = `
      <div class="section-two>
          <div class= "image"> <img src="${product.productImageUrl}" alt="${product.productName}" style="width:300px;">
          <h2>${product.productName}</h2>
          </div>
          <div class="content-phone">
                <div class= "phone-screen"><b>Màn hình:</b> ${product.productScreenSize} inches</div>
                <div><b>RAM:</b> ${product.productRam} GB</div>
                <div><b>ROM:</b> ${product.productRom} GB</div>
                <div><b>Màu sắc:</b> ${product.productColor}</div>
                <div><b>Mô tả:</b> ${product.productDescription}</div>
                <div><b>Giá:</b> ${product.productFormattedPrice}₫</div>
            </div>
          </div>
    `;
  })
  .catch(err => {
    document.getElementById("Phone").textContent = err.message;
  });
