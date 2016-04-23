package com.example.secret.call;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText) findViewById(R.id.phone_number);
        findViewById(R.id.call).setOnClickListener(listener);
    }

    Button.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.call:
                    String phoneNumber = "tel:";

                    if (edit.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, "전화번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    phoneNumber += edit.getText().toString();

                    Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));
                    startActivity(call);
                    break;
            }
        }
    };
}
