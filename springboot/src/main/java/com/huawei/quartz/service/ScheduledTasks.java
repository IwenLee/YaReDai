package com.huawei.quartz.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 *quartz 定时调度任务， 
 * @author iwen
 */
@Component  
//@Configurable
@EnableScheduling  
public class ScheduledTasks {  
  
    public void work(){  
    	System.out.println("quartz Scheduling Tasks Examples.................");
    }  
}  