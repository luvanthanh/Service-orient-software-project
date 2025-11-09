document.addEventListener("DOMContentLoaded", () => {
    const sumElementTotal = document.getElementById("sum_money_carts");
const userId = localStorage.getItem("userId");
console.log("User ID hiện tại:", userId);

if (!userId) {
alert("Bạn cần đăng nhập trước khi xem giỏ hàng!");
window.location.href = "LoginClient.html";
return;
}

fetch(`http://localhost:8084/CartDatabase/carts/findByUserId/${userId}`)
.then(response => {
    if (!response.ok) {
    throw new Error("Lấy giỏ hàng thất bại!");
    }
    return response.json();
})
.then(data => {
    console.log("xin chao");
    console.log("Dữ liệu giỏ hàng:", data);
    const listCartsDiv = document.getElementById("list_carts");
    if (!listCartsDiv) {
    console.error("Không tìm thấy phần tử #list_carts trong HTML!");
    return;
    }

    listCartsDiv.innerHTML = "";
    
    if (data.length === 0) {
    listCartsDiv.innerHTML = "<p>Giỏ hàng của bạn trống!</p>";
    return;
    }

    let tableHTML = `
        <table>
            <thead>
                <tr>
                    <th>Hình Ảnh</th>
                    <th>Tên Sản Phẩm</th>
                    <th>Giá Sản Phẩm</th>
                    <th>Số Lượng</th>
                    <th>Tổng Tiền</th>
                </tr>
            </thead>
            <tbody>
        
    `;
    let tongTien = 0;
    data.forEach((cart,index) => {
        
        let soluong = cart.cartQuantity;
        let giatri = cart.productPrice;
        let tong = soluong * giatri;
        tongTien = tongTien + tong;
        
        tableHTML += `
        <tr id="row-${index}">
            <td><img src="${cart.productImage}" alt="${cart.productName}" ></td>
            <td>${cart.productName}</td>
            <td id="price-${index}" data-price="${giatri}">${giatri.toLocaleString("vi-VN")} VND</td>

            <td>
                <button onclick="minus(${index})">-</button>
                <span id="quantity-${index}">${soluong}</span>
                <button onclick="plus(${index})">+</button>
            </td>
            <td id="total-${index}">${tong.toLocaleString()} VND</td>
            <td><button onclick="deleteCart(${index})">Xóa</button></td>
        </tr>
    `;
    });
    tableHTML += `
        </tbody>
        </table>
        <div class="sum-money" id="sum_money"> Tổng Tiền: ${tongTien.toLocaleString()} VND</div>
    `;
    listCartsDiv.innerHTML = tableHTML;
    window.cartData = data;
})
.catch(error => {
    console.error("Lỗi khi lấy giỏ hàng:", error);
});
});


function updateTotalSum() {
    let total = 0;
    const totalEls = document.querySelectorAll('[id^="total-"]'); // tất cả dòng có tổng tiền
    totalEls.forEach(el => {
        const value = Number(el.textContent.replace(/\D/g, "")); // loại bỏ chữ, chỉ lấy số
        total += value;
    });

    const sumMoneyEl = document.getElementById("sum_money");
    umElementTotal.textContent = tongTien.toLocaleString("vi-VN") + " VND";
    if (sumMoneyEl) {
        sumMoneyEl.textContent = "Tổng Tiền: " + total.toLocaleString("vi-VN") + " VND";

    }
}

function plus(index) {
    const quantityEl = document.getElementById(`quantity-${index}`);
    const priceEl = document.getElementById(`price-${index}`);
    const totalEl = document.getElementById(`total-${index}`);

    let quantity = parseInt(quantityEl.textContent);
    const price = Number(priceEl.textContent.replace(/\D/g, "")); // chỉ giữ lại số

    quantity++;
    quantityEl.textContent = quantity;
    totalEl.textContent = (price * quantity).toLocaleString("vi-VN") + " VND";

    updateTotalSum();
    }

function minus(index) {
    const quantityEl = document.getElementById(`quantity-${index}`);
    const priceEl = document.getElementById(`price-${index}`);
    const totalEl = document.getElementById(`total-${index}`);

    let quantity = parseInt(quantityEl.textContent);
    const price = Number(priceEl.textContent.replace(/\D/g, ""));

    if (quantity > 1) {
        quantity--;
        quantityEl.textContent = quantity;
        totalEl.textContent = (price * quantity).toLocaleString("vi-VN") + " VND";
    }

    updateTotalSum();
}

function deleteCart(index) {
    const cart = window.cartData[index]; // lấy cart tương ứng
    if (!cart) {
    console.error("Không tìm thấy cart tại index:", index);
    return;
    }

    const confirmDelete = confirm(`Bạn có chắc muốn xóa sản phẩm "${cart.productName}" khỏi giỏ hàng không?`);
    if (!confirmDelete) return;
    

fetch(`http://localhost:8084/CartDatabase/carts/${cart.cartId}`, {
    method: "DELETE",
    })
    .then(response => {
        if (!response.ok) {
        throw new Error("Xóa sản phẩm thất bại!");
        }
        console.log(`Đã xóa sản phẩm có ID: ${cart.cartId}`);

        // Xóa sản phẩm khỏi bảng trên giao diện
        const row = document.getElementById(`row-${index}`);
        if (row) row.remove();

        alert("Đã xóa sản phẩm khỏi giỏ hàng!");
    })
    .catch(error => {
        console.error("Lỗi khi xóa sản phẩm:", error);
        alert("Không thể xóa sản phẩm. Vui lòng thử lại!");
    });
    }



