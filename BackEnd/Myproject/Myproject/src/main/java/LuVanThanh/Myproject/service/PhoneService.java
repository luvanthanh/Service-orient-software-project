package LuVanThanh.Myproject.service;


import LuVanThanh.Myproject.entity.Phone;
import LuVanThanh.Myproject.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


}
