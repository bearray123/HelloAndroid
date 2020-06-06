/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.bluetooth;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xionglei01@baidu.com on 2020-05-26.
 */
public class VoiceAssistantHelper {

    private static final Set<String> resolverActivityPkg;

    static {
        resolverActivityPkg = new HashSet<>();
        resolverActivityPkg.add("android");
        resolverActivityPkg.add("com.huawei.android.internal.app");
    }

    public static final String XIAODU_APP_PACKAGE = "com.baidu.duer.superapp";


    public static boolean isAlreadyCleanAllVoiceDefaultAssistant(Context context) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VOICE_COMMAND);
        ResolveInfo info = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (info != null) {
            ActivityInfo activityInfo = info.activityInfo;
            if (activityInfo != null) {
                return resolverActivityPkg.contains(info.activityInfo.packageName);
            }
        }
        return false;
    }

    public static boolean isAlreadySetXiaoduAsDefaultVocieAssistant(Context context) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VOICE_COMMAND);
        ResolveInfo info = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (info != null) {
            ActivityInfo activityInfo = info.activityInfo;
            if (activityInfo != null) {
                return XIAODU_APP_PACKAGE.equals(info.activityInfo.packageName);
            }
        }
        return false;
    }


    public static HashMap<String, String> getInstalledVoiceAssistant(Context context) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VOICE_COMMAND);
        List<ResolveInfo> listInfo = pm.queryIntentActivities(intent, PackageManager.MATCH_ALL);
        int count = listInfo.size();
        HashMap<String, String> map = new HashMap<>(count);
        for (ResolveInfo info : listInfo) {
            if (info != null) {
                ActivityInfo activityInfo = info.activityInfo;
                if (activityInfo != null) {
                    map.put(info.activityInfo.packageName, info.activityInfo.name);
                }
            }
        }
        return map;
    }


}
