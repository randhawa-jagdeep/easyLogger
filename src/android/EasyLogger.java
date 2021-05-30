package org.apache.cordova.easylogger;

import android.os.Environment;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class echoes a string called from JavaScript.
 */
public class EasyLogger extends CordovaPlugin {

    @Override
    public boolean execute(String action,JSONArray args,final CallbackContext callbackContext) throws JSONException {
        final String log = args.getString(0);
        final  String logFilePath = "/delivery.log";

        if (action.equals("configure")) {
            cordova.getThreadPool().execute(() -> {
             String  fileName =  Environment.getExternalStorageDirectory()+logFilePath;
                    File logFile = new File(fileName);
                    if (!logFile.exists())
                    {
                        try
                        {
                            logFile.createNewFile();
                        }
                        catch (IOException e)
                        {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    try
                    {

                        //BufferedWriter for performance, true to set append to file flag
                        BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
                        buf.append(log);
                        buf.newLine();
                        buf.close();
                        callbackContext.success("Log file created successfully");

                    }
                    catch (IOException e)
                    {
                        callbackContext.error("Error in creating log file.");

                    }

            });
            return true;
        }
        return false;
    }
}
