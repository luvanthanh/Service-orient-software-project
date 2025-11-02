
  // Khi trang Home load xong
  const username = localStorage.getItem("username"); // Lấy username đã lưu khi login

if (username) {
    // Nếu có username thì đổi nút đăng nhập thành tên người dùng
    const loginButton = document.getElementById("login_button");
    loginButton.innerHTML = `<i class="fa fa-user"></i> ${username}`;
    
    // Xóa đường link dẫn đến trang login
    const loginLink = loginButton.closest("a");
    loginLink.removeAttribute("href");

    // Nếu muốn, bạn có thể thêm menu dropdown hoặc nút "Đăng xuất"
    // Ví dụ:
    // loginButton.addEventListener("click", () => {
    //   localStorage.clear();
    //   window.location.reload();
    // });
}
