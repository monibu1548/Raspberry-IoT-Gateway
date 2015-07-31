import Arduino.ArduinoManager;
import HTTP.HTTPmanager;
import Protocol.Data;

/**
 * Created by monibu on 2015. 7. 31..
 */
public class IotGateway implements Runnable{

    private static ArduinoManager arduinoManager;
    private static HTTPmanager httPmanager;
    private static IotGateway iotGateway;

    private static Data arduinoData;
    private static Data httpData;

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
