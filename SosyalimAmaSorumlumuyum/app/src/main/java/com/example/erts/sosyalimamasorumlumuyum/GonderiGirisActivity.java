package com.example.erts.sosyalimamasorumlumuyum;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;

public class GonderiGirisActivity extends AppCompatActivity {
    private RelativeLayout pnlGonderiGiris;
    private RelativeLayout.LayoutParams prms;
    private Button giris;




    private void init(){
        pnlGonderiGiris=new RelativeLayout(this);

        LayoutInflater li = getLayoutInflater();
        pnlGonderiGiris= (RelativeLayout) li.inflate(R.layout.gonderi_giris,null);

        giris = new Button(this);
        giris.setText("OLUŞTUR");
        prms = new RelativeLayout.LayoutParams(400,110);
        prms.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        prms.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        giris.setLayoutParams(prms);
        pnlGonderiGiris.addView(giris);
    }



    public void gonderiGirisClick(){
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editTextIl = (EditText) pnlGonderiGiris.findViewById(R.id.ilGiris);
                String ilStr = editTextIl.getText().toString();

                final EditText editTextBaslik = (EditText) pnlGonderiGiris.findViewById(R.id.baslikGiris);
                String baslikStr = editTextBaslik.getText().toString();

                EditText editTextAciklama = (EditText) pnlGonderiGiris.findViewById(R.id.aciklamaGiris);
                String aciklamaStr = editTextAciklama.getText().toString();

                EditText editTextAdres = (EditText) pnlGonderiGiris.findViewById(R.id.adresGiris);
                String adresStr = editTextAdres.getText().toString();

                EditText editTextTelefon = (EditText) pnlGonderiGiris.findViewById(R.id.telefonGiris);
                String telefonStr = editTextTelefon.getText().toString();



                Gonderi geciciGonderi = new Gonderi(LoginActivity.id,ilStr,baslikStr,aciklamaStr,adresStr,telefonStr);

                geciciGonderi.setKullanici(LoginActivity.name);

                MainActivity.container.addGonderiInVector(geciciGonderi);       //Container'a nesne eklendi


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intntGiris = new Intent(GonderiGirisActivity.this, MainActivity.class);
                                GonderiGirisActivity.this.startActivity(intntGiris);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(GonderiGirisActivity.this);
                                builder.setMessage("GÖNDERİ GİRİŞİ BAŞARISIZ!")
                                        .setNegativeButton("TEKRAR DENE", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                int geciciNesneIndex = MainActivity.container.getVector().indexOf(geciciGonderi);

                GonderiEkleRequest gonderiEkleRequest = new GonderiEkleRequest(
                        MainActivity.container.getVector().get(geciciNesneIndex).getId(),
                        MainActivity.container.getVector().get(geciciNesneIndex).getKullanici(),
                        MainActivity.container.getVector().get(geciciNesneIndex).getIl(),
                        MainActivity.container.getVector().get(geciciNesneIndex).getBaslik(),
                        MainActivity.container.getVector().get(geciciNesneIndex).getAciklama(),
                        MainActivity.container.getVector().get(geciciNesneIndex).getAdres(),
                        MainActivity.container.getVector().get(geciciNesneIndex).getTelefon(),
                        responseListener);

                try {
                    gonderiEkleRequest.setRetryPolicy(new DefaultRetryPolicy(DEFAULT_TIMEOUT_MS,20,2f));
                }catch (Exception e){
                    e.printStackTrace();
                }

                RequestQueue queue = Volley.newRequestQueue(GonderiGirisActivity.this);
                queue.add(gonderiEkleRequest);

            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        gonderiGirisClick();
        setContentView(pnlGonderiGiris);
    }
}
