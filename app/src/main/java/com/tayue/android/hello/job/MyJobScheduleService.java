/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hello.job;

import android.app.job.JobParameters;
import android.app.job.JobService;

/**
 * Created by xionglei01@baidu.com on 2020-02-07.
 */
public class MyJobScheduleService extends JobService {
    @Override public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override public boolean onStopJob(JobParameters params) {
        return false;
    }
}
