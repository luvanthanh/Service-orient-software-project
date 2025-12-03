const userId = localStorage.getItem("userId");
if (!userId) {
    alert("Bạn cần đăng nhập để xem đơn hàng!");
    window.location.href = "LoginClient.html";
} else {
    fetch(`http://localhost:8888/api/orders/getOrderByUserId/${userId}`)
        .then(res => res.json())
        .then(orders => {
            const orderListDiv = document.getElementById("order_list");
            orderListDiv.innerHTML = ""; // xóa nội dung cũ nếu có

            if (orders.length === 0) {
                orderListDiv.innerHTML = "<p>Chưa có đơn hàng nào.</p>";
                return;
            }

            orders.forEach(order => {
                const orderDiv = document.createElement("div");
                orderDiv.style.border = "1px solid #ccc";
                orderDiv.style.padding = "10px";
                orderDiv.style.marginBottom = "10px";

                orderDiv.innerHTML = `
                <div class = "order-table">
                    <div class="order-id"><strong>Order ID:</strong> ${order.orderId}</div>
                    <div class="order-shopAddress" ><strong>Shop Address:</strong> ${order.shopAddress}</div>
                    <div class="order-userName"><strong>Customer Name:</strong> ${order.customerName}</div>
                    <div class="order-address"><strong>Delivery Address:</strong> ${order.deliveryAddress}</div>
                    <div class="order-"><strong>Phone:</strong> ${order.customerPhoneNumber}</div>
                    <div><strong>Note:</strong> ${order.note || "-"}</div>
                    <div><strong>Payment Method:</strong> ${order.paymentMethod || "-"}</div>
                    <div><strong>Total Money:</strong> ${order.totalMoney || 0} VND</div>
                </div>
                `;

                orderListDiv.appendChild(orderDiv);
            });
        })
        .catch(err => {
            console.error("Lỗi khi lấy đơn hàng:", err);
        });
}

