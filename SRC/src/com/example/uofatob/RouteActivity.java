package com.example.uofatob;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.content.Intent;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;

/**
 * Created by James Cook on 30/01/2016.
 */
public class RouteActivity extends Activity {
    private final String dbPath = "uofatob.firebase.io.com";
    private Firebase database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        final Firebase database = new Firebase(dbPath);

        Intent intent = getIntent();
        String startBuilding = intent.getStringExtra("Start Building");
        int startRoom = intent.getIntExtra("Start Room", 0);
        String destBuilding = intent.getStringExtra("Dest Building");
        int destRoom = intent.getIntExtra("Dest Room", 0);
        Query buildingQ = database.child("Waypoints").orderByChild("/Location").equalTo(startBuilding.toLowerCase());
        ArrayList<Number> potentialRooms = new ArrayList<Number>();
        buildingQ.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                potentialBuildings.add(dataSnapshot.child("Room Number").getValue());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        

        }

        final ImageView image = (ImageView) findViewById(R.id.imageView);
        //String picQuery = getStringFromQuery();

       // image.setImageBitmap(imageLoad(picQuery));
        setContentView(R.layout.activity_route);
    }
}