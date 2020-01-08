package com.example.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView text1, text2;

    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView)findViewById(R.id.textView);
        text2 = (TextView)findViewById(R.id.textView2);

        handler = new Handler();

        // 핸들러를 통해 요청할 작업
        ThreadClass thread = new ThreadClass();
        // 핸들러를 통해 작업을 요청한다(메인쓰레드)
        //handler.post(thread);
        handler.postDelayed(thread, 100);
    }

    public void btnMethod(View view){
        long now = System.currentTimeMillis();
        text1.setText("버튼 클릭 : " + now);
    }

    class ThreadClass extends Thread{
        @Override
        public void run() {
            long now = System.currentTimeMillis();
            text2.setText("버튼 클릭 : "+ now);
            // 현재 작업을 다시 요청한다.
            //handler.post(this);
            handler.postDelayed(this, 100);
        }
    }
}
