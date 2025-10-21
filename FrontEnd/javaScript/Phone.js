
const params = new URLSearchParams(window.location.search);
const productId = params.get('id');


fetch(`http://localhost:8081/ProductDatabase/products/getProductById/${productId}`)
  .then(res => {
    if (!res.ok) throw new Error("Không tìm thấy sản phẩm");
    return res.json();
  })
  .then(product => {
    const detailDiv = document.getElementById("Phone");
    detailDiv.innerHTML = `
    
          
          <div class="section-two">
                <div class = "list-image">
                    <div class="main-image" id="mainImage"> <img src="${product.productImageUrl}" alt="${product.productName}"></div>
                    <div class="list-image-box">
                        <div class= "image" id="image0" onclick="changeMainImage('${product.productImageUrl}')"> <img src="${product.productImageUrl}" alt="${product.productName}"></div>
                        <div class="image" id="image1" onclick="changeMainImage('${product.productImageUrl1}')"> <img src="${product.productImageUrl1}" alt="${product.productName}" ></div>
                        <div class="image" id="image2" onclick="changeMainImage('${product.productImageUrl2}')"> <img src="${product.productImageUrl2}" alt="${product.productName}" ></div>
                        <div class="image" id="image3" onclick="changeMainImage('${product.productImageUrl3}')"> <img src="${product.productImageUrl3}" alt="${product.productName}" ></div>
                    </div>
                </div>

                <div class="content-phone">
                      
                      <div class="content-phone-right">
                          <h2>${product.productName}</h2>
                          <div> <b> Hãng Phát Hành:</b> ${product.productBrand}</div>
                          <div><b>Thời Gian Phát Hành :</b> ${product.productReleaseDate} </div>
                          <div><b>Màn hình:</b> ${product.productScreenSize} inches</div>
                          <div><b>Số Lượng Kho:</b> ${product.productStockQuantity} Chiếc</div>
                          <div><b>RAM:</b> ${product.productRam} Gigabyte</div>
                          <div><b>ROM:</b> ${product.productRom} Gigabyte</div>
                          <div><b>Màu sắc:</b> ${product.productColor} màu sắc độc đáo</div>
                          <div><b>Thời Gian Bảo Hành:</b> ${product.productWarranty}</div>
                          <div><b>Mô tả:</b> ${product.productDescription}</div>
                          <div><b>Giá:</b> ${product.productFormattedPrice}₫</div>
                      </div>
                </div>
          </div>

    `;
    const mainImage = document.getElementById('mainImage');

    // Hàm cần là global nếu bạn vẫn dùng onclick inline — gán vào window
    window.changeMainImage = function(imageUrl) {
      if (!mainImage) return;
      const img = mainImage.querySelector('img');
      if (img) img.src = imageUrl;
    };
  })
  
  .catch(err => {
    document.getElementById("Phone").textContent = err.message;
  });



  

