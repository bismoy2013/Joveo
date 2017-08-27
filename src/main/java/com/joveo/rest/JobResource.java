package com.joveo.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.NotFoundException;

@Path("/job")
public class JobResource {
	JobService jobService = new JobService();
	private final static Logger logger = Logger.getLogger(JobResource.class
			.getName());

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Job createJob(Job job) {
		try {
			Job jobResponse = null;
			if (jobService.getAllJobs().size() >= jobService.maxAllowed) {
				job.setStatus(JobStatus.QUEUED);
				jobResponse = jobService.createJob(job);
			} else {
				jobResponse = jobService.createJob(job);
			}
			return jobResponse;
		} catch (Exception e) {
			logger.info("Error : " + e.toString());
			throw new NotFoundException();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Job> getAllJobs() {
		try {
			List<Job> jobList = jobService.getAllJobs();
			return jobList;
		} catch (Exception e) {
			logger.info("Error : " + e.toString());
			throw new NotFoundException();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Job getJobById(@PathParam("id") String id) {
		try {
			Job job = jobService.getJobForId(id);
			return job;
		} catch (Exception e) {
			logger.info("Error : " + e.toString());
			throw new NotFoundException();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Job updateJob(Job job) {
		try {
			Job jobResponse = jobService.updateJob(job);
			return jobResponse;
		} catch (Exception e) {
			logger.info("Error : " + e.toString());
			throw new NotFoundException();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Job deleteeJob(@PathParam("id") String id) {
		try {
			Job jobResponse = jobService.deleteJob(id);
			if (jobService.getAllJobs() != null
					|| jobService.getAllJobs().size() > 0) {
				if (jobService.maxAllowed > jobService.getAllJobs().size()) {
					jobService.getAllJobs()
							.get(jobService.getAllJobs().size() - 1)
							.setStatus(JobStatus.RUNNING);
				} else {
					jobService.getAllJobs().get(jobService.maxAllowed - 1)
							.setStatus(JobStatus.RUNNING);
				}
			}
			return jobResponse;
		} catch (Exception e) {
			logger.info("Error : " + e.toString());
			throw new NotFoundException();
		}
	}

}
