package com.syberkeep.hackthespinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView spinner_imageView, arrow_imageView;
    private Button spinBtn;
    private RotateAnimation rotateAnimation;
    private Random random;
    private EditText name1, name2, name3, name4, name5, name6, name7;
    private TextView who_name;
    private int angle;
    private int pointer = 0;
    private boolean restart = false;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        random = new Random();

        arrayList = new ArrayList<>();

        who_name = (TextView) findViewById(R.id.who_edit_text);
        name1 = (EditText) findViewById(R.id.name1);
        name2 = (EditText) findViewById(R.id.name2);
        name3 = (EditText) findViewById(R.id.name3);
        name4 = (EditText) findViewById(R.id.name4);
        name5 = (EditText) findViewById(R.id.name5);
        name6 = (EditText) findViewById(R.id.name6);
        name7 = (EditText) findViewById(R.id.name7);
        spinner_imageView = (ImageView) findViewById(R.id.spinner_imageView);
        arrow_imageView = (ImageView) findViewById(R.id.arrow_imageView);
        spinBtn = (Button) findViewById(R.id.spin_btn);
        spinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinButtonClicked();
            }
        });
    }

    private int setRandomAngle() {
        int random_gen = random.nextInt(7) + 1;
        int random_rotation = random.nextInt(6) + 1;
        int extra_angle = 45 * random_gen;
        int rotation_angle = 360 * random_rotation;
        pointer = random_gen - 1;   //setting the pointer for displaying the result.
        return rotation_angle + extra_angle;
    }

    private void spinButtonClicked() {
        assignValues();
        if (restart) {
            angle = angle % 360;
            rotateAnimation = new RotateAnimation(angle, 360
                    , Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setDuration(1000);
            rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
            arrow_imageView.startAnimation(rotateAnimation);
            restart = false;
            who_name.setText("");
            spinBtn.setText("Spin");
        } else {
            angle = setRandomAngle();
            rotateAnimation = new RotateAnimation(0, angle
                    , Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setDuration(3000);
            rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
            rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    who_name.setText(arrayList.get(pointer));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            arrow_imageView.startAnimation(rotateAnimation);
            restart = true;
            spinBtn.setText("Reset");
        }
    }

    private void assignValues() {
        arrayList.add(0, name1.getText().toString());
        arrayList.add(1, name2.getText().toString());
        arrayList.add(2, name3.getText().toString());
        arrayList.add(3, name4.getText().toString());
        arrayList.add(4, name5.getText().toString());
        arrayList.add(5, name6.getText().toString());
        arrayList.add(6, name7.getText().toString());
    }

}
