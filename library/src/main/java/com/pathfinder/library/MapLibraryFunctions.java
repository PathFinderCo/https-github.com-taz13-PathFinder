package com.pathfinder.library;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Tahseen- PC on 5/13/2017.
 */


public class MapLibraryFunctions {
    Context activityContext;

    LatLng origin;
    LatLng dest;
    Map<String, String> latLongMap;
    private GoogleMap mMap;
    private String[] path;

    private ArrayList<LatLng> busStopLatLng;
    private AsyncTaskClass.DownloadTask downloadTask;

    private String mapDirectionsKey;

    public MapLibraryFunctions() {
        downloadTask = new AsyncTaskClass.DownloadTask();
        AsyncTaskClass.DownloadTask.setMapFunctions(this);
    }

    /**
     * A method to download json data from url
     */
    public static String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("xcptn dwnlding url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    public Context getActivityContext() {
        return activityContext;
    }

    public void setActivityContext(Context activityContext) {
        this.activityContext = activityContext;
    }

    public LatLng getOrigin() {
        return origin;
    }

    public void setOrigin(LatLng origin) {
        this.origin = origin;
    }

    public LatLng getDest() {
        return dest;
    }

    public void setDest(LatLng dest) {
        this.dest = dest;
    }

    public String[] getPath() {
        return path;
    }

    public void setPath(String[] path) {
        this.path = path;
    }

    public GoogleMap getMap() {
        return mMap;
    }

    public void setMap(GoogleMap mMap) {
        this.mMap = mMap;
    }

    public Map<String, String> getLatLongMap() {
        return latLongMap;
    }

    public void setLatLongMap(Map<String, String> latLongMap) {
        this.latLongMap = latLongMap;
    }

    public ArrayList<LatLng> getBusStopLatLng() {
        return busStopLatLng;
    }

    public void setBusStopLatLng(ArrayList<LatLng> busStopLatLng) {
        this.busStopLatLng = busStopLatLng;
    }

    public String getMapDirectionsKey() {
        return mapDirectionsKey;
    }

    public void setMapDirectionsKey(String mapDirectionsKey) {
        this.mapDirectionsKey = mapDirectionsKey;
    }

    public void drawPath() {
        origin = new LatLng(Double.valueOf(latLongMap.get(path[0]).split(",")[0]), Double.valueOf(latLongMap.get(path[0]).split(",")[1]));
        dest = new LatLng(Double.valueOf(latLongMap.get(path[path.length - 1]).split(",")[0]), Double.valueOf(latLongMap.get(path[path.length - 1]).split(",")[1]));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(origin));
        for (String stop : path) {
            String[] latLong = latLongMap.get(stop).split(",");
            //adding marker to the map
            LatLng busStop = new LatLng(Double.valueOf(latLong[0]), Double.valueOf(latLong[1]));
            if (!busStop.equals(origin) || !busStop.equals(dest)) {
                busStopLatLng.add(busStop);
            }
            mMap.addMarker(new MarkerOptions().position(busStop).title(stop));
        }
    }

    public void getDirection() {
        // Toast.makeText(this,"this is url"+url,Toast.LENGTH_SHORT).show();
        //Showing a dialog till we get the route

        drawPath();
        // Getting URL to the Google Directions API
        String url = getDirectionsUrl(origin, dest);


        // Start downloading json data from Google Directions API
        downloadTask.execute(url);

    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Waypoints
        String waypoints = "waypoints=";
        for (int i = 0; i < busStopLatLng.size(); i++) {
            LatLng point = busStopLatLng.get(i);
            waypoints += point.latitude + "," + point.longitude + "|";
        }


        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + waypoints + "&key=" + mapDirectionsKey;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }
}
