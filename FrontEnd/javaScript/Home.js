
document.addEventListener("DOMContentLoaded", () => {
  const usernameStored = localStorage.getItem("username");

  if (usernameStored) {
    const loginButton = document.getElementById("login_button");

    if (loginButton) {
      const icon = loginButton.querySelector(".fa-user");
      const textNode = icon.nextSibling; // lấy phần chữ sau icon

      if (textNode && textNode.nodeType === Node.TEXT_NODE) {
        textNode.textContent = " " + usernameStored; // thay "Tài khoản" bằng tên
      }
    }
  }
});
