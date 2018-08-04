package com.example.tarun.fitness_trainer;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.tarun.fitness_trainer.Database.Database;
import com.example.tarun.fitness_trainer.Model.Food;
import com.example.tarun.fitness_trainer.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {

    TextView food_name,food_price,food_description ;
    ImageView food_iamge;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnDUMBBELL;

    String foodId="";
    Food currentFood ;

    FirebaseDatabase database;
    DatabaseReference foods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);


        //status bar color

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor( Color.BLACK);
        }


        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Foods");

        btnDUMBBELL = (FloatingActionButton)findViewById(R.id.btnDumbbell);


        btnDUMBBELL.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {


              Uri uri = Uri.parse( currentFood.getLink ());
              Intent open_link = new Intent(Intent.ACTION_VIEW, uri);
              startActivity(open_link);


            }
        } );


        food_description=(TextView)findViewById(R.id.food_description);
        food_name=(TextView)findViewById(R.id.food_name);
        food_iamge=(ImageView) findViewById(R.id.img_food);


        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);

        if(getIntent()!=null)
            foodId = getIntent().getStringExtra("FoodId");

        if(!foodId.isEmpty())
        {
            getDetailFood(foodId);
        }


    }

    private void getDetailFood(String foodId) {

        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentFood = dataSnapshot.getValue(Food.class);

                //Then For Image

                Picasso.with(getBaseContext()).load(currentFood.getImage()).into(food_iamge);
                collapsingToolbarLayout.setTitle(currentFood.getName());
                food_name.setText(currentFood.getName());
                food_description.setText(currentFood.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
