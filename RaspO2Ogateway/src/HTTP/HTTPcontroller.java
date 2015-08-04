/**
 *
 * @author JingyuJung
 * @version 1.0, 2015.7.31
 * @git  https://github.com/monibu1548/Raspberry-IoT-Gateway.git
 * @mail monibu1548@gmail.com
 *
 */

package HTTP;

import Protocol.Data;
import org.json.simple.JSONObject;

public interface HTTPcontroller {
    JSONObject postJsonData(Data sensorData);
}
