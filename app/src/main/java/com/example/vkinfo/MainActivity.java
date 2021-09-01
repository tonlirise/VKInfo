package com.example.vkinfo;

import java.net.URL;

import static com.example.vkinfo.utils.NetworkUtils.generateURL;
import static com.example.vkinfo.utils.NetworkUtils.getResponseFromURL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText ed_search;
    private Button btn_search;
    private TextView tv_result;
    private TextView tv_error;
    private ProgressBar pgBar;

    private void showResultTextView(){
        tv_result.setVisibility(View.VISIBLE);
        tv_error.setVisibility(View.INVISIBLE);
    }

    private void showErrorTextView(){
        tv_result.setVisibility(View.INVISIBLE);
        tv_error.setVisibility(View.VISIBLE);
    }

    class VKQueryTask extends AsyncTask<URL,Void,String>{

        @Override
        protected void onPreExecute(){
            pgBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls){
            String responce = getResponseFromURL(urls[0]);
            return responce;
        }

        @Override
        protected void onPostExecute(String responce){
            String firstName = null;
            String lastName = null;

            pgBar.setVisibility(View.INVISIBLE);
            if(responce==null || responce.equals("")){
                showErrorTextView();
                return;
            }

            try {
                JSONObject jsonResponse = new JSONObject(responce);
                JSONArray jsonArray = jsonResponse.getJSONArray("response");
                JSONObject userInfo = jsonArray.getJSONObject(0);

                firstName = userInfo.getString("first_name");
                lastName = userInfo.getString("last_name");
            }
            catch (JSONException exp){
                exp.printStackTrace();
            }

            String resiltStr = "Имя: "+firstName+"\n Фамилия: "+lastName;
            tv_result.setText(resiltStr);
            showResultTextView();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_search = findViewById(R.id.et_serch_text);
        btn_search = findViewById(R.id.id_serarch_button);
        tv_result = findViewById(R.id.tv_result);
        tv_error = findViewById(R.id.id_tw_error_message);
        pgBar = findViewById(R.id.id_progress);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                URL generatedURL = generateURL(ed_search.getText().toString());

                new VKQueryTask().execute(generatedURL);
            }
        };
        btn_search.setOnClickListener(onClickListener);
    }
}