package nkosi.william.gmtechassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nkosi.william.gmtechassignment.models.Commit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ArrayList<Commit> listOfCommits = new ArrayList<Commit>();
    ListView listView;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.Main_Activity_ListView);
        RequestAsync task = new RequestAsync();
        task.execute();


    }

    private Void createListOfCommits(JSONArray json) {
        for (int i = 0; i < json.length(); i++){
            try {
                JSONObject arrayItem = json.getJSONObject(i);
                String sha = arrayItem.getString("sha");
                JSONObject commitObject = arrayItem.getJSONObject("commit");
                String commitMessage = commitObject.getString("message");
                JSONObject authorObject = commitObject.getJSONObject("author");
                String author = authorObject.getString("name");
                Commit commit = new Commit(author,sha,commitMessage);
                listOfCommits.add(commit);
                Log.d(TAG, "createListOfCommits: " + sha);
                Log.d(TAG, "createListOfCommits: " + commitMessage);
                Log.d(TAG, "createListOfCommits: " +author);
                Log.d(TAG, "createListOfCommits: -----------------");
            } catch (JSONException e){
                e.printStackTrace();
            }

        }
        Log.d(TAG, "createListOfCommits: --->" + listOfCommits.size());
        return  null;
    }

    private class RequestAsync extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading your project commits...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                //Get Request
                String json = RequestHandler.httpSendGet("https://api.github.com/repos/williamnkosi/GM_Tech_Assignment/commits");
                if(json != null){
                    JSONArray jsonArray = new JSONArray(json);
                    createListOfCommits(jsonArray);
                }
            } catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Error retrieving data from server", Toast.LENGTH_LONG).show();
            }

            return  null;
        }

    }
}