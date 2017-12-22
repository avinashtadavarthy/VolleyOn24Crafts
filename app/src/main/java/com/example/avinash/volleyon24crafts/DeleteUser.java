package com.example.avinash.volleyon24crafts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DeleteUser extends AppCompatActivity {

    String tokenthis;
    Button delete;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        delete = (Button) findViewById(R.id.delete);
        display = (TextView) findViewById(R.id.display);

        Intent intent = getIntent();
        tokenthis = intent.getStringExtra("token");

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteuserRequest(tokenthis);
            }
        });

    }

    void deleteuserRequest(final String kakaka) {

        String url = "http://24crafts.tk:3000/user/delete";

        StringRequest hellomama = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
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

                params.put("authorization", kakaka);

                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(hellomama);
    }

}
