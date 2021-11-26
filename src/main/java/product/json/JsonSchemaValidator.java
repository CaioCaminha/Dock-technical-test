package product.json;

import com.google.gson.Gson;
import product.dto.ProductDto;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import product.response.model.Error;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class JsonSchemaValidator {

    public List validateJson(ProductDto productDto) throws JsonProcessingException  {
        Gson gson = new Gson();
        List<Error> errors = new ArrayList<Error>();
        String json = gson.toJson(productDto);
        InputStream schemaAsStream = JsonSchemaValidator.class.getClassLoader().getResourceAsStream("model/dock.schema.json");
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaAsStream);

        ObjectMapper om = new ObjectMapper();
        om.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        JsonNode jsonNode = om.readTree(json);

        Set<ValidationMessage> validations = schema.validate(jsonNode);
        if(validations.size() > 0){
            for(ValidationMessage validation : validations){
                errors.add(new Error(validation.getType(), validation.getPath(), validation.getMessage()));
            }
            return errors;
        }else{
            return errors;
        }

    }
}
