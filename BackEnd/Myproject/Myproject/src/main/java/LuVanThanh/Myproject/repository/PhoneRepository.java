package LuVanThanh.Myproject.repository;

import LuVanThanh.Myproject.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Integer>{
    List<Phone> findByBrandIgnoreCase(String phoneBrand);
    List<Phone> findByRam(int ramPhone);
    List<Phone> findByRom(int romPhone);
    List<Phone> findByPriceBetween(long priceMin, long priceMax);
    List<Phone> findByScreenSizeBetween(double screenSizeMin, double screenSizeMax);
    List<Phone> findByColor(String color);
}
