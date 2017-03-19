package com.newit.bsrpos.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newit.bsrpos.Model.Customer;
import com.newit.bsrpos.Model.Order;
import com.newit.bsrpos.Model.OrderLine;
import com.newit.bsrpos.R;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private DatabaseReference orderDb = FirebaseDatabase.getInstance().getReference().child("Order");
    private TextView order_no;
    private TextView order_date;
    private Spinner order_cus;
    private Button order_save;
    private Order order;
    private String key;

    private ListView orderdetail_listView;
    private ArrayList<String> orderdetail_data = new ArrayList<>();
    private ArrayAdapter<String> orderdetail_adapter;

    private DatabaseReference cusDb = FirebaseDatabase.getInstance().getReference().child("Customer");
    private ArrayList<String> cus_data = new ArrayList<>();
    private ArrayAdapter<String> cus_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        order_no = (TextView) findViewById(R.id.order_no);
        order_date = (TextView) findViewById(R.id.order_date);
        order_cus = (Spinner) findViewById(R.id.order_cus);
        order_save = (Button) findViewById(R.id.order_save);

        order = getIntent().getParcelableExtra("SelectedOrder");
        if (order == null) {
            order = new Order();
        } else {
            key = order.getKey();
            order_no.setText(order.getNo());
            order_date.setText(order.getDate());
            int position = cus_data.indexOf(order.getCus());
            order_cus.setSelection(position);
            SaveCusSelection(position);
        }

        order_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setNo(order_no.getText().toString());
                order.setDate(order_date.getText().toString());
                order.setCus(order_cus.getSelectedItem().toString());
                if (key == null) {
                    setKey(orderDb.push().getKey());
                }
                orderDb.child(key).setValue(order);
            }
        });

        cus_adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, cus_data);
        cus_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        order_cus.setAdapter(cus_adapter);
        cusDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Customer c = dataSnapshot.getValue(Customer.class);
                cus_data.add(c.getCode());
                order_cus.setSelection(cus_data.indexOf(order.getCus()));
                cus_adapter.notifyDataSetChanged();
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

        order_cus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SaveCusSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    public void setKey(String key) {
        this.key = key;

        orderdetail_adapter = new ArrayAdapter<>(this, R.layout.activity_order_detail_row, orderdetail_data);
        orderdetail_listView = (ListView) findViewById(R.id.order_grid);
        orderdetail_listView.setAdapter(orderdetail_adapter);
        orderDb.child(key).child("Line").orderByChild("no").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                OrderLine o = dataSnapshot.getValue(OrderLine.class);
                orderdetail_data.add(o.getProd());
                orderdetail_adapter.notifyDataSetChanged();
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
    }

    public void SaveCusSelection(int position) {
        SharedPreferences sharedPref = getSharedPreferences("FileName", 0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("order_cus", position);
        prefEditor.commit();
    }
}
