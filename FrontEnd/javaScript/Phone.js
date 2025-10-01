document.addEventListener("DOMContentLoaded", function () {
  const slides = document.querySelectorAll(".section-one-right .list-slides img");
  const slidesContainer = document.querySelector(".section-one-right .list-slides");
  const prevBtn = document.getElementById("prev-slide");
  const nextBtn = document.getElementById("next-slide");
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

fetch("../FakeJson/product.json")
  .then(res => res.json())
  .then(products => {
    const product = products.find(p => String(p.id) === String(productId));
    const detailDiv = document.getElementById("Phone");
    if (product) {
      detailDiv.innerHTML = `
        <h2>${product.productName}</h2>
        <img src="${product.imageUrl}" alt="${product.productName}" style="width:300px;">
        <div><b>Màn hình:</b> ${product.screenSize} inches</div>
        <div><b>RAM:</b> ${product.ram} GB</div>
        <div><b>ROM:</b> ${product.rom} GB</div>
        <div><b>Mô tả:</b> ${product.description}</div>
        <div><b>Giá:</b> ${product.formattedPrice}₫</div>
      `;
    } else {
      detailDiv.textContent = "Không tìm thấy sản phẩm!";
    }
  });
