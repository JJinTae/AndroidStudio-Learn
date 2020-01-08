package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 뷰의 주소 값을 담을 참조 변수
    TextView text1, text2;
    // 스래드 동작 여부를 컨트롤할 변수
    boolean isRunning = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 뷰의 주소 값을 받는다
        text1 = (TextView)findViewById(R.id.textView);
        text2 = (TextView)findViewById(R.id.textView2);

        //100ms 마다 현재시간 가져와 출력
        /*while(true){
            SystemClock.sleep(100);
            long now = System.currentTimeMillis();
            text2.setText("무한 루프 : "+ now);
        }*/

        // 오래걸리는 작업 처리를 위해 쓰래드 발생
        isRunning = true;
        ThreadClass thread = new ThreadClass();
        thread.start();
    }

    public  void  btnMethod(View view){
        // 현재시간값을 ms로 가져옴
        long now = System.currentTimeMillis();
        //출력
        text1.setText("버튼 클릭 : "+ now);

    }

    //쓰래드 클래스
    class ThreadClass extends Thread{
        @Override
        public void run() {
            while(isRunning){
                SystemClock.sleep(100);
                long now = System.currentTimeMillis();
                Log.d("test", "쓰래드 : " + now);
                //8.0 부터만 가능 , 아래 버전은 개발자가 발생 시킨 스레드(현재 스래드) 에서 처리가 안돼서 오류 발생
                text2.setText("쓰래드 : " + now);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //스래드 종료를위해 false 값을 넣어줌
        isRunning = false;
    }
}
