package product.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import product.dto.ProductDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JsonSchemaValidatorTest {

    private final String VALID_BODY_JSON = "44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN";

    ProductDto validProductDto = ProductDto.generateDto(this.VALID_BODY_JSON);

    public JsonSchemaValidatorTest() throws Exception {
    }


    @Test
    public void validateJson() throws JsonProcessingException {
        JsonSchemaValidator jsonSchemaValidator = new JsonSchemaValidator();

        List validJson = jsonSchemaValidator.validateJson(validProductDto);
        assertEquals(0, validJson.size());
    }



}
