package com.huawei.quartz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.huawei.quartz.service.ScheduledTasks;


@Configuration
public class SchedledConfiguration {

	@Bean(name = "detailFactoryBean")  //配置JobDetail任务
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduledTasks scheduledTasks){  
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean ();  
        //这儿设置对应的Job对象  
        bean.setTargetObject (scheduledTasks);  
        //这儿设置对应的方法名  与执行具体任务调度类中的方法名对应  
        bean.setTargetMethod ("work");  
        bean.setConcurrent (false);  
        return bean;  
    }  
  
    @Bean(name = "cronTriggerFactoryBean")  //配置Trigger定时
    public CronTriggerFactoryBean cronTriggerBean(MethodInvokingJobDetailFactoryBean detailFactoryBean){  
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean ();  
        trigger.setJobDetail (detailFactoryBean.getObject ());  
        try {  
            trigger.setCronExpression ("0/5 * * ? * *");//每5秒执行一次 ,,cron表达式，具体百度 
        } catch (Exception e) {  
            e.printStackTrace ();  
        }  
        return trigger;  
  
    }  
  
    @Bean//配置Scheduler容器
    public SchedulerFactoryBean schedulerFactory(CronTriggerFactoryBean cronTriggerFactoryBean){  
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean ();  
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());  
        return schedulerFactoryBean;  
    }  
}