package com.example.tarun.fitness_trainer;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Ideal_Diet extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_ideal__diet );


        //status bar color


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor( Color.BLACK);
        }




        TextView H1 = findViewById ( R.id.h1 );
        H1.setText( "Here's Halthy Fitness Diet");
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Nabila.ttf");
        H1.setTypeface(face);

        TextView P1 = findViewById ( R.id.p1 );
        P1.setText( "Breakfast");
        P1.setTypeface(face);

        TextView P2 = findViewById ( R.id.p2 );
        P2.setText( "One grapefruit\n\n" +
                "Two poached eggs (or fried in a non-stick pan)\n\n" +
                "Two slices whole grain toast with one pat butter each\n\n" +
                "One cup low-fat milk\n\n" +
                "One cup black coffee or herbal tea");


        TextView P3 = findViewById ( R.id.p3);
        P3.setText( "Morning Snack");
        P3.setTypeface(face);

        TextView P4 = findViewById ( R.id.p4 );
        P4.setText( "One banana\n\n" +
                "One cup plain yogurt with two tablespoons honey\n\n" +
                "Glass of water");

        TextView P5 = findViewById ( R.id.p5);
        P5.setText( "Lunch");
        P5.setTypeface(face);

        TextView P6 = findViewById ( R.id.p6 );
        P6.setText( "Chicken breast (6-ounce portion), baked or roasted (not breaded or fried)\n\n" +
                "Large garden salad with tomato and onion with one cup croutons, topped with one tablespoon oil and vinegar (or salad dressing)\n\n" +
                "Glass of water ");

        TextView P7 = findViewById ( R.id.p7);
        P7.setText( "Afternoon Snack");
        P7.setTypeface(face);

        TextView P8 = findViewById ( R.id.p8 );
        P8.setText( "One cup carrot slices\n\n" +
                "Three tablespoon hummus\n\n" +
                "One-half piece of pita bread\n\n" +
                "Glass of water or herbal tea");

        TextView P9 = findViewById ( R.id.p9);
        P9.setText( "Dinner");
        P9.setTypeface(face);

        TextView P10 = findViewById ( R.id.p10 );
        P10.setText( "One cup steamed broccoli\n\n" +
                "One cup brown rice\n\n" +
                "Halibut (four-ounce portion)\n\n" +
                "Small garden salad with one cup spinach leaves, tomato, and onion topped with two tablespoons oil and vinegar or salad dressing\n\n" +
                "One glass white wine (regular or dealcoholized)\n\n" +
                "Sparkling water with lemon or lime slice");


        TextView P11 = findViewById ( R.id.p11);
        P11.setText( "Nighttime Snack");
        P11.setTypeface(face);


        TextView P12 = findViewById ( R.id.p12 );
        P12.setText( "One cup blueberries\n\n" +
                "Two tablespoons whipped cream (the real stuff—whip your own or buy in a can)\n\n" +
                "Glass of water");


    }
}
