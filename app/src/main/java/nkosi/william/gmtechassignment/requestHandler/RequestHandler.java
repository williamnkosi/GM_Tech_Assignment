package nkosi.william.gmtechassignment.requestHandler;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;

public class RequestHandler {
    private static final String TAG = "RequestHandler";

    @Inject
    public RequestHandler() {
    }

    //GET Request
    public String httpSendGet(String requestUrl) {
        try {
            URL reqUrl = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) reqUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            Log.d(TAG, "httpSendGet: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK){
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader in = new BufferedReader(inputStreamReader);

                String line;
                StringBuffer response = new StringBuffer();
                 while ((line = in.readLine()) != null){
                     response.append(line);
                 }
                 in.close();
                 String returnString = response.toString();
                Log.d(TAG, "httpSendGet: " + returnString);
                 return  returnString;
            }


        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

        // POST Request
        // UPDATE Request
        // DELETE Request
    }
}
