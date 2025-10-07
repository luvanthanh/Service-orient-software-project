package LuVanThanh.Myproject.controller;


import LuVanThanh.Myproject.entity.Phone;
import LuVanThanh.Myproject.repository.PhoneRepository;
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

    @GetMapping
    public List<Phone> getAllPhones() {
        return phoneService.getAllPhone();
    }

    @GetMapping("/{phoneId}")
    public Phone getPhone(@PathVariable int phoneId) {
        return phoneService.getPhoneById(phoneId);
    }

}
