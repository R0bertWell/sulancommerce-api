package src.sulancommerce.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static <T> T jsonToObject(String json, Class<T> cast) throws JSONException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JSONObject jsonObject = new JSONObject(json);

        try {
            return mapper.readValue(jsonObject.toString(), cast);
        } catch (Exception var7) {
            throw new JSONException("");
        }
    }


}
