let currentIndex = 0;
const slides = document.querySelectorAll(".slides img");
const names = document.querySelectorAll(".slide-names span");

function showSlide(index) {
  slides.forEach((slide, i) => {
    slide.classList.toggle("active", i === index);
    names[i].classList.toggle("active", i === index);
  });
}

function nextSlide() {
  currentIndex = (currentIndex + 1) % slides.length;
  showSlide(currentIndex);
}

// Tự động đổi slide mỗi 2 giây
setInterval(nextSlide, 2000);

// Hiển thị slide đầu tiên khi load
showSlide(currentIndex);

// Cho phép click vào tên để đổi slide
names.forEach((name, i) => {
  name.addEventListener("click", () => {
    currentIndex = i;
    showSlide(currentIndex);
  });
});
