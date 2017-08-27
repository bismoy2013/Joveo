package com.joveo.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Just to avoid DB calls in this example, Assume below data is interacting with DB
public class JobDao {
	static HashMap<String, Job> jobsMap = new HashMap<String, Job>();

	public JobDao() {
		// Job job1 = new Job();
		// job1.setId("1");
		// job1.setStatus(JobStatus.RUNNING);
		// job1.setName("job 1");
		//
		// Job job2 = new Job();
		// job2.setId("2");
		// job2.setStatus(JobStatus.RUNNING);
		// job2.setName("job 2");
		//
		// jobsMap.put("1", job1);
		// jobsMap.put("2", job2);
	}

	public List<Job> getAllJobs() {

		List<Job> jobList = new ArrayList<Job>(jobsMap.values());
		return jobList;
	}

	public Job getJobForId(String id) {
		Job job = jobsMap.get(id);
		return job;
	}

	public Job createJob(Job job) {
		jobsMap.put(job.getId(), job);
		return jobsMap.get(job.getId());
	}

	public Job updateJob(Job job) {
		if (jobsMap.get(job.getId()) != null) {
			jobsMap.get(job.getId()).setName(job.getName());
			jobsMap.get(job.getId()).setStatus(job.getStatus());
		} else {
			jobsMap.put(job.getId(), job);
		}
		return jobsMap.get(job.getId());
	}

	public Job deleteJob(String id) {
		Job jobResponse = jobsMap.remove(id);
		jobsMap.remove(id);
		return jobResponse;
	}

}
