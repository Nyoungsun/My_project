package com.example.foodduck;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<String> restaurant_data = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayList<FoodItemList> restaurant_list = new ArrayList<FoodItemList>();
    ListView listView;
    TextView itemCount;

    //final int REST_INFO = 21;
    final int NEW_REST = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_menu);

        itemCount = findViewById(R.id.itemCount);

//        restaurant_data = getStringArrayPref(getApplicationContext(), JSON); //리스트 저장

        setListView();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

    }

    //추가 버튼 눌렀을 시
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        intent.putExtra("restlist", restaurant_data);
        startActivityForResult(intent, NEW_REST);
    }

    public void setListView() {
        listView = findViewById(R.id.listview);
        //list 배열 만들고
        //어댑터를 만들어서 listview 생성
        adapter = new ArrayAdapter<String>(this, R.layout.list_layout, restaurant_data);
        listView.setAdapter(adapter);
        //리스트 뷰 클릭시 상세정보가 나타남
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int list_item, long id) {
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                FoodItemList foodItemList = restaurant_list.get(list_item);
                intent.putExtra("restinfo", foodItemList);
                startActivity(intent);
            }
        });

        //길게 눌렀을때 삭제
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int list_item, long id) {
                //삭제할것인지 묻는 대화상자 나타남
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("삭제 확인")
                        .setIcon(R.drawable.duck_img)
                        .setMessage("선택한 맛집을 삭제하시겠습니까?")
                        .setNegativeButton("취소", null)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //삭제 클릭시
                                restaurant_data.remove(list_item);
                                restaurant_list.remove(list_item);
                                adapter.notifyDataSetChanged();
                                itemCount.setText("맛집 " + restaurant_data.size() + "개 있어요");
                                Snackbar.make(view, "삭제되었습니다", 2000).show();
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NEW_REST) {
            if (resultCode == RESULT_OK) {
                FoodItemList foodItemList = data.getParcelableExtra("newrest");
                restaurant_data.add(foodItemList.getName());
                restaurant_list.add(foodItemList);
                adapter.notifyDataSetChanged();
                itemCount.setText("맛집 " + restaurant_data.size() + "개 있어요");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //네비게이션메뉴 기능
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Mchicken) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "치킨 맛집");
            startActivity(intent);
        } else if (id == R.id.MPizza) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "피자 맛집");
            startActivity(intent);
        } else if (id == R.id.Mtteokbokki) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "떡볶이 맛집");
            startActivity(intent);
        } else if (id == R.id.Mjapan) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "일식 맛집");
            startActivity(intent);
        } else if (id == R.id.MSushi) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "초밥 맛집");
            startActivity(intent);
        } else if (id == R.id.Mramen) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "라멘 맛집");
            startActivity(intent);
        } else if (id == R.id.Mudon) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "우동 맛집");
            startActivity(intent);
        } else if (id == R.id.Mtakoyaki) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "타코야끼 맛집");
            startActivity(intent);
        } else if (id == R.id.MHamburger) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "버거 맛집");
            startActivity(intent);
        } else if (id == R.id.Mpasta) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "파스타 맛집");
            startActivity(intent);
        } else if (id == R.id.MKorean) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "한식 맛집");
            startActivity(intent);
        } else if (id == R.id.MChinese) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "중식 맛집");
            startActivity(intent);
        } else if (id == R.id.Mdimsum) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "딤섬 맛집");
            startActivity(intent);
        } else if (id == R.id.Mcurry) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "카레 맛집");
            startActivity(intent);
        } else if (id == R.id.Mbread) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "빵 맛집");
            startActivity(intent);
        } else if (id == R.id.Msalad) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "샐러드 맛집");
            startActivity(intent);
        } else if (id == R.id.Mcow) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "소고기 맛집");
            startActivity(intent);
        } else if (id == R.id.Mpig) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "돼지고기 맛집");
            startActivity(intent);
        } else if (id == R.id.Mcake) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "케이크 맛집");
            startActivity(intent);
        } else if (id == R.id.Mcoffee) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, "커피 맛집");
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //-------------------------------리스트 저장-----------------------------------------
//    private void setStringArrayPref(Context context, String key, ArrayList<?> values) {
//        //저장위치: Device File Explorer를 이용하여 data/data/패키지명
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = prefs.edit();
//        JSONArray a = new JSONArray();
//
//        for (int i = 0; i < values.size(); i++) {
//            a.put(values.get(i));
//        }
//
//        if (!values.isEmpty()) {
//            editor.putString(key, a.toString());
//        } else {
//            editor.putString(key, null);
//        }
//
//        editor.apply();
//    }
//
//    private ArrayList getStringArrayPref(Context context, String key) {
//
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        String json = prefs.getString(key, null);
//        ArrayList urls = new ArrayList();
//
//        if (json != null) {
//            try {
//                JSONArray a = new JSONArray(json);
//
//                for (int i = 0; i < a.length(); i++) {
//                    String url = a.optString(i);
//                    urls.add(url);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return urls;
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        setStringArrayPref(getApplicationContext(), JSON, restauran t_data);
//    }
}