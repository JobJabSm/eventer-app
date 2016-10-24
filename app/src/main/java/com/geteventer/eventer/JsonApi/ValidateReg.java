package com.geteventer.eventer.JsonApi;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.geteventer.eventer.util.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by gaurav on 24/10/16.
 */
public class ValidateReg{

    String name,reg;
    Boolean flag=false;
    String f;

    public ValidateReg()  {
    }


    public void validateRegJSON(final String username,final String password,final ServerCallback callback)
    {
        Log.d("XYXX",username+"XXX"+password);
        final String URL = "https://mighty-basin-38940.herokuapp.com/crawl.json";
        JSONObject jsonBody;
        try {
            jsonBody = new JSONObject("{\"request\":{\"url\":\"http://courses.christuniversity.in/login/index.php\", \"meta\": {\"username\":\""+username+"\",\"password\":\""+password+"\"},\"callback\": \"parse_product\",\"dont_filter\": \"True\"}, \"spider_name\": \"christuniversity\"}\n");


            RequestFuture<JSONObject> future = RequestFuture.newFuture();
            JsonObjectRequest myRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    URL,
                    jsonBody,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try{
                                Log.d("XYXX",response.toString());
                                callback.onSuccessResult(response);

                            }
                            catch (Exception e)
                            {
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            flag=false;
                        }


                    }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    headers.put("User-agent", "My useragent");
                    return headers;
                }
            };

            myRequest.setRetryPolicy(new DefaultRetryPolicy(
                    50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            AppController.getInstance().addToRequestQueue(myRequest, "tag");
        }
        catch (Exception e)
        {

        }


    }







    public void setName(String name) {
        this.name = name;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }






    public String getName() {
        return name;
    }

    public String getReg() {
        return reg;
    }


}