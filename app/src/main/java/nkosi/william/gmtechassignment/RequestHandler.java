package nkosi.william.gmtechassignment;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestHandler {
    private static final String TAG = "RequestHandler";

    public static String httpSendGet(String requestUrl) {
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
                 return  response.toString();
            }


        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Failed to return anything...";
    }
}
