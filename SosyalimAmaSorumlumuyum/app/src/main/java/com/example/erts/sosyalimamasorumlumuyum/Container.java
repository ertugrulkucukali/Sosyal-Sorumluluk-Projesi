package com.example.erts.sosyalimamasorumlumuyum;

import java.util.Vector;

/**
 * Created by erts on 20.05.2017.
 */

public class Container
{
    public Vector<Gonderi> vector;

    public Vector<Gonderi> getVector()
    {
        return vector;
    }

    public void setVector(Vector<Gonderi> vector) {
        this.vector = vector;
    }

    public Container()
    {
        this.vector = new Vector<Gonderi>();
        //vector.add(new Gonderi(200,"TRABZON", "MONT İHTİYACI", "KNCMDOFVMEOPRVMÖOSMVOPRMOVISDOVMORIESDFSDFSFDSDFSDSDCXVXCVXCVXXCVMVPIOSDMVOIPRMVPOIMSDIOPVVSFVSDV", "ANERÖGKRÖKWÖERKGÖWKSDÖVKSLALŞSDMAŞSLMDŞAMSDŞAMSDŞLAMDÖKŞADÖCŞKACASŞCÖ", "05353655296"));
        //vector.add(new Gonderi(2,"İSTANBUL", "AYAKKABI İHTİYACI", "F", "IO", "05353645896"));
        //vector.add(nesne);

    }

    public void addGonderiInVector(Gonderi nesne){
        vector.add(nesne);
    }

}