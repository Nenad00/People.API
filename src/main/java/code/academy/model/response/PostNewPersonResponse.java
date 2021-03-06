package code.academy.model.response;

import code.academy.model.PersonData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("unsend")
@JsonIgnoreProperties(ignoreUnknown = true)

public class PostNewPersonResponse {

    private String code;
    private String message;
    private PersonData personData;

}
