package com.rafasalas.simbolic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;

import com.rafasalas.rafalib.composites.red;
import com.rafasalas.rafalib.vectorgraph.vectordraw;

import java.util.Random;

import processing.core.PVector;

/**
 * Created by salas on 20/07/2016.
 */


public class expositor  {
            vectordraw Globo;
            mensaje Mensaje;
            cara Cara1;
            //Color temporal
            int r,g,b;
            Color color;

    float hcomp, inc;


            //Color Temporal
            red fondo;
           int width, height;
            float[] hsv, hsvoscuro, hsv2;




    //Variables acelerometro

    private PVector gravedad;
    int contador,opacidad, intervalo, transicion, incremento;
    public expositor(Context context){



        //cuidadin

        color=new Color();
       color.rgb(r,g,b);
        hsv = new float[3];
        hsvoscuro=new float[3];
        hsv2=new float[3];
        //cuidadin

        int width =context.getResources().getDisplayMetrics().widthPixels;
        int height = context.getResources().getDisplayMetrics().heightPixels;
        Random rnd=new Random();
        String mensaje;
        Cara1=new cara(context);
        Globo=new vectordraw(context);
        Mensaje=new mensaje(context);

      // fondo=new red(-200,-100,10,10,180,150,true, 100,context);
        fondo=new red(-width/5,-height/10,10,10,width/5,height/10,true, height/10,context);
        //fondo.carga_dibujo ("geo", "drawable", "com.rafasalas.simbolic");
        fondo.rozamiento((float)0.015);
        fondo.muelle((float)0.01);
        //fondo.cuerda(50);
        fondo.invertir_masa();

        Cara1.carga();
        mensaje="icon_"+ Integer.toString(rnd.nextInt(79));
        Mensaje.carga(mensaje);
        fondo.carga_dibujo (mensaje, "drawable", "com.rafasalas.simbolic");
        opacidad=0;
        fondo.alfa(100);


        Globo.loadsvg("globo_1","drawable", "com.rafasalas.simbolic");
        contador=0;
        opacidad=0;
        intervalo=250;
        transicion=50;
        incremento=(int)((float)255/(float)transicion);
        // Color (Temporal)
      //Random rnd=new Random();
        r=rnd.nextInt(255);
         g=rnd.nextInt(255);
         b=rnd.nextInt(255);
        //Color color = new Color();
        generador_color();

    }
    //acelerometro
    //funciones acelerometro











    protected void Draw(Canvas canvas,int w, int h, PVector peso){
        Paint fondopaint;
        fondopaint=new Paint();
        Random rnd=new Random();
        String mensaje;
        width=w;
        height=g;
        if (contador==intervalo){
            contador=0;
            opacidad=0;
            Cara1.carga();
            mensaje="icon_"+ Integer.toString(rnd.nextInt(79));
            Mensaje.carga(mensaje);
            fondo.carga_dibujo (mensaje, "drawable", "com.rafasalas.simbolic");
            opacidad=0;
            // Color (Temporal)
            generador_color();
            // Color Temporal


        }
        if (contador<transicion){opacidad=opacidad+incremento;}
        if (contador==transicion){opacidad=255;}
        if (contador>intervalo-transicion){opacidad=opacidad-incremento;}


        Cara1.resize(width, height,0.75,.125,.5);
        Globo.resize(width,height,.45,.45,.15);
        Mensaje.resize(width, height,.15,.60,.25
        );

       // canvas.drawColor(0xFFFFFFFF);
        int colorclaro=Color.HSVToColor(255,hsv);
        int coloroscuro=Color.HSVToColor(255,hsvoscuro);
        hsv[0]=hsv[0]-inc;
        hsvoscuro[0]=hsvoscuro[0]-inc;
        hsv2[0]=hsv2[0]+inc;
        fondopaint.setShader(new RadialGradient(width / 2, height / 2, 2, colorclaro,coloroscuro, Shader.TileMode.MIRROR));
        //fondopaint.setShader(new RadialGradient(width / 2, height / 2, width , 0xffffffff, color.argb(opacidad,r,g,b), Shader.TileMode.MIRROR));
       Cara1.colorizearo(hsv2);
        Cara1.alfa(opacidad);

        //Cara1.colorize(opacidad, r,g,b);
        Globo.alfa(opacidad);

        canvas.drawPaint(fondopaint);
        fondo.acelerar(peso);
        fondo.alfa(100);
        fondo.mostrar_dibujo(canvas, width/85);
        Cara1.dibujar(canvas);
        Globo.dibujar(canvas);
        Mensaje.alfa(opacidad);
        Mensaje.dibujar(canvas);
        update();

        contador++;
    }
   protected void update(){

   }
    void generador_color(){
        Random rnd=new Random();
        //r=rnd.nextInt(255);
        //g=rnd.nextInt(255);
       //b=rnd.nextInt(255);
       // color.rgb(r,g,b);
       // color.RGBToHSV(r,g,b,hsv);
        hsv[0]=(float)rnd.nextInt(360);
        if (hsv[0]>180) {hcomp=hsv[0]-180;} else {hcomp=180-hsv[0];}
        //if (hsv[2]<80){hsv[2]=80;}
        hsv[1]=85;
        hsv[2]=60;
        hsv2[0]=hcomp;
        hsv2[1]=hsv[1];
        hsv2[2]=hsv[2];
        hsvoscuro[0]=hsv[0];
        hsvoscuro[1]=hsv[1];
        hsvoscuro[2]=15;

        inc=(float)(hsv[0]-hcomp)/intervalo;

    }

}
