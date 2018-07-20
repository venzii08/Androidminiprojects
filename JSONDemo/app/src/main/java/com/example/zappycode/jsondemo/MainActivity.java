package com.example.zappycode.jsondemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editText2;
    TextView resultText;
    LocationManager locationManager;
    LocationListener locationListener;

    public class DownloadTask extends AsyncTask<String,Void,String> {



        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo = jsonObject.getString("weather");
                String TempControl = jsonObject.getString("main");
                String light  = jsonObject.getString("sys");
                String visibility = jsonObject.getString("visibility");
                JSONArray arr = new JSONArray(weatherInfo);

                String message = "";
                JSONObject mainTemp = new JSONObject(TempControl);
                JSONObject sun = new JSONObject(light);

                 for (int i=0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);
                    String main = jsonPart.getString("main");
                    String description = jsonPart.getString("description");
                    if (!main.equals("") && !description.equals("")  ){
                       message += main + ":" + description +"\r\n";
                   }
                }

                    String temperature = mainTemp.getString("temp");
                    String pressure = mainTemp.getString("pressure");
                    String humidity = mainTemp.getString("humidity");
                    String tempMin = mainTemp.getString("temp_min");
                    String tempMax = mainTemp.getString("temp_max");
                    String sunrise = sun.getString("sunrise");
                    String sunset = sun.getString("sunset");
                    String country = sun.getString("country");

                    if (!temperature.equals("") && !pressure.equals("") && !humidity.equals("") && !tempMin.equals("") && !tempMax.equals("") && !sunrise.equals("") && !sunset.equals("") && !country.equals("")){
                       message += "Temp: " + temperature + "ºC" + "\r\n" + "Pressure: " + pressure +"mb" + "\r\n"+ "Humidity: " + humidity + "\r\n" + "MinTemp: " +tempMin +"ºC" + "\r\n" + "MaxTemp: " + tempMax + "ºC" + "\r\n" + "Visibility: " + visibility + "\r\n" + "Sunrise: " + sunrise + "\r\n" +  "Sunset: " + sunset + "\r\n" + "Country: " + country + "\r\n";

                   }

                if (!message.equals("")){
                    resultText.setText(message);
                }


            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), " could not find weather",Toast.LENGTH_SHORT).show();
            }

        }
    }

  /*  public void WeatherCheck(View view){
        try{
        DownloadTask task = new DownloadTask();

        task.execute("http://openweathermap.org/data/2.5/weather?q=" + LocationManager.GPS_PROVIDER/*editText2.getText().toString()*/// + "&appid=b6907d289e10d714a6e88b30761fae22");

       // InputMethodManager methodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); ///hide keyboard function
       // methodManager.hideSoftInputFromWindow(editText2.getWindowToken(), 0);

       /* }catch(Exception e){
            e.printStackTrace();
            android.widget.Toast.makeText(getApplicationContext(), " could not find weather", android.widget.Toast.LENGTH_SHORT).show();
            }
    }*/




    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            startListening();
        }
    }

    public void startListening(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

        }
    }

    public void updateLocationInfo(Location location){
        Log.i("Location", location.toString());
        String Address = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        WeatherCheck();

        try {
            List<android.location.Address> listAddresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
            if (listAddresses != null && listAddresses.size() > 0){
                Address = "Address:\n";

                if (listAddresses.get(0).getThoroughfare() != null) {
                    Address += listAddresses.get(0).getThoroughfare() ;
                }
                if (listAddresses.get(0).getAdminArea() !=null){
                    Address += listAddresses.get(0).getAdminArea() + " ";
                }
                if (listAddresses.get(0).getLocality() != null){
                    Address += listAddresses.get(0).getLocality() + " ";
                }

                resultText.setText(Address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void WeatherCheck() {
        try{
            DownloadTask task = new DownloadTask();

            task.execute("http://samples.openweathermap.org/data/2.5/weather?lat="+"&appid=b6907d289e10d714a6e88b30761fae22");

            // InputMethodManager methodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); ///hide keyboard function
            // methodManager.hideSoftInputFromWindow(editText2.getWindowToken(), 0);

        }catch(Exception e){
            e.printStackTrace();
            android.widget.Toast.makeText(getApplicationContext(), " could not find weather", android.widget.Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // editText2 = findViewById(R.id.editText2);
        resultText = findViewById(R.id.resulttext);
        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {   //// regular gps update
                Log.i("Location", location.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {////

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){ ///Its checking for" if the permisiion hasnt been granted then go request it "

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1); /// requesting for permission
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (lastKnownLocation != null){
                updateLocationInfo(lastKnownLocation);
            }
        }
    }
}

