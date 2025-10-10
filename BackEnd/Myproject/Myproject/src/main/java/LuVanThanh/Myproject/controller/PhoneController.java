package LuVanThanh.Myproject.controller;


import LuVanThanh.Myproject.entity.Phone;
import LuVanThanh.Myproject.request.phoneRequest.PhoneBetweenPrice;
import LuVanThanh.Myproject.request.phoneRequest.PhoneBetweenSreenSize;
import LuVanThanh.Myproject.request.phoneRequest.PhoneCreationRequest;
import LuVanThanh.Myproject.request.phoneRequest.PhoneUpdateRequest;
import LuVanThanh.Myproject.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/phones")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

//    lấy tất cả danh sách sản phẩm
    @GetMapping
    public List<Phone> getAllPhones() {
        return phoneService.getAllPhone();
    }


// lấy sản phẩm theo id
    @GetMapping("/{phoneId}")
    public Phone getPhone(@PathVariable int phoneId) {
        return phoneService.getPhoneById(phoneId);
    }

//    lấy sản phẩm theo hãng
    @GetMapping("findPhoneBrand/{phoneBrand}")
    public List<Phone> getPhoneBrand(@PathVariable String phoneBrand) {
        return phoneService.getPhoneByBrand(phoneBrand);
    }


//    Thêm sản phẩm
    @PostMapping
    public Phone createPhone(@RequestBody PhoneCreationRequest request) {
        return phoneService.creationPhone(request);
    }

//    xóa sản phẩm
    @DeleteMapping("/deletedPhone/{phoneId}")
    public void deletePhone(@PathVariable int phoneId) {
        phoneService.deletedPhone(phoneId);
    }


//    sửa sản phẩm theo id
    @PutMapping("/updatePhone/{phoneId}")
    public Phone updatePhone(@PathVariable int phoneId, @RequestBody PhoneUpdateRequest request) {
        return phoneService.updatedPhone(phoneId,request);
    }


//    lọc sản phẩm theo ram
    @GetMapping("/findPhoneByRam/{phoneRam}")
    public List<Phone> getPhoneRam(@PathVariable int phoneRam) {
        return phoneService.getPhoneByRam(phoneRam);
    }


//     lọc sản phẩm theo rom -> đã test oke
    @GetMapping("/findPhoneByRom/{phoneRom}")
    public List<Phone> getPhoneRom(@PathVariable int phoneRom){
        return phoneService.getPhoneByRom(phoneRom);
    }

// lọc sản phẩm theo giá tiền -> đã test ok
    @GetMapping("/findPhoneByPrice")
    public List<Phone> getPhoneByPrice(@RequestBody PhoneBetweenPrice request) {
        return phoneService.getListPhoneByPrice(request);
    }

//    lọc sản phẩm theo screen size -> đã test ok
    @GetMapping("/findPhoneByScreenSize")
    public List<Phone> getPhoneByScreenSize(@RequestBody PhoneBetweenSreenSize request) {
        return phoneService.getListPhoneByScreenSize(request);
    }

//  lọc sản phẩm theo color
    @GetMapping("/findPhoneByColor/{phoneColor}")
    public List<Phone> getPhoneByColor(@PathVariable String phoneColor) {
        return phoneService.getListPhoneByColor(phoneColor);
    }

}
