package com.joveo.rest;

import java.util.List;

public class JobService {
	JobDao jobDao = new JobDao();
	public final int maxAllowed = 3;

	public List<Job> getAllJobs() {
		List<Job> jobList = jobDao.getAllJobs();
		return jobList;
	}

	public Job getJobForId(String id) {
		Job job = jobDao.getJobForId(id);
		return job;
	}

	public Job createJob(Job job) {
		Job jobResponse = jobDao.createJob(job);
		return jobResponse;
	}

	public Job updateJob(Job job) {
		Job jobResponse = jobDao.updateJob(job);
		return jobResponse;
	}

	public Job deleteJob(String id) {
		Job jobResponse = jobDao.deleteJob(id);
		return jobResponse;
	}

}
