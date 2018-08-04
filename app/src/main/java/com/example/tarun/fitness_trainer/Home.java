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

import com.example.tarun.fitness_trainer.Common.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

public class Home extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference category;
    TextView txtFullName;
    double BmI;

    Button Bmi_btn , DietPlan_btn , Exercises_btn;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_home );

        //status bar color


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor( Color.BLACK);
        }



        //INIT FIREBASE

        database = FirebaseDatabase.getInstance();
        category = database.getReference("user");

        //set Name for User

        txtFullName=findViewById(R.id.textName);
        txtFullName.setText( "Welcome \n" +Common.currentUser.getName() + "\n  check your .......");
        TextView logout = findViewById ( R.id.logout_tv );

        //Set font
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Nabila.ttf");
        txtFullName.setTypeface(face);
        logout.setTypeface(face);


        //Buttonn id's
        Bmi_btn=findViewById ( R.id.BMI_btn );
        DietPlan_btn=findViewById ( R.id.Diet_btn );
        Exercises_btn=findViewById ( R.id.EXERCISE_btn );

        Bmi_btn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Intent bmi = new Intent ( Home.this,activitySettings.class );
                startActivity ( bmi );
            }
        } );



        try { BmI = Common.currentUser.getBmi ();
        }
        catch(NumberFormatException nfe) {
            System.out.println("Error occur" + nfe);
        }


        DietPlan_btn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                //DIET PLANS if Bmi != 0

                if(BmI == 0)
                {
                    Intent zero_bmi = new Intent ( Home.this,check_your_bmi_first.class);
                    startActivity ( zero_bmi );
                }
                else
                {
                    Intent intent = new Intent ( Home.this,User_Diet.class );
                    startActivity (  intent );
                }


                //if Bmi = 0


            }
        } );


        Exercises_btn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Intent bmi = new Intent ( Home.this,EXERCISES_ACTIVITY.class );
                startActivity ( bmi );
            }
        } );

        logout.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Paper.book ().destroy ();

                Intent signin = new Intent ( Home.this,SignIn.class );
                signin.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity ( signin );
            }
        } );
    }
}
