package com.nil.sjs.SJSServer.Model;

public class JsubModel {
	private String queue;
	private String resource;
	private String job_name;
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	@Override
	public String toString() {
		return "JsubModel [queue=" + queue + ", resource=" + resource + ", job_name=" + job_name + "]";
	}
	
}
