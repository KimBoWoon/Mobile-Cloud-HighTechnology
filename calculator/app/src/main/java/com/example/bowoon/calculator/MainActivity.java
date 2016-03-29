package com.example.bowoon.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private TextView val1, val2;
    private TextView result;
    private boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val1 = (TextView) findViewById(R.id.val1);
        val2 = (TextView) findViewById(R.id.val2);
        result = (TextView) findViewById(R.id.result);

        findViewById(R.id.num0).setOnClickListener(listener);
        findViewById(R.id.num1).setOnClickListener(listener);
        findViewById(R.id.num2).setOnClickListener(listener);
        findViewById(R.id.num3).setOnClickListener(listener);
        findViewById(R.id.num4).setOnClickListener(listener);
        findViewById(R.id.num5).setOnClickListener(listener);
        findViewById(R.id.num6).setOnClickListener(listener);
        findViewById(R.id.num7).setOnClickListener(listener);
        findViewById(R.id.num8).setOnClickListener(listener);
        findViewById(R.id.num9).setOnClickListener(listener);
        findViewById(R.id.add).setOnClickListener(listener);
        findViewById(R.id.sub).setOnClickListener(listener);
        findViewById(R.id.mul).setOnClickListener(listener);
        findViewById(R.id.div).setOnClickListener(listener);
        findViewById(R.id.next).setOnClickListener(listener);
        findViewById(R.id.clear).setOnClickListener(listener);
    }

    Button.OnClickListener listener = new View.OnClickListener() {
        int a, b;

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.num0:
                    if (val1.getText().length() != 0 || val2.getText().length() != 0) {
                        if (!next)
                            val1.setText(val1.getText() + "0");
                        else
                            val2.setText(val2.getText() + "0");
                    }
                    break;
                case R.id.num1:
                    if (!next)
                        val1.setText(val1.getText() + "1");
                    else
                        val2.setText(val2.getText() + "1");
                    break;
                case R.id.num2:
                    if (!next)
                        val1.setText(val1.getText() + "2");
                    else
                        val2.setText(val2.getText() + "2");
                    break;
                case R.id.num3:
                    if (!next)
                        val1.setText(val1.getText() + "3");
                    else
                        val2.setText(val2.getText() + "3");
                    break;
                case R.id.num4:
                    if (!next)
                        val1.setText(val1.getText() + "4");
                    else
                        val2.setText(val2.getText() + "4");
                    break;
                case R.id.num5:
                    if (!next)
                        val1.setText(val1.getText() + "5");
                    else
                        val2.setText(val2.getText() + "5");
                    break;
                case R.id.num6:
                    if (!next)
                        val1.setText(val1.getText() + "6");
                    else
                        val2.setText(val2.getText() + "6");
                    break;
                case R.id.num7:
                    if (!next)
                        val1.setText(val1.getText() + "7");
                    else
                        val2.setText(val2.getText() + "7");
                    break;
                case R.id.num8:
                    if (!next)
                        val1.setText(val1.getText() + "8");
                    else
                        val2.setText(val2.getText() + "8");
                    break;
                case R.id.num9:
                    if (!next)
                        val1.setText(val1.getText() + "9");
                    else
                        val2.setText(val2.getText() + "9");
                    break;
                case R.id.next:
                    next = !next;
                    break;
                case R.id.clear:
                    val1.setText("");
                    val2.setText("");
                    next = false;
                    break;
                case R.id.add:
                    a = Integer.parseInt(val1.getText().toString());
                    b = Integer.parseInt(val2.getText().toString());

                    result.setText(String.valueOf(a + b));
                    break;
                case R.id.sub:
                    a = Integer.parseInt(val1.getText().toString());
                    b = Integer.parseInt(val2.getText().toString());

                    result.setText(String.valueOf(a - b));
                    break;
                case R.id.mul:
                    a = Integer.parseInt(val1.getText().toString());
                    b = Integer.parseInt(val2.getText().toString());

                    result.setText(String.valueOf(a * b));
                    break;
                case R.id.div:
                    a = Integer.parseInt(val1.getText().toString());
                    b = Integer.parseInt(val2.getText().toString());

                    if (b == 0) {
                        Toast.makeText(MainActivity.this, "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    result.setText(String.valueOf((double) a / (double) b));
                    break;
                default:
                    Toast.makeText(MainActivity.this, "잘못된 선택", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
