package com.example.tarun.fitness_trainer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class check_your_bmi_first extends AppCompatActivity {


    TextView Note;
    Button First_bmi;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_check_your_bmi_first );



        //status bar color


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor( Color.BLACK);
        }



        Note=findViewById ( R.id.note );
        Note.setText ( "First , You have to check your BMI , Then you should check your Diet-Plan" );
        //Set font
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Nabila.ttf");
        Note.setTypeface(face);


        First_bmi=findViewById ( R.id.BMI_BTN );
        First_bmi.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Intent i4 = new Intent ( check_your_bmi_first.this,activitySettings.class );
                startActivity ( i4 );
            }
        } );


    }
}
