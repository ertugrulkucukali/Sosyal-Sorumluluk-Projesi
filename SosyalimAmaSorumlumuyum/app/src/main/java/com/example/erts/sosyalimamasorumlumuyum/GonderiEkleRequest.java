package com.example.erts.sosyalimamasorumlumuyum;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by erts on 23.05.2017.
 */

public class GonderiEkleRequest extends StringRequest {
    int idYerel = LoginActivity.id;
    private static final String GONDERIEKLE_REQUEST_URL = "https://hulyacetin12.000webhostapp.com/GonderiEkle.php";
    private Map<String, String> params;

    public GonderiEkleRequest(int idYerel, String kullanici, String il, String baslik, String aciklama, String adres, String telefon, Response.Listener<String> listener) {

        super(Method.POST, GONDERIEKLE_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("userID", idYerel + "");
        params.put("kullanici", kullanici);
        params.put("il", il);
        params.put("baslik", baslik);
        params.put("aciklama", aciklama);
        params.put("adres", adres);
        params.put("telefon", telefon);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
