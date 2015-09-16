 package org.apache.cordova.plugin;

    import org.apache.cordova.CordovaPlugin;
    import org.apache.cordova.CallbackContext;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    public class Echo extends CordovaPlugin {

        @Override
        public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
            if (action.equals("echo")) {
                String name = data.getString(0);
                callbackContext.success("Hello "+name);
                return true;
            }
            return false;
        }
    }