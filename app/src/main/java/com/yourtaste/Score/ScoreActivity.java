package com.yourtaste.Score;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import com.yourtaste.R;

public class ScoreActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<ListViewItem> adapter;
    private ArrayList<ListViewItem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        listView = (ListView)findViewById(R.id.ListView);
        setLisData();
        adapter = new ListViewAdapter(this, R.layout.layout_score_listitem, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(onItemClickListener());

        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("평가하기") ;
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu) ; return true ;}

    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //final Dialog dialog = new Dialog(ScoreActivity.this);


                ListViewItem movie = (ListViewItem) parent.getAdapter().getItem(position);
                String name =  movie.getName();
                String country = movie.getCountry();
                Float starRate = movie.getRatingStar();


                Toast toast = Toast.makeText(getApplicationContext(),
                        "name"+name+"country"+country+"starRate"+starRate.toString(), Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Log.i("Adapter At", "name"+name+"country"+country+"starRate"+starRate.toString());

                //dialog.show();
            }
        };
    }

    private void setLisData() {
        arrayList = new ArrayList<>();
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.jja),0, "짜장면", "1"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.jjam),0, "짬뽕", "2"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.kkan),0,"깐풍기", "3"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.kkansho),0, "깐쇼새우", "4"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.ma),0, "마파두부", "5"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.nan),0,"난자완스", "6"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.pal),0,"팔보채", "7"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.ra),0,"라조기", "8"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.tang),0,"탕수육", "9"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.u),0,"유산슬", "10"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.ul),0,"울면", "11"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.udong),0,"우동", "12"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.nu),0,"누룽지탕", "13"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.mabab),0,"마파두부밥", "14"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.japche),0,"잡채밥", "15"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.gan),0,"간짜장", "16"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.bokk),0,"볶음밥", "17"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.usan),0,"유산슬", "18"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.yakki),0,"야끼우동", "19"));
        arrayList.add(new ListViewItem(ContextCompat.getDrawable(this, R.drawable.yang),0,"양장피", "20"));


    }




}