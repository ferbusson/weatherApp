package com.example.estascionvl_tc_010.weatherapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.estascionvl_tc_010.weatherapp.Net.HttpAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements HttpAsyncTask.Httpi {

    TextView descripcion, humedad, temperatura, presion;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descripcion = (TextView) findViewById(R.id.txt_pronostico);
        humedad = (TextView) findViewById(R.id.txt_humedad);
        temperatura = (TextView) findViewById(R.id.txt_temp);
        presion = (TextView) findViewById(R.id.txt_presion);

        progress = new ProgressDialog(this);
        progress.setMessage(getString(R.string.loading));

    }

    public void loadWeather(){
        HttpAsyncTask task = new HttpAsyncTask(HttpAsyncTask.METHOD_GET, this);
        task.execute(getString(R.string.url));
        progress.show();
    }

    @Override
    public void onResponseReceived(String response){

        progress.dismiss();
try{
    JSONObject obj = new JSONObject(response);
    JSONObject query = obj.getJSONObject("query");
    JSONObject results = query.getJSONObject("results");
    JSONObject channel = results.getJSONObject("channel");
    JSONObject atmosphere = channel.getJSONObject("atmosphere");

    String h = atmosphere.getString("humidity");
    String p = atmosphere.getString("pressure");

    JSONObject item = channel.getJSONObject("item");
    JSONObject condition = item.getJSONObject("condition");

    String i = condition.getString("text");
    String t = condition.getString("temp");

    temperatura.setText(t);
    humedad.setText(h);
    presion.setText(p);
    descripcion.setText(i);

}catch (JSONException e){
    e.printStackTrace();
}
    }
}
