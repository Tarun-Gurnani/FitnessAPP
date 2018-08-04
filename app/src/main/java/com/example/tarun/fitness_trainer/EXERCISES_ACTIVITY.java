package com.example.tarun.fitness_trainer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.tarun.fitness_trainer.Common.Common;
import com.example.tarun.fitness_trainer.Interface.ItemClickListener;
import com.example.tarun.fitness_trainer.Model.Category;
import com.example.tarun.fitness_trainer.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class EXERCISES_ACTIVITY extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference category;
    TextView txtFullName;


    RecyclerView recyler_menu;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Category,MenuViewHolder> adapter;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_exercises__activity );

        //status bar color

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor( Color.BLACK);
        }

        //INIT FIREBASE

        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");





        //Load Menu

        recyler_menu=(RecyclerView)findViewById(R.id.recycler_menu);
        recyler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager (this);
        recyler_menu.setLayoutManager(layoutManager);

        loadMenu();


    }
    private void loadMenu() {

        adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,R.layout.menu_item,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {

                viewHolder.txtMenuName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                final Category clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener () {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {


                        //GET Category ID and Send to NEW Activity
                        Intent foodlist = new Intent(EXERCISES_ACTIVITY.this,FoodList.class);

                        foodlist.putExtra("CategoryId",adapter.getRef(position).getKey());

                        startActivity(foodlist);
                    }
                });
            }
        };

        recyler_menu.setAdapter(adapter);
    }


}
