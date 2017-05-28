package parkar.alim.inteliment.tasks;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import parkar.alim.inteliment.models.LocationInfo;

/**
 * Async task to fetch the location info from the server
 */
public class FetchDataTask extends AsyncTask<Void, Void, List<LocationInfo>> {

    private static final String TAG = "FetchDataTask";
    private static final String LINK = "http://express-it.optusnet.com.au/sample.json";

    /**
     * Listener for the caller
     */
    private CompletionListener completionListener;

    public FetchDataTask(CompletionListener completionListener) {
        this.completionListener = completionListener;
    }

    @Override protected List<LocationInfo> doInBackground(Void... params) {
        try {
            URL jsonDataUrl = new URL(LINK);
            HttpURLConnection urlConnection = (HttpURLConnection) jsonDataUrl.openConnection();

            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }

            //The response from the server starts with json array. So GSON requires a different parser type
            Type listType = new TypeToken<ArrayList<LocationInfo>>() {
            }.getType();

            return new Gson().fromJson(total.toString(), listType);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Invalid URL", e);
        } catch (IOException e) {
            Log.e(TAG, "Failed to read stream", e);
        }
        return null;
    }

    @Override protected void onPostExecute(List<LocationInfo> locations) {
        if (completionListener != null) {
            //share the result on UI thread
            completionListener.onComplete(locations);
        }
    }

    /**
     * Interface that will share the result to the caller
     */
    public interface CompletionListener {
        /**
         * Called after the completion of the task.
         * @param locationInfoList The list of locations retrieved from the server. If any error occurs, the list is <code>null</code>
         */
        void onComplete(@Nullable List<LocationInfo> locationInfoList);
    }
}
