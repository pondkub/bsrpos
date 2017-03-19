package com.newit.bsrpos.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.newit.bsrpos.Adapter.MainAdapter;
import com.newit.bsrpos.R;

public class MainActivity extends AppCompatActivity {
    GridView grid;
    String[] menuItems = {"Order", "Customer", "Product", "Utility"};
    int[] menuIcons = {R.drawable.order, R.drawable.customer, R.drawable.product, R.drawable.utility};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        MainAdapter adapter = new MainAdapter(MainActivity.this, menuItems, menuIcons);
        grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getBaseContext(), OrderListActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getBaseContext(), CustomerActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

    }

}
