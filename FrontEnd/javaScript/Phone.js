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

fetch(`http://localhost:8080/MyPhoneStore/phones/${productId}`)
  .then(res => {
    if (!res.ok) throw new Error("Không tìm thấy sản phẩm");
    return res.json();
  })
  .then(product => {
    const detailDiv = document.getElementById("Phone");
    detailDiv.innerHTML = `
      <h2>${product.productName}</h2>
      <img src="${product.imageUrl}" alt="${product.productName}" style="width:300px;">
      <div><b>Màn hình:</b> ${product.screenSize} inches</div>
      <div><b>RAM:</b> ${product.ram} GB</div>
      <div><b>ROM:</b> ${product.rom} GB</div>
      <div><b>Màu sắc:</b> ${product.color}</div>
      <div><b>Mô tả:</b> ${product.description}</div>
      <div><b>Giá:</b> ${product.formattedPrice}₫</div>
    `;
  })
  .catch(err => {
    document.getElementById("Phone").textContent = err.message;
  });
