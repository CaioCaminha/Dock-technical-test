package dock.json;

import com.google.gson.Gson;
import dock.dto.DockDto;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.Set;

@Component
public class JsonSchemaValidator {

    public boolean isJsonValid(DockDto dockDto) throws JsonProcessingException  {
        Gson gson = new Gson();
        String json = gson.toJson(dockDto);
        InputStream schemaAsStream = JsonSchemaValidator.class.getClassLoader().getResourceAsStream("model/dock.schema.json");
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaAsStream);

        ObjectMapper om = new ObjectMapper();
        om.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        JsonNode jsonNode = om.readTree(json);

        Set<ValidationMessage> errors = schema.validate(jsonNode);

        if(errors.size() > 0){
            return false;
        }else{
            return true;
        }
    }
}
