package com.example.erts.sosyalimamasorumlumuyum;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;



/**
 * Created by erts on 23.05.2017.
 */

public class GonderiCekRequest extends StringRequest {
    private static final String GONDERICEK_URL = "https://hulyacetin12.000webhostapp.com/vtcek.php";

    public GonderiCekRequest(Response.Listener<String> listener) {
        super(Request.Method.POST, GONDERICEK_URL, listener, null);
    }


}
