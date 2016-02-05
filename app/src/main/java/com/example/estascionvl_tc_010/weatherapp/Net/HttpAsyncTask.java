package com.example.estascionvl_tc_010.weatherapp.Net;

import android.os.AsyncTask;
import android.widget.Switch;

import java.io.IOException;

/**
 * Created by EstascionVL-TC-010 on 3/02/16.
 */
public class HttpAsyncTask extends AsyncTask<String,Integer,String>{



    public static final int METHOD_GET = 0;
    public static final int METHOD_POST = 1;
    public static final int METHOD_POST_JSON = 2;


    public interface Httpi{
        void onResponseReceived(String response);

    }

    Httpi httpI;
    int method;

    public HttpAsyncTask(int method, Httpi httpI){
        this.method = method;
        this.httpI = httpI;

    }

    @Override
    protected String doInBackground(String... params) {

        HttpConnection con = new HttpConnection();
        String rta = null;
    try {
        switch (method) {
            case METHOD_GET:
                rta = con.requestByGet(params[0]);
                break;
            case METHOD_POST:
                rta = con.requestByPostForm(params[0],params[1]);
                break;
            case METHOD_POST_JSON:
                rta = con.requestByPostJson(params[0],params[1]);
                break;

        }
    }catch (IOException e){

    }
        return rta;
    }




    @Override
    protected void onPostExecute(String s) {
        httpI.onResponseReceived(s);
    }
}
