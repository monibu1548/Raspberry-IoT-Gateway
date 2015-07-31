package Arduino;

import AppData.ReferenceValue;
import Protocol.Data;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by monibu on 2015. 7. 31..
 */
public class ArduinoManager implements HWcontroller{

    private CommPortIdentifier portIdentifier;
    private SerialPort serialPort;
    private InputStream in;
    private OutputStream out;

    public static final boolean DEV_MODE = true;

    public ArduinoManager(){
        try {
            // Try Connect Arduino Device
            // If can't connect Arduino, Program will terminate.

            this.connect(ReferenceValue.Device);

            // Printing Success message
            System.out.println("[OK] Success Connect " + ReferenceValue.Device);

        } catch( Exception e ) {

            // Printing Error Message. When cannot connect Arduino.

            System.out.println("[ERROR] Cannot connect Arduino. Please check the device");
            e.printStackTrace();
        }
    }

    void connect( String portName ) throws Exception {

        this.portIdentifier = CommPortIdentifier.getPortIdentifier( portName );

        if( portIdentifier.isCurrentlyOwned() ) {

            System.out.println( "[ERROR] Port is currently in use" );

        } else {

            if(DEV_MODE) System.out.println("[DEBUG] Try Connecting Arduino");

            int timeout = 2000;
            //

            CommPort commPort = portIdentifier.open( this.getClass().getName(), timeout );

            if( commPort instanceof SerialPort ) {
                this.serialPort = ( SerialPort )commPort;
                serialPort.setSerialPortParams( 9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.FLOWCONTROL_NONE );
                this.in = serialPort.getInputStream();
                this.out = serialPort.getOutputStream();
            } else {
                System.out.println( "Error: Only serial ports are handled by this example." );
            }
        }
    }

    @Override
    public void controlByJson(JSONObject json) {

        String serialString;

        String light = json.get("lum").toString();
        String aircon = json.get("aircon").toString();
        String proj = json.get("proj").toString();

        serialString = light + aircon + proj;

        try {
            this.out.write(serialString.getBytes());
        }catch (IOException ee){
            System.out.println("[ERROR] Cannot Send ControlData to Arduino");
        }
    }

    @Override
    public Data getSensorData() {

        int readOneChar;
        Data data = new Data();
        StringBuffer inputSerialData = new StringBuffer();
        String[] dataList;

        while(true) {

            try {
                readOneChar = this.in.read();
                if ((char) readOneChar == '\n') {
                    break;
                }
                inputSerialData.append((char) readOneChar);
            } catch (IOException ee) {
                System.out.println("[ERROR] Cannot Read Arduino Serial Data");
            }
        }

        dataList = inputSerialData.toString().split(" ");

        try {
            data.putId(ReferenceValue.id);
            data.put("control", 0);
            data.putHum(dataList[0]);
            data.putTemp(dataList[1]);
            data.putInuse(dataList[2]);
        }catch(Exception e){
            System.out.println("[ERROR] Input unvalidate Data. Skip Data....");
        }

        return data;
    }

}
