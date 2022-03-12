package com.yee.jobhunting.bean;

public class Job {
    private String jobTitle;    //职位名称
    private String jobSalary;   //工作薪资
    private String jobRequirement;  //工作要求
    private String jobEducation;    //学历要求
    private String jobExperience;    //经验要求
    private String comInformation;  //招聘单位信息
    private int hrImageId;  //招聘者头像
    private String hrName;   //招聘者名称
    private String jobPlace;   //工作地点

    public Job(String jobTitle, String jobSalary, String jobRequirement, String comInformation, int hrImageId, String hrName, String jobPlace) {
        this.jobTitle = jobTitle;
        this.jobSalary = jobSalary;
        this.jobRequirement = jobRequirement;
        this.comInformation = comInformation;
        this.hrImageId = hrImageId;
        this.hrName = hrName;
        this.jobPlace = jobPlace;
    }


    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public String getJobRequirement() {
        return jobRequirement;
    }

    public String getComInformation() {
        return comInformation;
    }

    public int getHrImageId() {
        return hrImageId;
    }

    public String getHrName() {
        return hrName;
    }

    public String getJobPlace() {
        return jobPlace;
    }
}
