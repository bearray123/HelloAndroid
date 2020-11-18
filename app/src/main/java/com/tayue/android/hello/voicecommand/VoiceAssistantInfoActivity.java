/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hello.voicecommand;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import com.tayue.android.bluetooth.VoiceAssistantHelper;
import com.tayue.android.hello.R;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by xionglei01@baidu.com on 2020-05-27.
 */
public class VoiceAssistantInfoActivity extends AppCompatActivity {


    private TextView isalreadycleanallvoicedefaultassistantView;
    private TextView isalreadysetxiaoduasdefaultvocieassistantView;
    private TextView currentVoiceAssistant;
    private View mJumpVoiceAssistantAppSetting;
    private TextView installedVoiceAssistantView;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_assistant_layout);
        initView();
    }

    @Override protected void onResume() {
        super.onResume();
        refreshData();
    }

    private void initView() {
        isalreadycleanallvoicedefaultassistantView = findViewById(R.id.isalreadycleanallvoicedefaultassistant);
        isalreadysetxiaoduasdefaultvocieassistantView = findViewById(R.id.isalreadysetxiaoduasdefaultvocieassistant);
        currentVoiceAssistant = findViewById(R.id.current_voice_assistant);
        mJumpVoiceAssistantAppSetting = findViewById(R.id.jump_voice_ass_app_settings);
        mJumpVoiceAssistantAppSetting.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                cleanDefaultVoiceAssistantSettings(VoiceAssistantInfoActivity.this);
            }
        });
        installedVoiceAssistantView = findViewById(R.id.installedvoiceassistant);
    }

    private void refreshData() {
        Pair queryResult = VoiceAssistantHelper.queryDefaultVocieAssistant(this);
        isalreadycleanallvoicedefaultassistantView.setText(
            "isAlreadyCleanAllVoiceDefaultAssistant: "  + VoiceAssistantHelper.isAlreadyCleanAllVoiceDefaultAssistant(this));
        isalreadysetxiaoduasdefaultvocieassistantView.setText(
            "isAlreadySetXiaoduAsDefaultVocieAssistant: " + queryResult.first);
        currentVoiceAssistant.setText("当前系统默认的语音助手：" + queryResult.second);
        StringBuffer installedVoiceAssisPrintStr = new StringBuffer();
        HashMap<String, String> installedVa = VoiceAssistantHelper.getInstalledVoiceAssistant(this);
        Iterator iter = installedVa.entrySet().iterator();
        while (iter.hasNext()) {
            java.util.Map.Entry entry = (Map.Entry) iter.next();
            String packageName = (String) entry.getKey();
            //String activity = (String) entry.getValue();
            //resolverActivityPkg.contains(packageName);
            installedVoiceAssisPrintStr.append(packageName).append("\n");
        }
        installedVoiceAssistantView.setText("当前已安装语音助手APP ：\n" + installedVoiceAssisPrintStr);
    }

    /**
     * 跳转至 默认打开语音助手APP的系统设置页面，供用户清除默认设置
     * @param context
     */
    public void cleanDefaultVoiceAssistantSettings(Context context) {
        try {
            Pair<String, String> pair = getDefaultVoiceAssistant(context);
            if (pair != null) {
                Uri uri = Uri.parse("package:" + pair.first);
                Intent it = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, uri);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(it);
            }
        } catch (Exception e) {
            Log.d("","cleanDefaultVoiceAssistantSettings:: Exception=" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获取系统默认的语音助手APP
     * @param context
     * @return
     */
    private Pair<String, String> getDefaultVoiceAssistant(Context context) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VOICE_COMMAND);
        ResolveInfo info = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (info != null) {
            ActivityInfo activityInfo = info.activityInfo;
            if (activityInfo != null) {
                Pair pair = new Pair(info.activityInfo.packageName, info.activityInfo.name);
                Log.i("","getDefaultActivity info = " + info + ";pkgName = " + info.activityInfo.packageName);
                Log.i("","getDefaultActivity info = " + info + ";name = " + info.activityInfo.name);
                return pair;
            }
        }
        return null;
    }


}
