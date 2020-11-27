package nkosi.william.gmtechassignment;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import nkosi.william.gmtechassignment.models.Commit;

public class GitHubService {
    private static final String TAG = "GitHubService";
    ArrayList<Commit> listOfCommits = new ArrayList<Commit>();

    public GitHubService() {
        new RequestAsync().execute();
    }
    
    public ArrayList getListOfCommits(){
        return listOfCommits;
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
        
        return  null;
    }

    private class RequestAsync extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                //Get Request
                String json = RequestHandler.httpSendGet("https://api.github.com/repos/williamnkosi/GM_Tech_Assignment/commits");
                if(json != null){
                    Log.d(TAG, "doInBackground: is working" + json);
                    JSONArray jsonArray = new JSONArray(json);
                     createListOfCommits(jsonArray);
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            return  null;
        }

    }
}
