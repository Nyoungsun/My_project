package com.example.foodduck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    EditText addName, addTel, addMenu1, addMenu2, addReview;
    Button addCancel, addOK;
    RadioGroup category;
    FoodItemList foodItemList;
    RadioButton addRadiobtn[] = new RadioButton[20];
    Integer[] addRadiobtnID = {R.id.addRadiobtn1, R.id.addRadiobtn2, R.id.addRadiobtn3,
            R.id.addRadiobtn4, R.id.addRadiobtn5, R.id.addRadiobtn6, R.id.addRadiobtn7,
            R.id.addRadiobtn8, R.id.addRadiobtn9, R.id.addRadiobtn10, R.id.addRadiobtn11,
            R.id.addRadiobtn12, R.id.addRadiobtn13, R.id.addRadiobtn14, R.id.addRadiobtn15,
            R.id.addRadiobtn16, R.id.addRadiobtn17, R.id.addRadiobtn18, R.id.addRadiobtn19,
            R.id.addRadiobtn20};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_info);

        addCancel = findViewById(R.id.addCancel);
        addOK = findViewById(R.id.addOK);
        addName = findViewById(R.id.addName);
        addMenu1 = findViewById(R.id.addMenu1);
        addMenu2 = findViewById(R.id.addMenu2);
        addTel = findViewById(R.id.addTel);
        addReview = findViewById(R.id.addReview);
        category = findViewById(R.id.category);
        for (int i = 0; i <= 19; i++) {
            addRadiobtn[i] = findViewById(addRadiobtnID[i]);
        }

        addCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onClick(View v) {
        for (int i = 0; i <= 19; i++) {
            if (addRadiobtn[i].isChecked()) {
                foodItemList = new FoodItemList(i + 1);
            }
        }

        if (addName.length() == 0) {
            Toast.makeText(this, "상호명을 입력하세요", Toast.LENGTH_SHORT).show();
        } else if (category.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "종류를 선택해주세요", Toast.LENGTH_SHORT).show();
        } else if (addMenu1.length() == 0) {
            Toast.makeText(this, "최소 1개의 메뉴를 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (addTel.length() == 0) {
            Toast.makeText(this, "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
        } else if (addReview.length() == 0) {
            Toast.makeText(this, "한줄평을 작성해주세요", Toast.LENGTH_SHORT).show();
        } else {
            foodItemList.setTel(addTel.getText().toString().trim());
            foodItemList.setName(addName.getText().toString().trim());
            foodItemList.setMenu(addMenu1.getText().toString().trim());
            foodItemList.setMenu(addMenu2.getText().toString().trim());
            foodItemList.setDate(date());
            foodItemList.setReview(addReview.getText().toString().trim());

            Intent intent = getIntent();
            intent.putExtra("newrest", foodItemList);  //Parcelable 첨부
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    //detail_info.xml - 등록일 설정
    public String date() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fmdate = sdf.format(date);

        return fmdate;
    }
}
