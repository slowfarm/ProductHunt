package eva.android.com.producthunt.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import eva.android.com.producthunt.R;
import eva.android.com.producthunt.models.Post;
import eva.android.com.producthunt.models.ScreenshotUrl;


public class PostDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        TextView voteCount = findViewById(R.id.vote_count);
        ImageView screenshot = findViewById(R.id.screenshot);
        Button getItBtn = findViewById(R.id.get_it_btn);

        Post post = new Post();
        post.setName("Name");
        post.setTagline("description");
        post.setVotesCount(32);
        post.setScreenshotUrl(new ScreenshotUrl());
        post.getScreenshotUrl().set850px("asdasd");
        post.setRedirectUrl("http://www.google.com");

        name.setText(post.getName());
        description.setText(post.getTagline());
        voteCount.setText(String.valueOf(post.getVotesCount()));

        Picasso.with(this).load(post.getScreenshotUrl().get850px()).into(screenshot);

        getItBtn.setOnClickListener(view-> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(post.getRedirectUrl()));
            startActivity(browserIntent);
        });

    }
}
