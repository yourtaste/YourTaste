package com.yourtaste.Home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.yourtaste.R;


public class RecommendFragment extends Fragment

{

    private static final String TAG = "RecommendFargment";

    private ListView userList;
    private ListView_Adapter adapter;

    //firebase
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recommend, null) ;
        adapter = new ListView_Adapter(getActivity().getApplicationContext()) ;

        setupFirebaseAuth();

        userList = (ListView) view.findViewById(R.id.user_list) ;
        userList.setAdapter(adapter) ;
        String result="100";


        FirebaseUser user = mAuth.getCurrentUser();

        String email = user.getEmail();
        String score ="";


        String res="";
        String useridbody="";

        try {
            res  = new CustomTask().execute(user.getEmail()).get();
            Document doc1 = Jsoup.parse(res);
            useridbody = doc1.body().text();
            System.out.print(useridbody);
        }catch (Exception e) {}


        result=useridbody;
        //result ="6";

        try {
            score  = new CustomTask1().execute(result).get();
            System.out.print(score);

        }catch (Exception e) {}

        //html parsing
        Document doc = Jsoup.parse(score);
        String body = doc.body().text();
        String[] ReScore = body.split("\\s");

        System.out.print(ReScore);



        ArrayList<ListView_User> list = new ArrayList<>();


        ListView_User u1 = new ListView_User(getResources().getDrawable(
                R.drawable.jja), "짜장면","" );
        list.add(u1);
        ListView_User u2 = new ListView_User(getResources().getDrawable(
                R.drawable.jjam), "짬뽕","" );
        list.add(u2);
        ListView_User u3 = new ListView_User(getResources().getDrawable(
                R.drawable.kkan), "깐풍기","" );
        list.add(u3);
        ListView_User u4 = new ListView_User(getResources().getDrawable(
                R.drawable.kkansho), "깐쇼새우","" );
        list.add(u4);
        ListView_User u5 = new ListView_User(getResources().getDrawable(
                R.drawable.ma), "마파두부","" );
        list.add(u5);
        ListView_User u6 = new ListView_User(getResources().getDrawable(
                R.drawable.nan), "난자완스","" );
        list.add(u6);
        ListView_User u7 = new ListView_User(getResources().getDrawable(
                R.drawable.pal), "팔보채","" );
        list.add(u7);
        ListView_User u8 = new ListView_User(getResources().getDrawable(
                R.drawable.ra), "라조기","" );
        list.add(u8);
        ListView_User u9 = new ListView_User(getResources().getDrawable(
                R.drawable.tang), "탕수육","" );
        list.add(u9);
        ListView_User u10 = new ListView_User(getResources().getDrawable(
                R.drawable.usan), "유산슬","" );
        list.add(u10);
        ListView_User u11 = new ListView_User(getResources().getDrawable(
                R.drawable.ul), "울면","" );
        list.add(u11);
        ListView_User u12 = new ListView_User(getResources().getDrawable(
                R.drawable.udong), "우동","" );
        list.add(u12);
        ListView_User u13 = new ListView_User(getResources().getDrawable(
                R.drawable.nu), "누룽지탕","" );
        list.add(u13);
        ListView_User u14 = new ListView_User(getResources().getDrawable(
                R.drawable.mabab), "마파두부덮밥","" );
        list.add(u14);
        ListView_User u15 = new ListView_User(getResources().getDrawable(
                R.drawable.japche), "잡채밥","" );
        list.add(u15);
        ListView_User u16 = new ListView_User(getResources().getDrawable(
                R.drawable.gan), "간짜장","" );
        list.add(u16);
        ListView_User u17 = new ListView_User(getResources().getDrawable(
                R.drawable.bokk), "볶음밥","" );
        list.add(u17);
        ListView_User u18 = new ListView_User(getResources().getDrawable(
                R.drawable.usan), "유산슬","" );
        list.add(u18);
        ListView_User u19 = new ListView_User(getResources().getDrawable(
                R.drawable.yakki), "야끼우동","" );
        list.add(u19);
        ListView_User u20 = new ListView_User(getResources().getDrawable(
                R.drawable.yang), "양장피","" );
        list.add(u20);



        int[] n = new int[10];
        //" float number = Float.parseFloat(numberAsString);



        n[0] =  (int)Float.parseFloat(ReScore[10]);
        n[1] =  (int)Float.parseFloat(ReScore[11]);
        n[2] =  (int)Float.parseFloat(ReScore[12]);
        n[3] =  (int)Float.parseFloat(ReScore[13]);
        n[4] =  (int)Float.parseFloat(ReScore[14]);


        adapter.add(list.get(n[0]));
        list.get(n[0]).setUserPhoneNumber(ReScore[15]);

        adapter.add(list.get(n[1]));
        list.get(n[1]).setUserPhoneNumber(ReScore[16]);

        adapter.add(list.get(n[2]));
        list.get(n[2]).setUserPhoneNumber(ReScore[17]);

        adapter.add(list.get(n[3]));
        list.get(n[3]).setUserPhoneNumber(ReScore[18]);

        adapter.add(list.get(n[4]));
        list.get(n[4]).setUserPhoneNumber(ReScore[19]);


        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://52.78.149.238:8080/idid.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "userid=" + strings[0];
                osw.write(sendMsg);
                osw.flush();
                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }


    class CustomTask1 extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;

        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://52.78.149.238:8080/YOLO.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id=" + strings[0];
                osw.write(sendMsg);
                osw.flush();
                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }

    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

}
