package HTTP;

import AppData.ReferenceValue;
import Protocol.Data;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by monibu on 2015. 7. 31..
 */
public class HTTPmanager implements HTTPcontroller{

    private String serverURL = ReferenceValue.ServerURL;
    private URL url;

    private DataOutputStream dos;
    private DataInputStream dis;

    private HttpURLConnection httpURLConnection;

    private JSONParser jsonParser;

    private String lightControl;
    private String airconControl;
    private String projControl;

    public HTTPmanager() {

        lightControl = "O";
        airconControl = "O";
        projControl = "O";

        jsonParser = new JSONParser();

        try {
            url = new URL(serverURL);
        } catch (MalformedURLException e) {
            System.out.println("[ERROR] Cannot Connect URL. Terminate program...");
            e.printStackTrace();
            System.exit(-1);
        }

    }



    @Override
    public Data postJsonData(Data sensorData) {


        String response = null;

        try {

            httpURLConnection = (HttpURLConnection)url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dis = new DataInputStream (httpURLConnection.getInputStream());

            dos.write(sensorData.toString().getBytes("UTF-8"));

            dos.close();

            response = dis.readLine();

            System.out.println("[SEND] " + sensorData.toString());
            System.out.println("[RESPOND] " + response);

            return handlePostResponse(response);

        }catch(Exception ee)
        {
            System.out.println("[ERROR] Cannot Post Data");
        }



        return null;
    }

    public Data handlePostResponse(String response){

        try {
            Data getJsonData = (Data) jsonParser.parse(response);
            return getJsonData;

        } catch (ParseException e) {
            return null;
        }

    };
    
}
