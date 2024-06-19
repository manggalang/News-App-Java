package org.galangcode.newsapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import org.galangcode.newsapp.models.NewsHeadline;

public class DetailsActivity extends AppCompatActivity{

    NewsHeadline headline;
    TextView text_title, text_author, text_time, text_desc, text_content;
    ImageView img_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        text_title = findViewById(R.id.text_detail_title);
        text_author = findViewById(R.id.text_detail_author);
        text_time = findViewById(R.id.text_detail_time);
        text_desc = findViewById(R.id.text_detail_desc);
        text_content = findViewById(R.id.text_detail_content);
        img_news = findViewById(R.id.img_detail_news);

        headline = (NewsHeadline) getIntent().getSerializableExtra("data");

        if (headline != null) {
            Log.d("MyApp", "Ini detail judul: " + headline.getTitle());
            Log.e("TAG", "Message");
            text_title.setText(headline.getTitle());
            text_author.setText(headline.getAuthor());
            text_time.setText(headline.getPublishedAt());
            text_desc.setText(headline.getDescription());
            text_content.setText(headline.getContent());

            if(headline.getUrlToImage() != null) {
                Picasso.get().load(headline.getUrlToImage()).into(img_news);
            }
        } else {
            Log.d("MyApp", "Headline is null");
        }
    }
}