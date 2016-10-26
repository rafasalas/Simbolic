package com.rafasalas.rafalib.atractors;
/**
 * Created by salas on 02/03/2016.
 *
 * This is a Basic Atractor
 *
 */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PVector;
import processing.data.IntDict;

public class Atractor {

    public PVector posicion, origen_icono;
    public float sentido, intensidad_rotacion, angulo;
    public int tipo_atractor;
    public int interaccion;
    public boolean rotacion;
    public int limit;
    Random rnd=new Random();

    public Atractor (int clase){
        posicion=new PVector(0,0);
        interaccion=0;
        sentido=-1;
        tipo_atractor=clase;
        origen_icono=new PVector (0,0);
        rotacion=false;
    }
    public PVector fuerza (PVector posicionobjeto){

        PVector f=posicionobjeto.get();
        f.sub(posicion);

        float modulo=f.mag();
        if (modulo <0) {f.mult(-1);}
        f.normalize();
        if (rotacion){
            if (modulo<limit){f.rotate(angulo);f.mult(intensidad_rotacion);}
        }



        switch(tipo_atractor) {
            case 1:
                f.mult(modulo/50);
                break;
            case 2:
                f.mult(150/modulo);
                break;
            case 3:
                f.mult(4);
                break;
            case 4:
                f.mult(150/modulo*modulo);
                break;
        }
        f.mult(sentido);
        return f;
    }
    public void visible(Canvas canvas){
        Paint paint;

        //int contador=0;
        paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setARGB(30,255,255,255);
        canvas.drawCircle(posicion.x, posicion.y, 100, paint);
        paint.setARGB(30,255,255,255);
        canvas.drawCircle(posicion.x, posicion.y, 250, paint);
    //stroke (255,255,255);
        //strokeWeight(1);
        //if (sentido>0) {fill(0,0,0);} else {fill(255,255,255);}
        //noFill();
       // ellipse (posicion.x, posicion.y, 10, 10);

    }


    public void rotador(String quiral,int limite, float intensidad ){
        rotacion=false;
        intensidad_rotacion=intensidad;
        limit=limite;
        if (quiral.equals("dextro")  ){rotacion=true;angulo=(float)(Math.PI)/2;}
        if (quiral.equals("levo")  ){rotacion=true;angulo=(float)((Math.PI)/2)*3;}
    }

}
