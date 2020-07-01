package com.example.noteme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class slider extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dots;

    TextView[] mdots;

    SliderAdapter sliderAdapter;

    Button nextbutton ,skip ;
    Button prevbutton;
    int currentpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        viewPager=(ViewPager) findViewById(R.id.viewPager);
        dots=(LinearLayout) findViewById(R.id.dots);
        nextbutton=findViewById(R.id.nextbutton);
        prevbutton=findViewById(R.id.prevbutton);
        skip=findViewById(R.id.skip);
        skip.setText("Skip");

        sliderAdapter = new SliderAdapter(this);

        viewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        viewPager.addOnPageChangeListener(viewListener);

        //OnClick Listeners

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nextbutton.getText()== "Finish"){
                    Toast.makeText(slider.this, "FINISH", Toast.LENGTH_SHORT).show();
                }
                viewPager.setCurrentItem(currentpage +1);
            }
        });

        prevbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentpage - 1);
            }
        });

    }

    public void addDotsIndicator (int position) {

        mdots = new TextView[3];
        dots.removeAllViews();

        for (int i=0; i<mdots.length; i++){

                mdots[i] =new TextView(this);
                mdots[i].setText(Html.fromHtml("&#8226;",0));
                mdots[i].setTextSize(35);
                mdots[i].setTextColor(getResources().getColor(R.color.colorGreen,getTheme()));

                dots.addView(mdots[i]);
        }

        if (mdots.length > 0){
            mdots[position].setTextColor(getResources().getColor(R.color.colordarkgreen,getTheme()));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);
            currentpage = position;

            if(position==0){
                nextbutton.setEnabled(true);
                prevbutton.setEnabled(false);
                prevbutton.setVisibility(View.INVISIBLE);

                nextbutton.setText("Next");
                prevbutton.setText("");
            } else if(position==mdots.length-1){
                nextbutton.setEnabled(true);
                prevbutton.setEnabled(true);
                prevbutton.setVisibility(View.VISIBLE);

                nextbutton.setText("Finish");
                prevbutton.setText("Back");
            } else{
                nextbutton.setEnabled(true);
                prevbutton.setEnabled(true);
                prevbutton.setVisibility(View.VISIBLE);

                nextbutton.setText("Next");
                prevbutton.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}