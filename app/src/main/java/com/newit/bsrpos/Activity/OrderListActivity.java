package com.newit.bsrpos.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newit.bsrpos.Model.Order;
import com.newit.bsrpos.R;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {

    private DatabaseReference orderDb = FirebaseDatabase.getInstance().getReference().child("Order");
    private ListView order_grid;
    private ArrayList<Order> order_data = new ArrayList<>();
    private ArrayAdapter<Order> grid_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);

        grid_adapter = new ArrayAdapter<>(this, R.layout.activity_orderlist_grid_row, order_data);
        order_grid = (ListView) findViewById(R.id.orderlist_grid);
        order_grid.setAdapter(grid_adapter);
        orderDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Order o = dataSnapshot.getValue(Order.class);
                o.setKey(dataSnapshot.getKey());
                order_data.add(o);
                grid_adapter.notifyDataSetChanged();
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
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), OrderActivity.class);
                startActivity(intent);
            }
        });

        order_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), OrderActivity.class);
                intent.putExtra("SelectedOrder", order_data.get(position));
                startActivity(intent);
            }
        });
    }

}

