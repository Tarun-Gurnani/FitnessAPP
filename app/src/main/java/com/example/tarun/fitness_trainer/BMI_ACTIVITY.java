package com.example.tarun.fitness_trainer;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Menu;
import android.widget.Toast;
import android.widget.EditText;
import android.view.MenuItem;
import android.content.Intent;

import java.lang.String;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.tarun.fitness_trainer.Common.Common;
import com.example.tarun.fitness_trainer.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BMI_ACTIVITY extends Activity {

    private String IMPERIAL_UNIT;
    private String METRIC_UNIT;
    private final String IMPERIAL_WEIGHT_DESC = "Enter your Weight(lb)";
    private final String METRIC_WEIGHT_DESC = "Enter your Weight(kg)";
    private final String IMPERIAL_HEIGHT_DESC = "Enter your Height(ft)";
    private final String METRIC_HEIGHT_DESC = "Enter your Height(m)";
    private final double CONVERTION_RATE_WEIGHT = 2.20462;
    private final double CONVERTION_RATE_HEIGHT = 3.28084;

    TextView bmi_print;
    Button BMI_DIET_BTN;
    double bmi;
    double BMI;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi__activity);

        IMPERIAL_UNIT = getString(R.string.spnrItemImperial);
        METRIC_UNIT = getString(R.string.spnrItemMetric);

        //sets Units to be used at start up
        setUnitsDesc();

        //status bar color

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags( WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor( Color.BLACK);
        }



        BMI_DIET_BTN=findViewById ( R.id.DIET_PLAN );


        BMI_DIET_BTN.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {


                try { BMI = Common.currentUser.getBmi ();
                }
                catch(NumberFormatException nfe) {
                    System.out.println("Error occur" + nfe);
                }

                if(BMI==0)
                {
                    Intent CYBF = new Intent ( BMI_ACTIVITY.this,check_your_bmi_first.class );
                    startActivity ( CYBF );
                }
                else if(BMI>0 && BMI<=18)
                {
                    Intent i1 = new Intent ( BMI_ACTIVITY.this,Gain_Diet.class );
                    startActivity ( i1 );
                }
                else if(18<BMI && BMI<=25)
                {
                    Intent i2 = new Intent ( BMI_ACTIVITY.this,Ideal_Diet.class );
                    startActivity ( i2 );
                }
                else if(BMI>25)
                {
                    Intent i3 = new Intent ( BMI_ACTIVITY.this,Loose_Diet.class );
                    startActivity ( i3 );
                }

            }
        } );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu);

        //Add settings button
        MenuItem mnuiSettings = menu.add(0, 0, 0, "Settings");
        {
            mnuiSettings.setIcon(R.drawable.gear_blue);
            mnuiSettings.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {

        int itemId = item.getItemId();	//get id of clicked action bar item

        switch(itemId)
        {
            case 0:	Intent settingsIntent = new Intent(getApplicationContext(), activitySettings.class);
                startActivityForResult(settingsIntent, 1);
                break;
        }

        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                //Set new units to be used
                setUnitsDesc();
            }
            else
            {
                Toast.makeText(getBaseContext(), "Any changed settings where not saved!", Toast.LENGTH_LONG).show();
            }
        }

    }

    public String getUnitSettings()
    {
        //Gets units preferences
        SharedPreferences settings = getSharedPreferences("units", MODE_PRIVATE);
        String unitsType = settings.getString("units", METRIC_UNIT);
        return unitsType;

    }

    public void setUnitsDesc()
    {
        //Gets units preferences
        String unitsType = getUnitSettings();

        //Get EditText objects
        EditText txtWeight = (EditText)findViewById(R.id.txtbWeight);
        EditText txtHeight = (EditText)findViewById(R.id.txtHeight);

        //Changes EditText objs hint desc accordingly
        if(unitsType.equalsIgnoreCase(METRIC_UNIT))
        {
            txtWeight.setHint(METRIC_WEIGHT_DESC);
            txtHeight.setHint(METRIC_HEIGHT_DESC);
        }
        else if(unitsType.equalsIgnoreCase(IMPERIAL_UNIT))
        {
            txtWeight.setHint(IMPERIAL_WEIGHT_DESC);
            txtHeight.setHint(IMPERIAL_HEIGHT_DESC);
        }
    }

    public void onClickClear(View view)
    {
        EditText txtWeight = (EditText)findViewById(R.id.txtbWeight);
        txtWeight.setText("");

        EditText txtHeight = (EditText)findViewById(R.id.txtHeight);
        txtHeight.setText("");

    }

    public void onClickCalculate(View view)
    {
        double[] input = getInput();

        //First element of input array will be -1 if any of the validation checks have failed
        if(input[0] != -1)
        {
            //Gets unit prefs
            String unitsType = getUnitSettings();

            //Calculates bmi and passes it to a method that det the category in which the bmi falls and outputs the results
            bmi = calculateBmi(input, unitsType);
            resultOfBmi( bmi);


            ImageView imageView = findViewById ( R.id.image_set );


            if(bmi>0 && bmi<=18)
            {
                imageView.setImageResource ( R.drawable.light_smiley );
            }
            else if(18<bmi && bmi<=25)
            {
                imageView.setImageResource ( R.drawable.wink );
            }
            else if(bmi>25)
            {
                imageView.setImageResource ( R.drawable.heavy_smiley );
            }


            //firebase

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference bmi_of_user = database.getReference("user").child(Common.currentUser.getPhone ()).child("bmi");
            bmi_of_user.setValue (bmi );



        }

    }

    public void onClickCalcIdeal(View view)
    {
        double[] input = getInput();
        double[] idealWeight = new double[2];
        String idealWeightAdjustment;
        String unitsType = getUnitSettings();

        //First element of the array will be -1 if any of the validation checks have failed
        if(input[0] != -1)
        {
            idealWeight = calculateIdealWeight(input[1], unitsType);
            idealWeightAdjustment = calcIdealWeightAdjustment(input[0], idealWeight, unitsType);

            //fetch text view obj
            TextView txtvResults = (TextView)findViewById(R.id.txtVResults);

            //Checks what units to express the results in
            if(unitsType.equalsIgnoreCase(METRIC_UNIT))
            {
                txtvResults.setText("Your Ideal Weight is " + idealWeight[0] + "kg -> " + idealWeight[1] + "kg. \n" + idealWeightAdjustment);
            }
            else
            {
                txtvResults.setText("Your Ideal Weight is " + idealWeight[0] + "lbs -> " + idealWeight[1] + "lbs. \n" + idealWeightAdjustment);
            }
        }
    }

    public void onClickExit(View view)
    {
       Intent exit = new Intent ( BMI_ACTIVITY.this,Home.class );
       startActivity ( exit );
    }

    public void resultOfBmi( final double bmi)
    {
        String clasification = "";

        if(bmi<15)
            clasification = "Starving";
        else if(bmi > 14.9 && bmi < 17.5)
            clasification = "Anorexic";
        else if(bmi > 17.4 && bmi < 18.5)
            clasification = "Underweight";
        else if(bmi > 18.4 && bmi < 25)
            clasification = "Ideal";
        else if(bmi > 24.9 && bmi < 30)
            clasification = "Overweight";
        else if(bmi > 29.9 && bmi < 40)
            clasification = "Obese";
        else if(bmi==40 || bmi > 40)
            clasification = "Morbidly Obese";

        bmi_print = findViewById ( R.id.BMI_tv );
        bmi_print.setText ( "Your BMI: " + bmi + ". You are "+ clasification);



    }

    public double calculateBmi(double[] input, String unitsType)
    {
        double bmi = 0;

        //Converts to Metric units before calc instead of using Imperial formulae
        if(unitsType.equalsIgnoreCase(IMPERIAL_UNIT))
        {
            input[0] = input[0]/CONVERTION_RATE_WEIGHT;
            input[1] = input[1]/CONVERTION_RATE_HEIGHT;
        }

        bmi = input[0]/ (input[1] * input[1]);

        return bmi;

    }

    //calculates ideal weight range ie min Ideal and max Ideal
    public double[] calculateIdealWeight(double height, String unitsType)
    {
        double[] idealWeight = new double[2];

        //Checks units pref and converst height to metric for calculation
        if(unitsType.equalsIgnoreCase(IMPERIAL_UNIT))
            height = height / CONVERTION_RATE_HEIGHT;

        double minWeight = (18.4 * (height*height)); //min Ideal Weight
        double maxWeight = (24.9 * (height*height)); //max Ideal Weight

        //round to 2 deimal places
        idealWeight[0] =  round(2, minWeight);
        idealWeight[1] =  round(2, maxWeight);

        //Check units pref to return calculation in correct units
        if(unitsType.equalsIgnoreCase(IMPERIAL_UNIT))
        {
            idealWeight[0] = round(2, (idealWeight[0] * CONVERTION_RATE_WEIGHT));
            idealWeight[1] = round(2, (idealWeight[1] * CONVERTION_RATE_WEIGHT));
        }

        return idealWeight;
    }

    //Round a value to x decimal places
    public double round(int places, double value)
    {
        double roundedValue = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return roundedValue;
    }

    //calculates how much weight the user should lose to fit ideal weight
    public String calcIdealWeightAdjustment(double actualWeight ,double [] idealWeight, String unitsType)
    {
        String idealWeightAdjustment;
        double minAdjustment;
        double maxAdjustment;

        if(actualWeight > idealWeight[1]) //overweight
        {
            minAdjustment = round(2, (actualWeight - idealWeight[1]));
            maxAdjustment = round(2, (actualWeight - idealWeight[0]));

            if(unitsType.equalsIgnoreCase(IMPERIAL_UNIT))
            {
                idealWeightAdjustment = "You should loose " + minAdjustment + "lbs -> " + maxAdjustment + "lbs.";
            }
            else
            {
                idealWeightAdjustment = "You should loose " + minAdjustment + "kg -> " + maxAdjustment + "kg.";
            }

        }
        else if(actualWeight < idealWeight[0])	//underweight
        {
            minAdjustment = round(2, (idealWeight[0] - actualWeight));
            maxAdjustment = round(2, (idealWeight[1] - actualWeight));

            if(unitsType == IMPERIAL_UNIT)
            {
                idealWeightAdjustment = "You should gain " + minAdjustment + "lbs -> " + maxAdjustment + "lbs.";
            }
            else
            {
                idealWeightAdjustment = "You should gain " + minAdjustment + "kg -> " + maxAdjustment + "kg.";
            }
        }
        else
            idealWeightAdjustment = "You do not need to loose or gain weight, you are ideal.";

        //unit conversion not required because calculation would stay the same irespective of metric or imperial

        return idealWeightAdjustment;
    }

    public double[] getInput()
    {
        double [] input = new double[2];	//weight and hight values from the user

        //retrieves the EditText objs
        EditText txtWeight = (EditText) findViewById(R.id.txtbWeight);
        EditText txtHeight = (EditText) findViewById(R.id.txtHeight);

        //validation
        try
        {
            boolean valid = true;

            //Checks for empty
            if(txtWeight.getText().toString().isEmpty())
            {
                Toast.makeText(getBaseContext(), "You have not entered a value for the Weight field", Toast.LENGTH_SHORT).show();
                valid = false;
            }

            if(txtHeight.getText().toString().isEmpty())
            {
                Toast.makeText(getBaseContext(), "You have not entered a value for the height field", Toast.LENGTH_SHORT).show();
                valid = false;

            }

            double weight = 0;
            double height = 0;

            //Checks that the checks above have been successful then Converts the text input from the use into doubles
            if(valid)
            {
                weight = Double.parseDouble(txtWeight.getText().toString());
                height = Double.parseDouble(txtHeight.getText().toString());
                String unitsType = getUnitSettings();
                String unit;

                //Checks for invalid numbers
                if(weight < 1)
                {
                    if(unitsType.equalsIgnoreCase(IMPERIAL_UNIT))
                        unit = "lbs";
                    else
                        unit = "kg";

                    Toast.makeText(getBaseContext(), "You have specified an incorrect weight. Weight must be more that 1" + unit, Toast.LENGTH_SHORT).show();
                    valid = false;
                }

                if(height < 1)
                {
                    if(unitsType.equalsIgnoreCase(IMPERIAL_UNIT))
                        unit = "ft";
                    else
                        unit = "m";

                    Toast.makeText(getBaseContext(), "You have specified an incorrect height. Height must be more that 1" + unit, Toast.LENGTH_SHORT).show();
                    valid = false;
                }

            }

            if(valid)
            {
                input[0] = weight;
                input[1] = height;

            }
            else
                input[0] = -1;	//sets first element of array to -1 if any of the validation checks failed


        }
        catch(NumberFormatException nfe)
        {
            nfe.printStackTrace();
            Toast.makeText(getBaseContext(), "An Error has occured, an invalid character was detectected in one of the fields. /nPlease contact the developer", Toast.LENGTH_SHORT).show();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return input;
    }




}