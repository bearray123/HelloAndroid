/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hello;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.tayue.android.hello.broadcast.BTBroadcastReceiver;
import com.tayue.android.hello.memory.MockMemoryLeakActivity;

public class MainActivity extends AppCompatActivity {

    private View mTestConstraintBtn;
    private View mTestCoordinatorBtn;
    private View mTestMemoryShakeBtn;

    private BTBroadcastReceiver mBTReceiver;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      mTestConstraintBtn = findViewById(R.id.test_constraint);
      mTestCoordinatorBtn = findViewById(R.id.test_coordinator);
      mTestMemoryShakeBtn = findViewById(R.id.test_memory_shake);

      initView();

      mBTReceiver = new BTBroadcastReceiver();
      //mReceive
      IntentFilter filter = new IntentFilter();
      filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
      //registerReceiver(mBTReceiver, filter);

    }

    private void initView() {
      mTestConstraintBtn.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, TestConstraintLayoutActivity.class));
        }
      });
      mTestCoordinatorBtn.setOnClickListener(new View.OnClickListener(){
        @Override public void onClick(View v) {
          Toast.makeText(MainActivity.this, "TODO： 还未做！", Toast.LENGTH_SHORT).show();
          //startActivity(new Intent(MainActivity.this, TestCoordinatorLayoutActivity.class));

        }
      });

        mTestMemoryShakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MockMemoryLeakActivity.class));

            }
        });
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(mBTReceiver);
    }
}
