async function login(event) {
      event.preventDefault();

      const user = document.getElementById("username").value;
      const pass = document.getElementById("password").value;
      const error = document.getElementById("error-msg");

      try {
        const response = await fetch("http://localhost:8080/Demo/auth/token", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({ username: user, password: pass })
        });

        if (response.ok) {
          // ✅ đăng nhập thành công
          window.location.href = "Home.html";
        } else {
          // ❌ sai tài khoản hoặc mật khẩu
          error.textContent = "Sai tài khoản hoặc mật khẩu!";
          error.style.color = "red";
        }
      } catch (err) {
        console.error("Lỗi:", err);
        error.textContent = "Không kết nối được đến server!";
        error.style.color = "red";
      }
    }