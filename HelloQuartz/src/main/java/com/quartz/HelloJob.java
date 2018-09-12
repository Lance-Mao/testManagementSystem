package com.quartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: HelloQuartz
 * @author: MaoLW
 * @create: 2018-09-11 15:44
 **/

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Exec Time is:" + simpleDateFormat.format(date));

        // 编写具体的业务逻辑
        System.out.println("Hello World");

    }
}
