package com.joveo.rest;

/**
 * 
 * @author bismoy
 *
 */

public enum JobStatus {

	RUNNING(1), QUEUED(2), STOPPED(3),ERROR(4);

	private int value;

	JobStatus(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}

}
