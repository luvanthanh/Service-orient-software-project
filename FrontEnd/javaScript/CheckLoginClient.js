function login(event) {
  event.preventDefault(); // Ngăn form reload lại trang

      const username = document.getElementById("username").value;
      const password = document.getElementById("password").value;
      const errorMsg = document.getElementById("error-msg");

  errorMsg.textContent = ""; // chỗ này cần tìm hiểu

  if (username == "" || password=="") {
    errorMsg.textContent = "Vui lòng nhập đầy đủ thông tin!";
  }

  fetch("http://localhost:8082/UserDatabase/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json" // chỗ này cần tìm hiểu
    },
    body: JSON.stringify({ //chỗ này cần tìm hiểu
      userName: username,
      password: password
    })
  })

    .then(response => {
      if (!response.ok) {
        console.log("Đăng nhập thất bại ở bước xác thực userName và password");
      }
      return response.json();
    })

    .then(data => {
      console.log("Login success:", data);

      if (data.data.token && data.data.checkLogin) {
        localStorage.setItem("token",data.data.token);
        localStorage.setItem("username", username);
        localStorage.setItem("userId", data.data.userId);

        console.log("Token lưu trong localStorage:", data.data.token , username);
        
        alert("Đăng nhập thành công!");

        window.location.href = "Home.html";
      } else {
        errorMsg.textContent = "Đăng nhập thất bại! Vui lòng kiểm tra lại tên đăng nhập và mật khẩu.";
      }
    })
    .catch(error => {
      console.error("Lỗi khi đăng nhập:", error);
      errorMsg.textContent = error.message || "Không thể kết nối đến server!";
    });
}
