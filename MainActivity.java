package com.example.riley.willyoupleasehiremeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.riley.willyoupleasehiremeapp.R.*;


public class MainActivity extends AppCompatActivity {

    public ArrayList<Job> jobList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        jobList = new ArrayList<>();

        // Get ListView object from xml
        final ListView listView = (ListView) findViewById(id.listView);

        // Create a new Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // Connect to the Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to the todoItems child items it the database
        final DatabaseReference myRef = database.getReference("Jobs");

        Query query = myRef.orderByChild("CompanyName");
        System.out.println(query);

        // Assign a listener to detect changes to the child items
        // of the database reference.
        myRef.addChildEventListener(new ChildEventListener(){

            // This function is called once for each child that exists
            // when the listener is added. Then it is called
            // each time a new child is added.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
               Iterable<DataSnapshot> value = dataSnapshot.getChildren();
                Job newJob = new Job();
                int i= 0;

                for (DataSnapshot data : value)
                {
                    System.out.println(data.getValue());

                    switch (i)
                    {
                        case 0:
                            newJob.setCompanyName(data.getValue().toString());
                        case 1:
                            newJob.setContactEmail(data.getValue().toString());
                        case 2:
                            newJob.setContactLink(data.getValue().toString());
                        case 3:
                            newJob.setContactName(data.getValue().toString());
                        case 4:
                            newJob.setLatitude(data.getValue().toString());
                        case 5:
                            newJob.setLongitude(data.getValue().toString());
                        case 6:
                            newJob.setDescription(data.getValue().toString());
                        case 7:
                            newJob.setTitle(data.getValue().toString());
                    }
                    i++;
                }

                //TODO: Do the math so that only jobs in an X mile radius would appear on a users timeline
                //TODO: Filter smart keywords

                adapter.add(newJob.getPositionTitle() + " - " + newJob.getCompanyName());
                jobList.add(newJob);

            }

            // This function is called each time a child item is removed.
            public void onChildRemoved(DataSnapshot dataSnapshot){
               // String value = dataSnapshot.getValue(String.class);
               // adapter.remove(value);
            }

            // The following functions are also required in ChildEventListener implementations.
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName){}
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName){}

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG:", "Failed to read value.", error.toException());
            }
        });

        // Delete items when clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Query myQuery = myRef.orderByValue().equalTo((String)
                        listView.getItemAtPosition(position));

                PopupMenu popup = new PopupMenu(MainActivity.this, listView);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

                myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                            firstChild.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                })
                ;}
        })
        ;}

}
