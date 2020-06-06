/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hello.voicecommand;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

    private TextView installedVoiceAssistantView;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_assistant_layout);
        initView();
    }

    private void initView() {
        isalreadycleanallvoicedefaultassistantView = findViewById(R.id.isalreadycleanallvoicedefaultassistant);
        isalreadysetxiaoduasdefaultvocieassistantView = findViewById(R.id.isalreadysetxiaoduasdefaultvocieassistant);

        isalreadycleanallvoicedefaultassistantView.setText(
            "isAlreadyCleanAllVoiceDefaultAssistant: "  + VoiceAssistantHelper.isAlreadyCleanAllVoiceDefaultAssistant(this));
        isalreadysetxiaoduasdefaultvocieassistantView.setText(
            "isAlreadySetXiaoduAsDefaultVocieAssistant: " + VoiceAssistantHelper.isAlreadySetXiaoduAsDefaultVocieAssistant(this));


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
        installedVoiceAssistantView = findViewById(R.id.installedvoiceassistant);
        installedVoiceAssistantView.setText("InstalledVoiceAssistant ：\n" + installedVoiceAssisPrintStr);

    }


}
