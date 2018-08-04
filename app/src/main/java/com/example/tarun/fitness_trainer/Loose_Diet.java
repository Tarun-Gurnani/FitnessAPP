package com.example.tarun.fitness_trainer;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Loose_Diet extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_loose__diet );

        //status bar color


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor( Color.BLACK);
        }




        TextView H1 = findViewById ( R.id.h1 );
        H1.setText( "Here's How to Loose Weight");
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Nabila.ttf");
        H1.setTypeface(face);

        TextView P1 = findViewById ( R.id.p1 );
        P1.setText( "Breakfast");
        P1.setTypeface(face);

        TextView P2 = findViewById ( R.id.p2 );
        P2.setText( "One cup oatmeal\n\n" +
                "One-half cup non-fat milk\n\n" +
                "One tablespoon honey\n\n" +
                "One-half cup blueberries\n\n" +
                "One cup plain coffee or tea as a beverage");


        TextView P3 = findViewById ( R.id.p3);
        P3.setText( "Lunch");
        P3.setTypeface(face);

        TextView P4 = findViewById ( R.id.p4 );
        P4.setText( "Two slices 100-percent whole grain bread, deli sliced turkey breast, tomato slice, lettuce and one tablespoon mustard\n\n" +
                "One-half cup sliced carrots\n\n" +
                "Water as a beverage");


        TextView P5 = findViewById ( R.id.p5);
        P5.setText( "Dinner");
        P5.setTypeface(face);

        TextView P6 = findViewById ( R.id.p6 );
        P6.setText( "Three ounces baked salmon\\nn" +
                "One cup green beans\n\n" +
                "Salad with one cup raw spinach, five cherry tomatoes and one-half cup broccoli florets with lemon juice as a dressing\n\n" +
                "Water with a slice of lemon as a beverage");

        TextView P7 = findViewById ( R.id.p7);
        P7.setText( "Snacks");
        P7.setTypeface(face);

        TextView P8 = findViewById ( R.id.p8 );
        P8.setText( "One apple with 12 almonds\n\n" +
                "Several glasses of water\n\n" +
                "One cup non-fat milk\n\n" +
                "One-half cup plain yogurt with one tablespoon honey\n\n" +
                "One cup strawberries.");

        TextView P9 = findViewById ( R.id.p9);
        P9.setText( "Nutrition Information");
        P9.setTypeface(face);

        TextView P10 = findViewById ( R.id.p10 );
        P10.setText( "Total Calories - 1,215\n\n" +
                "Total Fat - 17.7 percent (25 grams)\n\n" +
                "Total Protein - 23 percent (72 grams)\n\n" +
                "Total Carbohydrates - 59.3 percent (185 grams)\n\n" +
                "Sodium - 1,402 milligrams\n\n" +
                "Sugar - 107 grams\n\n" +
                "Cholesterol - 94 milligrams\n\n" +
                "Saturated Fat - 5.0 grams\n\n" +
                "Fiber - 28 grams");

    }
}
