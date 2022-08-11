package com.example.zap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;

public class ItemActivity extends AppCompatActivity {
    private static final String TAG = "ItemActivity";

    public static final String ITEM_KEY = "incoming_item";

    //    private RecyclerView reviewsRecView;
    private TextView txtName, txtPrice, txtDescription;
    private ImageView itemImage;
    private Button btnAddToCart;
    private RelativeLayout firstStarRelLayout, secondStarRelLayout, thirdStarRelLayout;
    private MaterialToolbar toolbar;

    private Items incomingItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        initViews();

        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (null != intent) {
            incomingItem = intent.getParcelableExtra(ITEM_KEY);
            if (null != incomingItem) {
                txtName.setText(incomingItem.getName());
                txtDescription.setText(incomingItem.getDescription());
                txtPrice.setText("₹"+String.valueOf(incomingItem.getPrice()));
                Glide.with(this)
                        .asBitmap()
                        .load(incomingItem.getImageUrl())
                        .into(itemImage);
                //ArrayList<Review> reviews = incomingItem.getReviews();
//                if (null != reviews) {
//                    if (reviews.size()>0) {
//                        ReviewsAdapter adapter = new ReviewsAdapter();
//                        reviewsRecView.setAdapter(adapter);
//                        reviewsRecView.setLayoutManager(new LinearLayoutManager(this));
//                        adapter.setReviews(reviews);
//                    }
//                }

                btnAddToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.addItemToCart(ItemActivity.this, incomingItem);
                        Intent cartIntent = new Intent(ItemActivity.this, CartActivity.class);
                        cartIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(cartIntent);
                    }
                });

//                txtAddReview.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // TODO: 4/24/2020 Show a dialog
//                    }
//                });
            }
        }
    }

    private void initViews() {
        txtName = findViewById(R.id.txtName);
        txtDescription = findViewById(R.id.txtDescription);
        txtPrice = findViewById(R.id.txtPrice);
//        txtAddReview = findViewById(R.id.txtAddReview);
        itemImage = findViewById(R.id.itemImage);
//        firstEmptyStar = findViewById(R.id.firstEmptyStar);
//        firstFilledStar = findViewById(R.id.firstFilledStar);
//        secondEmptyStar = findViewById(R.id.secondEmptyStar);
//        secondFilledStar = findViewById(R.id.secondFilledStar);
//        thirdEmptyStar = findViewById(R.id.thirdEmptyStar);
//        thirdFilledStar = findViewById(R.id.thirdFilledStar);
        btnAddToCart = findViewById(R.id.btnAddToCart);
//        reviewsRecView = findViewById(R.id.reviewsRecView);
//        firstStarRelLayout = findViewById(R.id.firstStarRelLayout);
//        secondStarRelLayout = findViewById(R.id.secondStarRelLayout);
//        thirdStarRelLayout = findViewById(R.id.thirdStarRelLayout);
        toolbar = findViewById(R.id.toolbar);
    }
}