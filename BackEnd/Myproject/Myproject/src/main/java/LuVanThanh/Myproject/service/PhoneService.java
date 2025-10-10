package LuVanThanh.Myproject.service;


import LuVanThanh.Myproject.entity.Phone;
import LuVanThanh.Myproject.repository.PhoneRepository;
import LuVanThanh.Myproject.request.phoneRequest.PhoneBetweenPrice;
import LuVanThanh.Myproject.request.phoneRequest.PhoneBetweenSreenSize;
import LuVanThanh.Myproject.request.phoneRequest.PhoneCreationRequest;
import LuVanThanh.Myproject.request.phoneRequest.PhoneUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public List<Phone> getAllPhone() {
        return phoneRepository.findAll();
    }

    public Phone getPhoneById(int id) {
        return phoneRepository.findById(id)
                .orElseThrow(()->new RuntimeException("phone does not exist"));
    }

    public List<Phone> getPhoneByBrand (String brand){
        List<Phone> listPhones = phoneRepository.findByBrandIgnoreCase(brand);
        if (listPhones.isEmpty()) {
            throw new RuntimeException("No phones found for brand: " + brand);
        }
        return listPhones;
    }

//    tạo sản phẩm
    public Phone creationPhone( PhoneCreationRequest request){
        Phone phone = new Phone();

        phone.setId(request.getId());
        phone.setProductName(request.getProductName());
        phone.setBrand(request.getBrand());
        phone.setScreenSize(request.getScreenSize());
        phone.setColor(request.getColor());
        phone.setRam(request.getRam());
        phone.setRom(request.getRom());
        phone.setDescription(request.getDescription());
        phone.setReleaseDate(request.getReleaseDate());
        phone.setStockQuantity(request.getStockQuantity());
        phone.setWarranty(request.getWarranty());
        phone.setPrice(request.getPrice());
        phone.setFormattedPrice(request.getFormattedPrice());
        return phoneRepository.save(phone);

    }

//    xóa sản phẩm theo id
    public String deletedPhone(int phoneId){
        phoneRepository.deleteById(phoneId);
        return "Phone deleted successfully";
    }


//    sửa thông tin sản phẩm theo id sản phẩm
    public Phone updatedPhone(int phoneId, PhoneUpdateRequest request){
        Phone phone = getPhoneById(phoneId);

        phone.setProductName(request.getProductName());
        phone.setBrand(request.getBrand());
        phone.setScreenSize(request.getScreenSize());
        phone.setColor(request.getColor());
        phone.setRam(request.getRam());
        phone.setRom(request.getRom());
        phone.setDescription(request.getDescription());
        phone.setReleaseDate(request.getReleaseDate());
        phone.setStockQuantity(request.getStockQuantity());
        phone.setWarranty(request.getWarranty());
        phone.setPrice(request.getPrice());
        phone.setFormattedPrice(request.getFormattedPrice());
        return phoneRepository.save(phone);
    }


//    tìm sản phẩm theo ram
    public List<Phone> getPhoneByRam(int ram){
        List<Phone> list = phoneRepository.findByRam(ram);
        if (list.isEmpty()) {
            throw new RuntimeException("No phones found for brand: " + ram);
        }
        return list;
    }

//    tìm kiêm sản phẩm theo rom
    public List<Phone> getPhoneByRom(int rom){
        List<Phone> listPhoneRom = phoneRepository.findByRom(rom);
        if (listPhoneRom.isEmpty()) {
            throw new RuntimeException("No phones found for rom: " + rom);
        }
        return listPhoneRom;
    }



//    tìm kiếm danh sach trong khoảng giá tiền
    public List<Phone> getListPhoneByPrice(PhoneBetweenPrice request){
       return  phoneRepository.findByPriceBetween(request.getPriceMin(), request.getPriceMax());
    }


//    tìm kiếm sản phẩm theo screenSize
    public List<Phone> getListPhoneByScreenSize(PhoneBetweenSreenSize request){
        return phoneRepository.findByScreenSizeBetween(request.getScreenSizeMin(), request.getScreenSizeMax());
    }


//    tìm kiếm sản phẩm theo màu sắc
    public List<Phone> getListPhoneByColor(String color){
        return phoneRepository.findByColor(color);
    }

}




