package com.example.tarun.fitness_trainer;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tarun.fitness_trainer.Common.Common;

public class Gain_Diet extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_gain__diet );


        //status bar color


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor( Color.BLACK);
        }




        TextView H1 = findViewById ( R.id.h1 );
        H1.setText( "Here's How to Gain Weight");
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Nabila.ttf");
        H1.setTypeface(face);



        TextView P1 = findViewById ( R.id.p1 );
        P1.setText( "Breakfast");
        P1.setTypeface(face);

        TextView P2 = findViewById ( R.id.p2 );
        P2.setText( "One cup oatmeal with one-half cup reduced-fat milk and one-half cup raisins\n\n" +
                "One cup orange juice\n\n" +
                "One cup black coffee");


        TextView P3 = findViewById ( R.id.p3);
        P3.setText( "Morning Snack");
        P3.setTypeface(face);

        TextView P4 = findViewById ( R.id.p4 );
        P4.setText( "One apple and 24 almonds\n\n" +
                "Glass of water");

        TextView P5 = findViewById ( R.id.p5);
        P5.setText( "Lunch");
        P5.setTypeface(face);

        TextView P6 = findViewById ( R.id.p6 );
        P6.setText( "Sandwich with two large slices of whole grain bread, four slices of lean turkey, two tomato slices, lettuce, and mustard\n\n" +
                "10-ounce glass of reduced-fat milk\n\n" +
                "One baked sweet potato with a pat of butter or margarine");

        TextView P7 = findViewById ( R.id.p7);
        P7.setText( "Afternoon Snack");
        P7.setTypeface(face);

        TextView P8 = findViewById ( R.id.p8 );
        P8.setText( "One protein bar\n\n" +
                "Glass of water");


        TextView P9 = findViewById ( R.id.p9);
        P9.setText( "Dinner");
        P9.setTypeface(face);

        TextView P10 = findViewById ( R.id.p10 );
        P10.setText( "Fresh garden salad with three tablespoons salad dressing\n\n" +
                "Six-ounce salmon filet\n\n" +
                "One cup cooked spinach\n\n" +
                "One-half cup mashed potatoes with butter or margarine\n\n" +
                "One glass of wine (or milk or 100-percent fruit juice)\n\n" +
                "One whole wheat dinner roll");


        TextView P11 = findViewById ( R.id.p11);
        P11.setText( "Nighttime Snack");
        P11.setTypeface(face);


        TextView P12 = findViewById ( R.id.p12 );
        P12.setText( "One-half cup plain yogurt with one-half cup sliced strawberries\n\n" +
                "Glass of water");



    }
}
