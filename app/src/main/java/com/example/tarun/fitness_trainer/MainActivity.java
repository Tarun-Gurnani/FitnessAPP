package com.example.tarun.fitness_trainer;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tarun.fitness_trainer.Common.Common;
import com.example.tarun.fitness_trainer.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    Button btnSignup,btnSignIn;
    TextView textslogan1,textslogan2 ,textslogan3,textslogan4;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );



        btnSignIn=(Button)findViewById(R.id.btnSignIN);
        btnSignup=(Button)findViewById(R.id.btnSignUp);
        textslogan1=(TextView)findViewById(R.id.txtslogan1);
        textslogan2=(TextView)findViewById(R.id.txtslogan2);
        textslogan3=(TextView)findViewById(R.id.txtslogan3);
        textslogan4=(TextView)findViewById(R.id.txtslogan4);
        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Nabila.ttf");
        textslogan1.setTypeface(face);
        textslogan2.setTypeface(face);
        textslogan3.setTypeface(face);
        textslogan4.setTypeface(face);

        //status bar color

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }

        //initialize paper
        Paper.init ( this );


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Signup = new Intent(MainActivity.this,SignUp.class);
                startActivity(Signup);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Signin = new Intent(MainActivity.this,SignIn.class);
                startActivity(Signin);
            }
        });

        //Check Checkbox , if remeber then directly open LOGIN activity
        String user = Paper.book ().read ( Common.USER_KEY );
        String pwd = Paper.book ().read ( Common.PWD_KEY );

        if(user != null && pwd !=  null)
        {
            if(!user.isEmpty () && !pwd.isEmpty ())
                login(user,pwd);
        }

    }

    private void login ( final String phone , final String pwd ) {


        //INITIALIZE  FIREBASE
        FirebaseDatabase database = FirebaseDatabase.getInstance ( );
        final DatabaseReference table_user = database.getReference ( "user" );

        final ProgressDialog mDialog = new ProgressDialog ( MainActivity.this );
        mDialog.setMessage ( "PLEASE WAITING ....." );
        mDialog.show ( );

        table_user.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange ( DataSnapshot dataSnapshot ) {

                //CHECK IF USER NOT EXIST IN DATABASE

                if (dataSnapshot.child ( phone ).exists ( )) {
                    //GET USER INFORMATION
                    mDialog.dismiss ( );
                    User user = dataSnapshot.child ( phone ).getValue ( User.class );
                    user.setPhone ( phone );
                    if (user.getPassword ( ).equals ( pwd )) {

                        Intent homeintent = new Intent ( MainActivity.this , Home.class );
                        Common.currentUser = user;
                        startActivity ( homeintent );
                        finish ( );
                    } else {
                        Toast.makeText ( MainActivity.this , "WRONG PASSWORD !!!!!!" , Toast.LENGTH_SHORT ).show ( );
                    }
                } else {

                    mDialog.dismiss ( );
                    Toast.makeText ( MainActivity.this , "USER NOT EXIST IN DATABASE ...??." , Toast.LENGTH_SHORT ).show ( );
                }

            }

            @Override
            public void onCancelled ( DatabaseError databaseError ) {

            }
        } );

    }



    }
