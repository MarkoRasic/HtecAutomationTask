package htecautomation.sandboxAPI.pojo;

import lombok.Data;
import org.jeasy.random.annotation.Exclude;

import java.util.List;

/**
 * pojo class used for creating payloads for use cases requests
 */
@Data
public class UseCaseObjects {
    private String title;
    private String description;
    private String expected_result;
    private Boolean automated;
    private List<String> teststeps;

    @Exclude
    private Integer usecase_id;
    @Exclude
    private Integer user_id;
}
