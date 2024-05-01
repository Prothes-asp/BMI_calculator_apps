package com.prothesbrand.bmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;


public class MainActivity extends AppCompatActivity {
    private EditText edweight,edheightFt,edheightIn;
    private Button btn,resetbtn;
    private TextView tvDisplay,tvdev;
    private AlertDialog.Builder alertDialogBuilder;
    private Typeface typeface;
    private LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        this.getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        View decorView = getWindow().getDecorView();
        int uiOptons = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptons);
        setContentView(R.layout.activity_main);


        edweight = findViewById(R.id.edweight);
        edheightFt = findViewById(R.id.edheightFt);
        edheightIn = findViewById(R.id.edheightIn);
        btn = findViewById(R.id.btn);
        tvDisplay = findViewById(R.id.tvDisplay);
        tvdev = findViewById(R.id.tvdev);
        animationView = findViewById(R.id.animationView);
        resetbtn = findViewById(R.id.resetbtn);


        typeface = Typeface.createFromAsset(getAssets(),"fonts/monitor.ttf");
        tvdev.setTypeface(typeface);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getWeight = edweight.getText().toString();
                String getheightFt = edheightFt.getText().toString();
                String getheightIn = edheightIn.getText().toString();

                if (getWeight.length()>0 && getheightFt.length()>0 && getheightIn.length() > 0){
                    float weight,heightFt,hfeet,heightIn,hin,totalHeightInMeter,bmi;
                    weight = Float.parseFloat(getWeight);
                    heightFt = Float.parseFloat(getheightFt);
                    heightIn = Float.parseFloat(getheightIn);
                    if(heightFt <= 10 ){
                        hfeet = heightFt * 0.3048f;
                        hin = heightIn * 0.0254f;
                        totalHeightInMeter = hfeet + hin;
                        if (weight <= 100){
                            bmi = (weight/(totalHeightInMeter * totalHeightInMeter));
                            if (bmi < 18.5f){
                                tvDisplay.setText("Your BMI : "+bmi + "\n\nUnderweight\nLess than : 18.5");
                                tvDisplay.setTextColor(getResources().getColor(R.color.yellow));
                                animationView.setAnimation(R.raw.warning_yellow);
                                animationView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                tvdev.setTextColor(getResources().getColor(R.color.yellow));

                            }
                            if (bmi > 18.5f && bmi<24.9f){
                                tvDisplay.setText("Your BMI : "+bmi + "\n\nYour BMI is Normal\nNormal\t: 18.5 – 24.9");
                                tvDisplay.setTextColor(getResources().getColor(R.color.green));
                                animationView.setAnimation(R.raw.successfull);
                                animationView.setScaleType(ImageView.ScaleType.FIT_XY);
                                tvdev.setTextColor(getResources().getColor(R.color.green));
                            }
                            if (bmi>24.9f && bmi<30f){
                                tvDisplay.setText("Your BMI : "+bmi + "\n\nOverweight\n25.0 – 29.9");
                                tvDisplay.setTextColor(getResources().getColor(R.color.red));
                                animationView.setAnimation(R.raw.warning_red);
                                animationView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                tvdev.setTextColor(getResources().getColor(R.color.red));
                            }
                            if (bmi>30f){
                                tvDisplay.setText("Your BMI : "+bmi + "\n\nObese\nGreater than 30.0");
                                tvDisplay.setTextColor(getResources().getColor(R.color.red));
                                animationView.setAnimation(R.raw.warning_red);
                                animationView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                tvdev.setTextColor(getResources().getColor(R.color.red));
                            }
                        }else{
                            edweight.setError("Max Value 100");
                            tvDisplay.setText("Something Wrong");
                        }
                    }else{
                        edheightFt.setError("Max Value 10");
                        tvDisplay.setText("Something Wrong");
                    }

                }else if (getWeight.length()<=0 && getheightFt.length()<=0 && edheightIn.length()<=0){
                    Toast.makeText(MainActivity.this, "All Field is Empty", Toast.LENGTH_SHORT).show();
                    edweight.setError("Empty");
                    edheightFt.setError("Empty");
                    edheightIn.setError("Empty");
                }else if(getWeight.length()<=0){
                    edweight.setError("Empty");
                }else if(getheightFt.length()<=0){
                    edheightFt.setError("Empty");
                }else if(edheightIn.length()<=0){
                    edheightIn.setError("Empty");
                }






             }
        });


        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edweight.setText(null);
                edheightFt.setText(null);
                edheightIn.setText(null);
                tvDisplay.setText(R.string.calculate_your_bmi);
                tvDisplay.setTextColor(getResources().getColor(R.color.white));
                tvdev.setText(R.string.developer);
                tvdev.setTextColor(getResources().getColor(R.color._36ffffff));
                animationView.setAnimation(R.raw.bmi);
                animationView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                edweight.setError(null);
                edheightFt.setError(null);
                edheightIn.setError(null);
            }
        });


    }


    /** @noinspection deprecation*/
    @Override
    public void onBackPressed() {
        if (isTaskRoot()){
            alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Warning !");
            alertDialogBuilder.setMessage("Do you want exit this app?");
            alertDialogBuilder.setIcon(R.drawable.warning_svgrepo_com);
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }else {
            super.onBackPressed();
        }
    }
}