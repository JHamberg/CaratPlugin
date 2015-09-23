package org.carat20.client;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import org.carat20.client.protocol.CommunicationManager;
import org.carat20.client.storage.DataStorage;
import org.apache.thrift.TException;
import org.carat20.client.thrift.Reports;

public class Carat extends CordovaPlugin{

    private static DataStorage storage = null;
    private static CommunicationManager communicationManager = null;

    @Override
     public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        storage = new DataStorage();
        Log.v("Carat", "Setting storage, storage is "+storage);
        new Thread() {
            @Override
            public void run() {
                communicationManager = new CommunicationManager(storage);
            }
        }.start();

        //Complete freshness-implementation before running on refreshUi
        while(communicationManager == null){}
        Log.v("Carat", "Making sure CM is initialized, CM is "+communicationManager);
        try {
            communicationManager.refreshAllReports();
        } catch(TException ex){
            Log.v("Carat", "Exception when calling RefreshAllReports(): "+ex);
        }

        if (action.equals("echo")) {
            int message = this.getJscore();
            Log.v("Carat", "Getting JScore, message is "+message);
            callbackContext.success("Jscore is "+message+"!");
            return true;
        }
        return false;
    }
     
    public static int getJscore(){
        while(storage.getReports()==null){}
        Log.v("Carat", "Getting reports for JScore, reports are "+storage.getReports());
        Reports reports = storage.getReports();
        Log.v("Carat", "Reports.getJScore() returned with "+reports.getJScore());
        return (int)(reports.getJScore()*100);
    }
}
