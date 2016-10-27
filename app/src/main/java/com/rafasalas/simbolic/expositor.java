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
            //Color Temporal
            red fondo;
           int width, height;
    //Variables acelerometro

    private PVector gravedad;
    int contador,opacidad, intervalo, transicion, incremento;
    public expositor(Context context){



        //cuidadin
       //registerSensors();
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
        fondo.rozamiento((float)0.009);
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
        Color color = new Color();
        gravedad=new PVector(0,0);

    }
    //acelerometro
    //funciones acelerometro











    protected void Draw(Canvas canvas,int w, int h){
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
            r=rnd.nextInt(255);
            g=rnd.nextInt(255);
            b=rnd.nextInt(255);
            // Color Temporal


        }
        if (contador<transicion){opacidad=opacidad+incremento;}
        if (contador==transicion){opacidad=255;}
        if (contador>intervalo-transicion){opacidad=opacidad-incremento;}


        Cara1.resize(width, height,0.75);
        Globo.resize(width,height,.45,.45,.15);
        Mensaje.resize(width, height,.15,.60,.25
        );

       // canvas.drawColor(0xFFFFFFFF);
        fondopaint.setShader(new RadialGradient(width / 2, height / 2, width , 0xff5881c0,0xff343a7e, Shader.TileMode.MIRROR));
        //fondopaint.setShader(new RadialGradient(width / 2, height / 2, width , 0xffffffff, color.argb(opacidad,r,g,b), Shader.TileMode.MIRROR));
        Cara1.alfa(opacidad);

        //Cara1.colorize(opacidad, r,g,b);
        Globo.alfa(opacidad);

        canvas.drawPaint(fondopaint);
        fondo.acelerar(gravedad);
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


}
