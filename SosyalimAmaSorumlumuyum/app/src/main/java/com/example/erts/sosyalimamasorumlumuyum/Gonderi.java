package com.example.erts.sosyalimamasorumlumuyum;

import java.util.Date;
/**
 * Created by erts on 20.05.2017.
 */

public class Gonderi {
    private int id;
    private String tarih;
    private String kullanici;
    private String il;
    private String baslik;
    private String aciklama;
    private String adres;
    private String telefon;

    public Gonderi( int id, String il, String baslik, String aciklama, String adres, String telefon){
        this.id=id;
        this.il=il;
        this.baslik=baslik;
        this.aciklama=aciklama;
        this.adres=adres;
        this.telefon=telefon;
        this.tarih=new Date().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKullanici() {
        return kullanici;
    }

    public void setKullanici(String kullanici) {
        this.kullanici = kullanici;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
