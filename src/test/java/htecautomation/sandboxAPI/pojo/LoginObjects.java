package htecautomation.sandboxAPI.pojo;

import lombok.Data;

/**
 * pojo class used for creating payloads for use login requests
 */
@Data
public class LoginObjects {

    private String email;
    private String password;

}
