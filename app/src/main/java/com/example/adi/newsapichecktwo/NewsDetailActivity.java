package com.example.adi.newsapichecktwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsDetailActivity extends AppCompatActivity {
    ImageView news_img;
    TextView title,author,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        news_img=(ImageView)findViewById(R.id.img);
        title=(TextView)findViewById(R.id.title);
        author=(TextView)findViewById(R.id.author);
        description=(TextView)findViewById(R.id.desc);

        news_img.setImageResource(getIntent().getIntExtra("news_img",00));
        title.setText(getIntent().getStringExtra("news_title"));
        author.setText(getIntent().getStringExtra("news_author"));
        description.setText(getIntent().getStringExtra("news_description"));
    }
}
