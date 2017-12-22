package com.yourtaste.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.yourtaste.R;


public class ListView_Adapter extends BaseAdapter implements View.OnClickListener {


    private ListView_User mUser;
    private Context mContext;


    private ImageView imgUserIcon;
    private TextView tvUserName;
    private TextView tvUserPhoneNumber;
    private ImageButton btnSend;


    private ArrayList<ListView_User> mUserData;

    public ListView_Adapter(Context context) {
        super();
        mContext = context;
        mUserData = new ArrayList<ListView_User>();
    }

    @Override

    public int getCount() {
        // TODO Auto-generated method stub
        return mUserData.size();
    }

    @Override

    public ListView_User getItem(int position) {
        // TODO Auto-generated method stub
        return mUserData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = convertView;


        if (v == null) {

            v = ((LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.layout_item_recommend, null);


            imgUserIcon = (ImageView) v.findViewById(R.id.user_icon);
            tvUserName = (TextView) v.findViewById(R.id.user_name);
            tvUserPhoneNumber = (TextView) v.findViewById(R.id.user_phone_number);
            btnSend = (ImageButton) v.findViewById(R.id.btn_send);
        }


        mUser = getItem(position);


        btnSend.setTag(mUser);


        if (mUser != null) {

            if (mUser.getUserIcon() != null) {
                imgUserIcon.setImageDrawable(mUser.getUserIcon());
            }
            tvUserName.setText(mUser.getUserName());
            tvUserPhoneNumber.setText(mUser.getUserPhoneNumber());
            btnSend.setOnClickListener(this);
        }

        return v;
    }


    public void add(ListView_User user) {
        mUserData.add(user);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        ListView_User clickItem = (ListView_User) v.getTag();

        switch (v.getId()) {
            case R.id.btn_send:
                Toast.makeText(mContext, clickItem.getUserPhoneNumber(),
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}