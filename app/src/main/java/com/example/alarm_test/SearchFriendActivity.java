package com.example.alarm_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class SearchFriendActivity extends AppCompatActivity {
    Button searchBtn;
    EditText searchName;
    TextView friend_Tv;
    private String name;
    private DatabaseReference mdbr;
    private Map<String , Object> childUpdates = null;
    private FirebaseUser user;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);
        searchBtn = findViewById(R.id.searchBtn);
        searchName = findViewById(R.id.search_Tv);
        friend_Tv = findViewById(R.id.friend_Tv);
        friend_Tv.setVisibility(View.INVISIBLE);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name =  searchName.getText().toString();
                user = FirebaseAuth.getInstance().getCurrentUser();

                FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            for(DataSnapshot ds : snapshot.getChildren()){
                                String id = (String) ds.child("ID").getValue();
                                if(id.equals(name)){
                                    friend_Tv.setText(id);
                                    friend_Tv.setVisibility(View.VISIBLE);
                                    friend_Tv.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            System.out.println("z");
                                        }
                                    });
                                }
                            }
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
   /*             DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("UserAccount");
                userID = user != null ? user.getUid() : null;
                mDatabase.child(userID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        System.out.println("완료 !!!!!!!!");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("MainActivity", "onCancelled");
                    }
                });
                */
            }
        });
    }
}