package LuVanThanh.Myproject.request.userRequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserUpdateRequest {
    private String userName;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userBirthday;
    private String userPhone;
}
