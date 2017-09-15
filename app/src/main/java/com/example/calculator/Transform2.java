package com.example.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Transform2 extends AppCompatActivity {
    EditText e2,e10,e16;
    String s2,s10,s16;
    Button b2,b10,b16,clear22;
    int i2,i10,i16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform2);

        b2 = (Button) findViewById(R.id.btwo);
        b10 = (Button) findViewById(R.id.bten);
        b16 = (Button) findViewById(R.id.bsixt);
        clear22 = (Button) findViewById(R.id.clear22);

        e2 = (EditText) findViewById(R.id.two);
        e10 = (EditText) findViewById(R.id.ten);
        e16 = (EditText) findViewById(R.id.sixt);

        clear22.setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {
                e2.setText("");
                e10.setText("");
                e16.setText("");
            }
        }));
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s2 = e2.getText().toString();

                s10 = Integer.valueOf(s2, 2).toString();
                s16 = Integer.toHexString(Integer.parseInt(s2, 2));

                e10.setText(s10);
                e16.setText(s16);
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s10 = e10.getText().toString();
                i10 = Integer.parseInt(s10);

                s2 = Integer.toBinaryString(i10);
                ;
                s16 = Integer.toHexString(i10);

                e2.setText(s2);
                e16.setText(s16);
            }
        });
        b16.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s16 = e16.getText().toString();

                s2 = Integer.toBinaryString(Integer.valueOf(s16, 16));
                s10 = Integer.valueOf(s16, 16).toString();

                e2.setText(s2);
                e10.setText(s10);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.calculator:
                Intent intent=new Intent(Transform2.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.transform:
                Intent intent2=new Intent(Transform2.this,Transform1.class);
                startActivity(intent2);
                break;
            case R.id.transform2:
                Toast.makeText(this, "这个就是进制转换器！", Toast.LENGTH_SHORT).show();
                break;

            case R.id.exit:
                System.exit(0);
        }


        return super.onOptionsItemSelected(item);
    }
}
