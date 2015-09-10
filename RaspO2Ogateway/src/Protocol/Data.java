/**
 *
 * @author JingyuJung
 * @version 1.0, 2015.7.31
 * @git  https://github.com/monibu1548/Raspberry-IoT-Gateway.git
 * @mail monibu1548@gmail.com
 *
 */

package Protocol;

import org.json.simple.JSONObject;

public class Data extends JSONObject{

    public Data(){

    }


    public void putId(int id){
        this.put("id", id);
    }

    public void putHum(String hum){
        this.put("hum", hum);
    }

    public void putInuse(String inuse) {
        this.put("inuse", inuse);
    }

    public void putTemp(String temp){
        this.put("temp", temp);
    }


}
