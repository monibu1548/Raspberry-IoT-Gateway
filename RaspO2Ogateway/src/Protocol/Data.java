package Protocol;

import org.json.simple.JSONObject;

/**
 * Created by monibu on 2015. 7. 31..
 */
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
