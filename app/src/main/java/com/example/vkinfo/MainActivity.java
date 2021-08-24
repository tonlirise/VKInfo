package com.example.vkinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText ed_search;
    private Button btn_search;
    private TextView tv_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_search = findViewById(R.id.et_serch_text);
        btn_search = findViewById(R.id.id_serarch_button);
        tv_result = findViewById(R.id.tv_result);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_result.setText(ed_search.getText());
            }
        };
        btn_search.setOnClickListener(onClickListener);
    }
}