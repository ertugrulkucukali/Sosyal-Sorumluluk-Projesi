package com.example.erts.sosyalimamasorumlumuyum;

import android.app.Activity;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by erts on 20.05.2017.
 */

public class GonderiAdapter extends BaseAdapter {
    private Context context;
    private Container model;

    public GonderiAdapter(Context context, Container model)
    {
        this.context = context;
        this.model = model;
    }

    @Override
    public int getCount() {
        return model.getVector().size();
    }

    @Override
    public Object getItem(int position) {
        return model.getVector().get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)((Gonderi)this.getItem(position)).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Gonderi gnd = (Gonderi) getItem(position);

        LayoutInflater li = ((Activity) context).getLayoutInflater();
        RelativeLayout pnlItem = (RelativeLayout) li.inflate(R.layout.gonderi, null);
        pnlItem.setMinimumHeight(500);

        TextView txtKullanici = (TextView) pnlItem.findViewById(R.id.kullanici);
        txtKullanici.setText(gnd.getKullanici());

        TextView txtTarih = (TextView) pnlItem.findViewById(R.id.tarih);
        txtTarih.setText((CharSequence) gnd.getTarih());

        TextView txtIl = (TextView) pnlItem.findViewById(R.id.il);
        txtIl.setText(gnd.getIl());

        TextView txtBaslik = (TextView) pnlItem.findViewById(R.id.baslik);
        txtBaslik.setText(gnd.getBaslik());

        TextView txtAciklama = (TextView) pnlItem.findViewById(R.id.aciklama);
        txtAciklama.setMovementMethod(new ScrollingMovementMethod());
        txtAciklama.setText(gnd.getAciklama());

        TextView txtAdres = (TextView) pnlItem.findViewById(R.id.adres);
        txtAdres.setMovementMethod(new ScrollingMovementMethod());
        txtAdres.setText(gnd.getAdres());

        TextView txtTelefon = (TextView) pnlItem.findViewById(R.id.telefon);
        txtTelefon.setText(gnd.getTelefon());

        return pnlItem;
    }
}
