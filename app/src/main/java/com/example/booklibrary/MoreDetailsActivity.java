package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MoreDetailsActivity extends AppCompatActivity {
    private TextView name;
    private TextView author;
    private TextView pages;
    private TextView language;
    private TextView longDescription;
    private ImageView bookImage;

    private Book incomingBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra(getString(R.string.bundle));
            incomingBook = bundle.getParcelable(getString(R.string.book));

            init();
            initUI();

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    private void init() {
        name = findViewById(R.id.nameForMoreDetails);
        author = findViewById(R.id.author);
        pages = findViewById(R.id.pages);
        language = findViewById(R.id.language);
        longDescription = findViewById(R.id.longDesc);
        bookImage = findViewById(R.id.imageForMoreDetails);
    }

    private void initUI() {
        name.setText(incomingBook.getName());
        author.setText(incomingBook.getAuthor());
        pages.setText(incomingBook.getPages() + " pages");
        longDescription.setText((incomingBook.getLongDescription()));
        language.setText(incomingBook.getLanguage());

        Glide.with(this)
                .asBitmap()
                .load(incomingBook.getImageUrl())
                .into(bookImage);
    }
}