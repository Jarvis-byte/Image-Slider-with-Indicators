package com.example.imageslider;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager2 imageContainer;
    SliderAdapter adapter;
    int list[];
    TextView[] dots;
    LinearLayout layout;
    Button btn_next, btn_previous;
    List<Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "https://image.freepik.com/free-vector/flat-floral-facebook-frame-profile-pic_23-2148962057.jpg",
                url1="https://img.freepik.com/free-photo/side-view-female-with-headphones_23-2148543333.jpg?size=338&ext=jpg",
                url2="https://img.freepik.com/free-photo/young-handsome-man-with-beard-isolated-wall-pointing-finger-side-presenting-product_1368-114226.jpg?size=338&ext=jpg",
                url3="https://image.freepik.com/free-photo/pleasant-looking-serious-man-stands-profile-has-confident-expression-wears-casual-white-t-shirt_273609-16959.jpg",
                url4="https://image.freepik.com/free-photo/amazing-lady-standing-isolated-looking-aside_171337-36087.jpg"
                        ;
        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_previous);
        imageContainer = findViewById(R.id.image_container);
        layout = findViewById(R.id.dots_container);

        dots = new TextView[5];

        list = new int[5];
        list[0] = getResources().getColor(R.color.black);
        list[1] = getResources().getColor(R.color.red);
        list[2] = getResources().getColor(R.color.green);
        list[3] = getResources().getColor(R.color.yellow);
        list[4] = getResources().getColor(R.color.orange);
        data = new ArrayList<>();


        data.add(new Data(url1));
        data.add(new Data(url));
        data.add(new Data(url4));
        data.add(new Data(url3));
        data.add(new Data(url2));

        Log.i("LISTSIZE", String.valueOf(data.size()));
        adapter = new SliderAdapter(data);
        imageContainer.setAdapter(adapter);

        setIndicators();

        imageContainer.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                selectedDots(position);
                super.onPageSelected(position);
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("CURRENT", String.valueOf(imageContainer.getCurrentItem()));
                imageContainer.setCurrentItem(getItem(+1), true);

            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageContainer.setCurrentItem(getItem(-1), true);
            }
        });
    }

    private int getItem(int i) {
        if (imageContainer.getCurrentItem() == data.size()-1) {
            return 0;
        }
        return imageContainer.getCurrentItem() + i;
    }

    private void selectedDots(int position) {
        for (int i = 0; i < dots.length; i++) {
            if (i == position) {
                dots[i].setTextColor(list[position]);
            } else {
                dots[i].setTextColor(getResources().getColor(R.color.grey));
            }
        }
    }

    private void setIndicators() {
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#9679;"));
            dots[i].setTextSize(18);
            layout.addView(dots[i]);
        }
    }
}