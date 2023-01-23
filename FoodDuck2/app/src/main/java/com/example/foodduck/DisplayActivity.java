package com.example.foodduck;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
    TextView displayName, displayMenu1, displayMenu2, displayTel, displayReview, displayDate;
    ImageView displayFood;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_info);

        displayFood = findViewById(R.id.displayFood);
        displayName = findViewById(R.id.displayName);
        displayMenu1 = findViewById(R.id.displayMenu1);
        displayMenu2 = findViewById(R.id.displayMenu2);
        displayTel = findViewById(R.id.displayTel);
        displayReview = findViewById(R.id.displayReview);
        displayDate = findViewById(R.id.date);
        back = findViewById(R.id.back);

        Intent intent = getIntent();
        FoodItemList foodItemList = intent.getParcelableExtra("restinfo");

        displayName.setText(foodItemList.getName());
        displayMenu1.setText(foodItemList.getmenu1());
        displayMenu2.setText(foodItemList.getmenu2());
        displayTel.setText(foodItemList.getTel());
        displayDate.setText(foodItemList.getDate());
        displayReview.setText(foodItemList.getReview());

        if (foodItemList.getCategorynum() == 1)
        { displayFood.setImageResource(R.drawable.korean); }
        else if (foodItemList.getCategorynum() == 2)
        { displayFood.setImageResource(R.drawable.cow); }
        else if (foodItemList.getCategorynum() == 3)
        { displayFood.setImageResource(R.drawable.pig); }
        else if (foodItemList.getCategorynum() == 4)
        { displayFood.setImageResource(R.drawable.tteokbokki); }
        else if (foodItemList.getCategorynum() == 5)
        { displayFood.setImageResource(R.drawable.japan); }
        else if (foodItemList.getCategorynum() == 6)
        { displayFood.setImageResource(R.drawable.sushi); }
        else if (foodItemList.getCategorynum() == 7)
        { displayFood.setImageResource(R.drawable.ramen); }
        else if (foodItemList.getCategorynum() == 8)
        { displayFood.setImageResource(R.drawable.udon); }
        else if (foodItemList.getCategorynum() == 9)
        { displayFood.setImageResource(R.drawable.takoyaki); }
        else if (foodItemList.getCategorynum() == 10)
        { displayFood.setImageResource(R.drawable.chinese); }
        else if (foodItemList.getCategorynum() == 11)
        { displayFood.setImageResource(R.drawable.dimsum); }
        else if (foodItemList.getCategorynum() == 12)
        { displayFood.setImageResource(R.drawable.curry); }
        else if (foodItemList.getCategorynum() == 13)
        { displayFood.setImageResource(R.drawable.pasta); }
        else if (foodItemList.getCategorynum() == 14)
        { displayFood.setImageResource(R.drawable.chicken); }
        else if (foodItemList.getCategorynum() == 15)
        { displayFood.setImageResource(R.drawable.pizza); }
        else if (foodItemList.getCategorynum() == 16)
        { displayFood.setImageResource(R.drawable.hamburger); }
        else if (foodItemList.getCategorynum() == 17)
        { displayFood.setImageResource(R.drawable.salad); }
        else if (foodItemList.getCategorynum() == 18)
        { displayFood.setImageResource(R.drawable.bread); }
        else if (foodItemList.getCategorynum() == 19)
        { displayFood.setImageResource(R.drawable.cake); }
        else if (foodItemList.getCategorynum() == 20)
        { displayFood.setImageResource(R.drawable.coffee); }

    }

    public void onClick(View v) {
        Intent intent = getIntent();
        FoodItemList foodItemList = intent.getParcelableExtra("restinfo");

        switch (v.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.displayTel: //전화번호 누르면 전화연결로 이동
                Intent intentTel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:/" + foodItemList.getTel()));
                startActivity(intentTel);
                break;

            case R.id.displayName: //가게 이름 Textview 누르면 검색페이지로 이동
                Intent intentSearch = new Intent(Intent.ACTION_WEB_SEARCH);
                intentSearch.putExtra(SearchManager.QUERY, displayName.getText().toString());
                startActivity(intentSearch);
                break;
        }
    }
}
