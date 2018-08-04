package com.example.tarun.fitness_trainer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tarun.fitness_trainer.Common.Common;

public class User_Diet extends AppCompatActivity {

    double BMI ;

    private int currImage=0;

    ImageView imageView;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_user__diet );

        imageView = findViewById ( R.id.smiley_set );
        imageView.setImageResource ( R.drawable.bmi_logo );


        //status bar color

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor( Color.BLACK);
        }

        final TextView view = findViewById ( R.id.Bmi_data );

        view.setText ( Common.currentUser.getBmi () + "");


        Button Diet_plan=findViewById ( R.id.diet_plan );
        Diet_plan.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                final String data = view.getText ().toString ();

                try { BMI = Common.currentUser.getBmi ();
                }
                catch(NumberFormatException nfe) {
                    System.out.println("Error occur" + nfe);
                }

                if(BMI==0)
                {
                    Intent CYBF = new Intent ( User_Diet.this,check_your_bmi_first.class );
                    startActivity ( CYBF );
                }
                else if(BMI>0 && BMI<=18)
                {
                    Intent i1 = new Intent ( User_Diet.this,Gain_Diet.class );
                    startActivity ( i1 );
                }
                else if(18<BMI && BMI<=25)
                {
                    Intent i2 = new Intent ( User_Diet.this,Ideal_Diet.class );
                    startActivity ( i2 );
                }
                else if(BMI>25)
                {
                    Intent i3 = new Intent ( User_Diet.this,Loose_Diet.class );
                    startActivity ( i3 );
                }

            }
        } );



        Button Update_Bmi=findViewById ( R.id.Update_bmi_btn );
        Update_Bmi.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent ( User_Diet.this,activitySettings.class );
                startActivity ( intent );
            }
        } );


        Button USer_Exercises = findViewById ( R.id.exercises_button );
        USer_Exercises.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Intent i = new Intent ( User_Diet.this,EXERCISES_ACTIVITY.class );
                startActivity ( i );
            }
        } );
    }

}
