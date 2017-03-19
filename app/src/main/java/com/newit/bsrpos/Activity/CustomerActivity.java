package com.newit.bsrpos.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newit.bsrpos.Model.Customer;
import com.newit.bsrpos.R;

public class CustomerActivity extends AppCompatActivity {
    private DatabaseReference cusDb = FirebaseDatabase.getInstance().getReference().child("Customer");
    private TextView cus_code;
    private TextView cus_name;
    private TextView cus_addr;
    private TextView cus_point;
    private Button cus_save;
    private Customer customer;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        cus_code = (TextView) findViewById(R.id.cus_code);
        cus_name = (TextView) findViewById(R.id.cus_name);
        cus_addr = (TextView) findViewById(R.id.cus_addr);
        cus_point = (TextView) findViewById(R.id.cus_point);
        cus_save = (Button) findViewById(R.id.cus_save);
        customer = new Customer();

        cus_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer.setCode(cus_code.getText().toString());
                customer.setName(cus_name.getText().toString());
                customer.setAddr(cus_addr.getText().toString());
                customer.setPoint(Integer.valueOf(cus_point.getText().toString()));
                if (key == null) {
                    key = cusDb.push().getKey();
                }
                cusDb.child(key).setValue(customer);
            }
        });
    }
}