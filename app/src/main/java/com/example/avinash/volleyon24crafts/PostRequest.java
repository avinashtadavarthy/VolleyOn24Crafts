package com.example.avinash.volleyon24crafts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PostRequest extends AppCompatActivity {

    EditText id;
    EditText pwd;
    Button parse;
    TextView display;

    String jwtToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);

        display = (TextView) findViewById(R.id.display);
        parse = (Button) findViewById(R.id.parse);

        Intent intent = getIntent();

        String idthis = intent.getStringExtra("username");
        String pwdthis = intent.getStringExtra("password");

        postJsonRequest(idthis, pwdthis);

        parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DeleteUser.class).putExtra("token", jwtToken);
                startActivity(i);
            }
        });

        /*

        id = (EditText) findViewById(R.id.id);
        pwd = (EditText) findViewById(R.id.pwd);
        parse = (Button) findViewById(R.id.parse);
        display = (TextView) findViewById(R.id.display);

        parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postJsonRequest();
            }
        });

        */

    }

    private void postJsonRequest(final String id, final String pwd) {

        String url = "http://24crafts.tk:3000/login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    jwtToken = jsonObject.optString("token");








                    //to get the data

                    String newurl = "http://24crafts.tk:3000/user";

                    StringRequest getRequest = new StringRequest(Request.Method.GET, newurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                          display.setText(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {

                            Map<String, String> params = new HashMap<String, String>();

                            params.put("authorization", jwtToken);

                            return params;
                        }
                    };

                    MySingleton.getInstance(getApplicationContext()).addToRequestQueue(getRequest);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //to get the data









            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("username", id);
                params.put("password", pwd);

                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
