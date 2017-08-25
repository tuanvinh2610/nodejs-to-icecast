package com.example.tuanvinh.weatherapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONTACT_ID";

    private ListView listView;
    DatabaseHandler databaseHandler;

    TextView result1;

    EditText cityName;

    Weather weather;
    Context context;

    ArrayList<HashMap<String, String>> weatherList;

    // This method would be called when What's the weather button would be pressed
    public void findtheweather(View view) {

        String s = cityName.getText().toString();

        download task = new download();

        task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + s + "&appid=9f681051b003f104ae5e2a0cbef19a02");

    }

    public void saveWeather(View view) {
        Intent intent = new Intent(this, ListWeatherActivity.class);
        this.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherList = new ArrayList<>();

        cityName = (EditText) findViewById(R.id.cityname);

    }

    private class download extends AsyncTask<String, Void, String> {


        @Override
        protected void onPostExecute(String result) {
            try {
                result1 = (TextView) findViewById(R.id.result);

                JSONObject jsonObject = new JSONObject(result);

                Integer id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String weather = jsonObject.getString("weather");

                JSONObject main = jsonObject.getJSONObject("main");
                Double temp = main.getDouble("temp");
                Double humidity = main.getDouble("humidity");


                JSONObject wind = jsonObject.getJSONObject("wind");
                Double speed = wind.getDouble("speed");

                JSONArray arr = new JSONArray(weather);
//                for (int i = 0; i <= arr.length(); i++) {

                JSONObject jsonPart = arr.getJSONObject(0);


                HashMap<String, String> mapWeather = new HashMap<>();
                mapWeather.put("id", id.toString());
                mapWeather.put("name", name);
                mapWeather.put("main", jsonPart.getString("main"));
                mapWeather.put("description", jsonPart.getString("description"));
                mapWeather.put("temp", temp.toString());
                mapWeather.put("humidity", humidity.toString());
                mapWeather.put("speed", speed.toString());

                 weatherList.add(mapWeather);
//                databaseHandler = new DatabaseHandler(context);
//                databaseHandler.addWeather();


                result1.setText(String.format("Id: %s\n Name: %s\n Main : %s\n Description : %s\n Temperature:%s\n Humidity:%s\n Speed: %s\n",
                        id, name, jsonPart.getString("main"), jsonPart.getString("description"), temp, humidity, speed));
//                }


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... urls) {
            String result = "";

            URL url;
            HttpURLConnection urlConnection;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

//                using it to check if we reached the end of String / Data
                int Data = inputStreamReader.read();
//              using While loop to assign that data to string called result because InputStreamReader reads only one character at a time
                while (Data != -1) {

                    char current = (char) Data;

                    result += current;

                    Data = inputStreamReader.read();
                }

                // returning value of result

                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


    }

}

