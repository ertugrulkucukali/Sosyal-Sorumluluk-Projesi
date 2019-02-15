package com.example.erts.sosyalimamasorumlumuyum;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;

public class MainActivity extends AppCompatActivity {

    public static Container container = new Container();

    private ListView lstgonderiler;
    private GonderiAdapter adp;

    private LinearLayout pnlTasiyici;
    private LinearLayout pnlButton;
    private Button btnYeniGnd;

    Button veriCekButton ;

    private void init(){

        pnlTasiyici = new LinearLayout(this);
        pnlButton = new LinearLayout(this);
        btnYeniGnd = new Button(this);
        veriCekButton = new Button(this);
        veriCekButton.setText("GÖNDERİLERİ LİSTELE");

        lstgonderiler=new ListView(this);
        adp = new GonderiAdapter(this,container);
    }

    public void BindData(){
        pnlTasiyici.setOrientation(LinearLayout.VERTICAL);
        pnlButton.addView(btnYeniGnd);
        pnlTasiyici.addView(pnlButton);
        pnlTasiyici.addView(veriCekButton);

        lstgonderiler.setAdapter(adp);
        pnlTasiyici.addView(lstgonderiler);
    }



    public void gonderiCekVeritabani() {

        veriCekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);

                            for (int i = 1; i <LoginActivity.giristekisatirsayisi + 1 ; i++) {
                                JSONObject obj = jsonarray.getJSONObject(i);

                                int id = obj.getInt("user_id_fk");
                                String il = obj.getString("il");
                                String baslik = obj.getString("baslik");
                                String aciklama = obj.getString("aciklama");
                                String adres = obj.getString("adres");
                                String telefon = obj.getString("telefon");
                                String kullanici = obj.getString("kullanici");


                                Gonderi gecicinesne = new Gonderi(id, il, baslik, aciklama, adres, telefon);
                                gecicinesne.setKullanici(kullanici);
                                MainActivity.container.addGonderiInVector(gecicinesne);


                            }

                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            MainActivity.this.startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("GÖNDERİLER ŞU AN GÖSTERİLEMİYOR..")
                                    .setNegativeButton("TEKRAR DENE", null)
                                    .create()
                                    .show();
                        }
                    }
                };

                GonderiCekRequest gonderiCekRequest = new GonderiCekRequest(responseListener);

                try {
                    gonderiCekRequest.setRetryPolicy(new DefaultRetryPolicy(DEFAULT_TIMEOUT_MS,20,2f));
                }catch (Exception e){
                    e.printStackTrace();
                }

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(gonderiCekRequest);
            }
        });


    }



    private void gonderiGirisKopru(){

        btnYeniGnd.setText("YENİ GÖNDERİ OLUŞTUR");

        btnYeniGnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intntMain = new Intent(MainActivity.this, GonderiGirisActivity.class);

                MainActivity.this.startActivity(intntMain);

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        gonderiCekVeritabani();
        BindData();
        gonderiGirisKopru();
        setContentView(pnlTasiyici);


    }
}
/*
    @Override
    protected void onStart() {
        super.onStart();
        gonderiCekVeritabani();
        init();
        BindData();
        gonderiGirisKopru();
        setContentView(pnlTasiyici);
    }


    @Override
    protected void onResume() {
        super.onResume();
        gonderiCekVeritabani();
        init();

        BindData();
        gonderiGirisKopru();
        setContentView(pnlTasiyici);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        gonderiCekVeritabani();
        init();
        BindData();
        gonderiGirisKopru();
        setContentView(pnlTasiyici);
    }
    */
