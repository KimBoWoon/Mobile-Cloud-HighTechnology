package com.calculator.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById(R.id.text).setOnClickListener(listener);
    }

    Button.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText val1 = (EditText) findViewById(R.id.val1);
            EditText val2 = (EditText) findViewById(R.id.val2);

            switch (v.getId()) {
                case R.id.num1:
                    break;
                case R.id.num2:
                    break;
                case R.id.num3:
                    break;
                case R.id.num4:
                    break;
                case R.id.num5:
                    break;
                case R.id.num6:
                    break;
                case R.id.num7:
                    break;
                case R.id.num8:
                    break;
                case R.id.num9:
                    break;
                case R.id.num0:
                    break;
                case R.id.add:
                    break;
                case R.id.min:
                    break;
                case R.id.div:
                    break;
                case R.id.mul:
                    break;
                default:
                    break;
            }
        }
    };
}
