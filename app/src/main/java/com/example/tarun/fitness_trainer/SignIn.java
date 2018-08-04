package com.example.tarun.fitness_trainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tarun.fitness_trainer.Common.Common;
import com.example.tarun.fitness_trainer.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class SignIn extends AppCompatActivity {

    EditText edtphone,edtpassword;
    Button btnSignin;
    ProgressBar bar;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_sign_in );

         bar = findViewById ( R.id.progress );
        bar.setVisibility ( View.GONE );

        //status bar color

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor( Color.BLACK);
        }

        edtphone=(EditText)findViewById(R.id.edtphone);
        edtpassword=(EditText)findViewById(R.id.edtpassword);
        btnSignin=(Button)findViewById(R.id.btnSignIN);
        final CheckBox ckbRemeber = findViewById ( R.id.ckbRemember );


        //Initialize Paper

        Paper.init ( this );

        //INIT FIREBASE


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Common.isConnectedInternet ( getBaseContext ( ) )) {

                    bar.setVisibility ( View.VISIBLE );


                    if (ckbRemeber.isChecked ( )) {
                        Paper.book ( ).write ( Common.USER_KEY , edtphone.getText ( ).toString ( ) );
                        Paper.book ( ).write ( Common.PWD_KEY , edtpassword.getText ( ).toString ( ) );
                    }


                    table_user.addValueEventListener ( new ValueEventListener ( ) {
                        @Override
                        public void onDataChange ( DataSnapshot dataSnapshot ) {

                            //CHECK IF USER NOT EXIST IN DATABASE

                            if (edtphone == null) {
                                Toast.makeText ( SignIn.this , "Please Enter Your Phone No." , Toast.LENGTH_SHORT ).show ( );
                                if (edtpassword == null) {
                                    Toast.makeText ( SignIn.this , "Please Enter Your Password" , Toast.LENGTH_SHORT ).show ( );
                                }
                            } else {
                                if (dataSnapshot.child ( edtphone.getText ( ).toString ( ) ).exists ( )) {
                                    //GET USER INFORMATION
                                    User user = dataSnapshot.child ( edtphone.getText ( ).toString ( ) ).getValue ( User.class );
                                    user.setPhone ( edtphone.getText ( ).toString ( ) );
                                    if (user.getPassword ( ).equals ( edtpassword.getText ( ).toString ( ) )) {

                                        Intent homeintent = new Intent ( SignIn.this , Home.class );
                                        Common.currentUser = user;
                                        startActivity ( homeintent );
                                        finish ( );
                                    } else {
                                        Toast.makeText ( SignIn.this , "WRONG PASSWORD !!!!!!" , Toast.LENGTH_SHORT ).show ( );
                                    }
                                } else {

                                    Toast.makeText ( SignIn.this , "USER NOT EXIST IN DATABASE ...??." , Toast.LENGTH_SHORT ).show ( );
                                }

                            }
                        }

                        @Override
                        public void onCancelled ( DatabaseError databaseError ) {

                        }
                    } );
                }

                else {

                    Toast.makeText ( SignIn.this , "Please , Check your Internet connectivity ......" , Toast.LENGTH_SHORT ).show ( );
                }

            }
        });

    }
}
