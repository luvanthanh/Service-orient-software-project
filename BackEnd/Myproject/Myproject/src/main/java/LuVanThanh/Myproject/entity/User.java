package LuVanThanh.Myproject.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    private String userName;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userBirthday;
    private String userPhone;
}
