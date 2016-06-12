package com.example.echo.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "Task id is" + getTaskId());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.button_1);
        if (button1 != null) {
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
    //                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

    //                Intent intent = new Intent("com.example.echo.ACTION_START");
    //                intent.addCategory("com.example.echo.MY_CATEGORY");

    //                Intent intent = new Intent(Intent.ACTION_VIEW);
    //                intent.setData(Uri.parse("http://www.baidu.com"));

    //                Intent intent = new Intent(Intent.ACTION_DIAL);
    //                intent.setData(Uri.parse("tel:10086"));

    //                String data = "Hello Second";
    //                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
    //                intent.putExtra("extra_data", data);

//                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                    startActivityForResult(intent, 1);

//                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                    startActivity(intent);

                    SecondActivity.actionStart(MainActivity.this, "data1", "data2");
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("First", returnedData);
                }
                break;
            default:
        }
    }
}
