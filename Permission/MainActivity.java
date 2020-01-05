package com.example.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 뷰의 주소 값을 담을 참조 변수
    TextView text1;
    // 체크할 권한 목록
    String [] permission_list = {
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰의 주소 값을 받는다.
        text1 = (TextView)findViewById(R.id.textView);

        checkPermission();
    }


    // 권한 체크 메서드
    public void checkPermission(){
        // 현재 안드로이드 버전이 6.0 미만이면 메서드를 종료한다.
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return;
        }
        // 각 권한의 허용 여부를 확인한다.
        for(String permission : permission_list){
            // 권한 허용 여부를 확인한다.
            int chk = checkCallingOrSelfPermission(permission);
            // 거부 상태라고 한다면..
            if(chk == PackageManager.PERMISSION_DENIED){
                requestPermissions(permission_list,0);
            }
        }
    }
    // 권한 확인 여부가 완료되면 호출되는 메서드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        text1.setText("");

        // 사용자가 권한 허용 여부를 확인한다.
        for(int i = 0; i < grantResults.length; i++){
            if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                text1.append(permissions[i] + " : 허용\n");
            } else{
                text1.append(permissions[i] + " : 거부\n");
            }
        }

    }
}
