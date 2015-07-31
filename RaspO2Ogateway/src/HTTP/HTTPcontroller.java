package HTTP;

import Protocol.Data;
import org.json.simple.JSONObject;

/**
 * Created by monibu on 2015. 7. 31..
 */
public interface HTTPcontroller {
    JSONObject postJsonData(Data sensorData);
}
