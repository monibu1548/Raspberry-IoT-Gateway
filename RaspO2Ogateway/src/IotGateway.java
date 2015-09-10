/**
 *
 * @author JingyuJung
 * @version 1.0, 2015.7.31
 * @git  https://github.com/monibu1548/Raspberry-IoT-Gateway.git
 * @mail monibu1548@gmail.com
 *
 */

import Arduino.ArduinoManager;
import HTTP.HTTPmanager;
import Protocol.Data;
import org.json.simple.JSONObject;

public class IotGateway implements Runnable{

    private static ArduinoManager arduinoManager;
    private static HTTPmanager httPmanager;
    private static IotGateway iotGateway;

    private static Data arduinoData;
    private static JSONObject httpData;

    public static void main(String[] args){

        arduinoManager = new ArduinoManager();
        httPmanager = new HTTPmanager();
        iotGateway = new IotGateway();

        Thread gateway = new Thread(iotGateway);
        gateway.start();

        while(true){}

    }


    @Override
    public void run() {

        while (true) {
            arduinoData = arduinoManager.getSensorData();
            httpData = httPmanager.postJsonData(arduinoData);

            if (httpData != null) {
                arduinoManager.controlByJson(httpData);
            }
        }

    }
}
