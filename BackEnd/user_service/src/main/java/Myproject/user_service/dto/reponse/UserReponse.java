package Myproject.user_service.dto.reponse;

import lombok.*;

@Data

public class UserReponse {
    private String userId;
    private String userName;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userAddress;
    private String userEmail;
    private String userPhoneNumber;
}
