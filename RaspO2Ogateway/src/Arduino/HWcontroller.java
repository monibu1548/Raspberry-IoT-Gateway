/**
 *
 * @author JingyuJung
 * @version 1.0, 2015.7.31
 * @git  https://github.com/monibu1548/Raspberry-IoT-Gateway.git
 * @mail monibu1548@gmail.com
 *
 */

package Arduino;

import Protocol.Data;
import org.json.simple.JSONObject;

public interface HWcontroller{
    void controlByJson(JSONObject json);
    Data getSensorData();
}
