package nkosi.william.gmtechassignment;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.ArrayList;

import nkosi.william.gmtechassignment.models.Commit;

public class GitHubService {
    ArrayList<Commit> listOfCommits = new ArrayList<Commit>();

    public GitHubService() {
        new RequestAsync().execute();
    }

    private ArrayList<Commit> createListOfCommits(JSONObject json) {
        
    }

    private class RequestAsync extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                //Get Request
                String json = RequestHandler.httpSendGet("https://api.github.com/repos/williamnkosi/GM_Tech_Assignment/commits");
                if(json != null){
                    JSONObject jsonObject = new JSONObject(json);
                    listOfCommits = createListOfCommits(jsonObject);
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            return  null;
        }

    }
}
