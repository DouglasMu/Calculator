package com.example.calculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    Button add, cut, rid, divide;//加减乘除
    Button result;
    Button point;
    Button clear;
    Button sin, cos, pingfang, genhao;
    int pointCount = 0;
    int option = 0;//运算符状态
    int fff = 0;//连加连减判断符号
    boolean newdigital = true;//标记是否是新输入的数字
    boolean flag = true;//判断程序是否出错
    double a = 0, b = 0;//两个相加的数
    double sum = 0;
    double sumtype = 0;//判断输出的数是否有小数部分
    View.OnClickListener lisenter = new View.OnClickListener() {


        @Override
        public void onClick(View v) {
            TextView text = (TextView) findViewById(R.id.textView);
            String s = text.getText().toString();//获取文本框显示的字符串
            Button btn = (Button) v;
            String t = (String) btn.getText();//获取按钮的字符
            //数字的输入
            if (btn.getId() == R.id.btn0 || btn.getId() == R.id.btn1 || btn.getId() == R.id.btn2 || btn.getId() == R.id.btn3
                    || btn.getId() == R.id.btn4 || btn.getId() == R.id.btn5 || btn.getId() == R.id.btn6 ||
                    btn.getId() == R.id.btn7 || btn.getId() == R.id.btn8 || btn.getId() == R.id.btn9 ||
                    (btn.getId() == R.id.btnPoint && pointCount == 0)) {
                if (btn.getId() == R.id.btnPoint) {
                    if (null == s || s.equals("")) {
                        s += "0" + btn.getText();
                    } else {
                        s += btn.getText();
                    }
                    pointCount = 1;
                } else {
                    s += btn.getText();
                }
                text.setText(s);

            }
            //运算符的输入
            if (btn.getId() == R.id.sin || btn.getId() == R.id.cos || btn.getId() == R.id.pingfang || btn.getId() == R.id.genhao) {
                if (s == null || s.equals("")) {
                    s = "0";
                }
                b = Double.valueOf(s);
                switch (btn.getId()) {
                    case R.id.sin:
                        sum = Math.sin(b);
                        break;
                    case R.id.cos:
                        sum = Math.sin(b);
                        break;
                    case R.id.pingfang:
                        sum = b * b;
                        break;
                    case R.id.genhao:
                        if (b < 0) {
                            Toast.makeText(MainActivity.this, "请输入大于等于0的数", Toast.LENGTH_LONG).show();
                            text.setText("");
                            break;
                        }
                        sum = Math.sqrt(b);
                    default:
                        break;
                }
                s = "" + sum;
                text.setText(s);
            }
            if (btn.getId() == R.id.add || btn.getId() == R.id.divide || btn.getId() == R.id.cut || btn.getId() == R.id.rid) {
                //Toast.makeText(MainActivity.this, btn.getText(), Toast.LENGTH_LONG).show();
                //如果已经有两个数，再按运算符就直接把结果运算出来保存到a中然后继续运算
                if (s == null || s.equals("")) {
                    s = "0";
                }
                if (option != 0) {
                    b = Double.valueOf(s);
                    switch (option) {
                        case 1:
                            sum = a + b;
                            break;
                        case 2:
                            sum = a - b;
                            break;
                        case 3:
                            sum = a * b;
                            break;
                        case 4:
                            if (b == 0) {
                                Toast.makeText(MainActivity.this, "0不能为除数", Toast.LENGTH_LONG).show();
                                text.setText("");
                                break;
                            }
                            sum = a / b;
                            break;
                        default:
                            break;
                    }
                    a = sum;

                }
                if (option == 0) {
                    a = Double.valueOf(s);
                }
                switch (btn.getId()) {
                    case R.id.add:
                        option = 1;
                        break;
                    case R.id.cut:
                        option = 2;
                        break;
                    case R.id.rid:
                        option = 3;
                        break;
                    case R.id.divide:
                        option = 4;
                        break;
                    default:
                        break;
                }

                text.setText("");


            }

            //等于，运算结果
            if (btn.getId() == R.id.btnResult) {
                if (s == null || s.equals("")) {
                    s = "0";
                }

                //Toast.makeText(MainActivity.this, btn.getText(), Toast.LENGTH_LONG).show();
                if(option == 0){
                    if(fff == 1)
                        sum =a +b ;

                    if(fff == 2)
                        sum =a -b ;
                    if(fff == 3)
                        sum =a * b ;
                    if(fff == 4)
                        sum =a /b ;

                }
                else {
                    b = Double.valueOf(s);
                    //连加
                    sum = a;
                    switch (option) {
                        case 1:
                            sum = sum + b;
                            fff = 1;
                            break;
                        case 2:
                            sum = sum - b;
                            fff = 2;
                            break;
                        case 3:
                            sum = sum * b;
                            fff =3;
                            break;
                        case 4:
                            if (b == 0) {
                                Toast.makeText(MainActivity.this, "0不能为除数", Toast.LENGTH_LONG).show();
                                text.setText("");
                                flag = false;
                                break;
                            }
                            sum = sum / b;
                            fff = 4;
                            break;
                        default:
                            break;
                    }
                }
                sumtype = sum % 1;
                if (sumtype > 0) {
                    pointCount = 1;
                }
                s = "" + sum;
                if (sumtype == 0) {
                    int end = (s.toString()).lastIndexOf(".");
                    String str = (s.toString()).substring(0, end);
                    s = "" + Integer.parseInt(str);
                    pointCount = 0;
                }
                if (flag) {
                    text.setText(s);
                }
                a = Double.valueOf(s);
                option = 0;
                flag = true;

            }
            //清除
            if (btn.getId() == R.id.clear) {
                //Toast.makeText(MainActivity.this, btn.getText(), Toast.LENGTH_LONG).show();
                text.setText("");
                pointCount = 0;
                option = 0;
                flag = true;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT)
        {
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            textView = (TextView) findViewById(R.id.textView);
            button1 = (Button) findViewById(R.id.btn1);
            button2 = (Button) findViewById(R.id.btn2);
            button3 = (Button) findViewById(R.id.btn3);
            button4 = (Button) findViewById(R.id.btn4);
            button5 = (Button) findViewById(R.id.btn5);
            button6 = (Button) findViewById(R.id.btn6);
            button7 = (Button) findViewById(R.id.btn7);
            button8 = (Button) findViewById(R.id.btn8);
            button9 = (Button) findViewById(R.id.btn9);
            button0 = (Button) findViewById(R.id.btn0);
            add = (Button) findViewById(R.id.add);
            cut = (Button) findViewById(R.id.cut);
            rid = (Button) findViewById(R.id.rid);
            divide = (Button) findViewById(R.id.divide);
            result = (Button) findViewById(R.id.btnResult);
            point = (Button) findViewById(R.id.btnPoint);
            clear = (Button) findViewById(R.id.clear);
            sin = (Button) findViewById(R.id.sin);
            cos = (Button) findViewById(R.id.cos);
            pingfang = (Button) findViewById(R.id.pingfang);
            genhao = (Button) findViewById(R.id.genhao);

            button0.setOnClickListener(lisenter);
            button1.setOnClickListener(lisenter);
            button2.setOnClickListener(lisenter);
            button3.setOnClickListener(lisenter);
            button4.setOnClickListener(lisenter);
            button5.setOnClickListener(lisenter);
            button6.setOnClickListener(lisenter);
            button7.setOnClickListener(lisenter);
            button8.setOnClickListener(lisenter);
            button9.setOnClickListener(lisenter);
            add.setOnClickListener(lisenter);
            cut.setOnClickListener(lisenter);
            rid.setOnClickListener(lisenter);
            divide.setOnClickListener(lisenter);
            result.setOnClickListener(lisenter);
            point.setOnClickListener(lisenter);
            clear.setOnClickListener(lisenter);
            sin.setOnClickListener(lisenter);
            cos.setOnClickListener(lisenter);
            pingfang.setOnClickListener(lisenter);
            genhao.setOnClickListener(lisenter);
        }
        else if(this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            Intent intent=new Intent(MainActivity.this,Land_MainActivity.class);
            startActivity(intent);

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.calculator:
                Toast.makeText(this, "这个就是计算器！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.transform:
                Intent intent=new Intent(MainActivity.this,transformActivity.class);
                startActivity(intent);
                break;
            case R.id.transform2:
                Intent intent2=new Intent(MainActivity.this,Transform2.class);
                startActivity(intent2);
                break;
            case R.id.exit:
                System.exit(0);
        }


        return super.onOptionsItemSelected(item);
    }
}
