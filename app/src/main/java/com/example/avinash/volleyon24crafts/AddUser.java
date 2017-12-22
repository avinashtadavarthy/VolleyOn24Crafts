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

import java.util.HashMap;
import java.util.Map;

public class AddUser extends AppCompatActivity {

    EditText name;
    EditText pwd;
    EditText dob;
    EditText isClient;
    EditText category;
    EditText gender;
    EditText email;
    Button adduser;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        name = (EditText) findViewById(R.id.name);
        pwd = (EditText) findViewById(R.id.pwd);
        dob = (EditText) findViewById(R.id.dob);
        isClient = (EditText) findViewById(R.id.isClient);
        category = (EditText) findViewById(R.id.category);
        gender = (EditText) findViewById(R.id.gender);
        email = (EditText) findViewById(R.id.email);
        adduser = (Button) findViewById(R.id.adduser);
        display = (TextView) findViewById(R.id.display);

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postJsonRequest();
            }
        });

    }

    void postJsonRequest() {
        String url = "http://24crafts.tk:3000/adduser";

        final String userid = name.getText().toString().trim();
        final String password = pwd.getText().toString().trim();
        final String date = dob.getText().toString().trim();
        final String client = isClient.getText().toString().trim();
        final String categ = category.getText().toString().trim();
        final String gend = gender.getText().toString().trim();
        final String emailid = email.getText().toString().trim();

        //RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                display.setText(response);

                Intent i = new Intent(getApplicationContext(), PostRequest.class).putExtra("username", emailid).putExtra("password", password);
                startActivity(i);

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

                params.put("name", userid);
                params.put("password", password);
                params.put("dob", date);
                params.put("isClient", client);
                params.put("category", categ);
                params.put("gender", gend);
                params.put("email", emailid);

                return params;
            }
        };

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

}
