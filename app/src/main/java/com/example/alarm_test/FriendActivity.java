package com.example.alarm_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.alarm_test.list.FriendsAdapter;
import com.example.alarm_test.list.FriendsDataItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class FriendActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FriendsAdapter adapter;
    ImageButton addFriendBtn;

    private ArrayList<FriendsDataItem> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        addFriendBtn = findViewById(R.id.addFriendBtn);
        addFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendActivity.this,SearchFriendActivity.class);
                startActivity(intent);
            }
        });
    }
}