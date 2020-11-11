/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hello;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.browser.CommonWebActivity;
import com.tayue.android.bluetooth.BTBroadcastReceiver;
import com.tayue.android.hello.memory.MockMemoryLeakActivity;
import com.tayue.android.hello.voicecommand.VoiceAssistantInfoActivity;
import com.tayue.android.hello.voicecommand.VoiceCmdLauncherActivity;
import com.tayue.android.hellokotlin.PowerService;
import com.tayue.android.mvvm.view.TestMVVMMainActivity;

public class MainActivity extends AppCompatActivity {

    private View mTestConstraintBtn;
    private View mTestCoordinatorBtn;
    private View mTestMemoryShakeBtn;
    private View mTestMVVMBtn;
    private View mTestVoiceCommand;
    private View mTestForgroundServiceBtn;
    private View mTestNetwork;
    private View mTestPhoneProperty;
    private View mTestWebView;

    private TextView mWifiSleepPolicy;



    private BTBroadcastReceiver mBTReceiver;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      mTestConstraintBtn = findViewById(R.id.test_constraint);
      mTestCoordinatorBtn = findViewById(R.id.test_coordinator);
      mTestMemoryShakeBtn = findViewById(R.id.test_memory_shake);
      mTestMVVMBtn = findViewById(R.id.test_mvvm);
      mTestVoiceCommand = findViewById(R.id.test_voice_command);
      mTestForgroundServiceBtn = findViewById(R.id.test_forgroudservice);
      mTestNetwork = findViewById(R.id.test_network);
      mWifiSleepPolicy = (TextView) findViewById(R.id.wifi_sleep_state);
      mTestPhoneProperty = findViewById(R.id.test_phone_property);
      mTestWebView = findViewById(R.id.test_webview);

      initView();

      mBTReceiver = new BTBroadcastReceiver();
      //mReceive
      //IntentFilter filter = new IntentFilter();
      //filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);

      // 蓝牙广播
      registerReceiver(mBTReceiver, makeFilters());

      startService(new Intent(MainActivity.this, PowerService.class));

      }

    /**
     * 蓝牙监听需要添加的Action
     */
    private IntentFilter makeFilters() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothA2dp.ACTION_PLAYING_STATE_CHANGED);
        intentFilter.addAction(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        intentFilter.addAction(BluetoothHeadset.ACTION_AUDIO_STATE_CHANGED);
        return intentFilter;
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

        mTestMVVMBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestMVVMMainActivity.class));
            }
        });

        mTestVoiceCommand.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VoiceAssistantInfoActivity.class));
            }
        });

        mTestForgroundServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VoiceCmdLauncherActivity.class));
            }
        });

        mTestNetwork.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, TestNetworkActivity.class));
                boolean supportSet = true;
                try {
                    ComponentName componentName = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.power.ui.PowerSettingActivity");
                    Intent intent = new Intent();
                    intent.setComponent(componentName);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception e) {
                    supportSet = false;
                    e.printStackTrace();
                }
                if (!supportSet) {
                    Toast.makeText(MainActivity.this, "当前手机系统版本不支持网络休眠设置", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mTestPhoneProperty.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PhonePropertyActivity.class));
            }
        });

        mTestWebView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CommonWebActivity.class));
            }
        });
    }

    @Override protected void onResume() {
        super.onResume();
        int wifiSleepState = getmWifiSleepPolicy();
        mWifiSleepPolicy.setText(wifiSleepState + "");
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBTReceiver);
    }

    private int getmWifiSleepPolicy() {
        ContentResolver resolver = getContentResolver();
        int value = android.provider.Settings.Global.getInt(resolver,
            android.provider.Settings.Global.WIFI_SLEEP_POLICY,
            android.provider.Settings.Global.WIFI_SLEEP_POLICY_DEFAULT);

        //System.out.println("test -- wifi value old: "+ value + " default:" +
        //    android.provider.Settings.Global.WIFI_SLEEP_POLICY_DEFAULT +
        //    " never:" + android.provider.Settings.Global.WIFI_SLEEP_POLICY_NEVER);
        return value;
    }


}
