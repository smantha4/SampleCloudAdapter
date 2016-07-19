package com.manthalabs.portfoliomanager.analytics;

public class Analysis implements Comparable<Analysis> {

	private String message;
	private boolean suppress;
	private String priority = "1";
	private String originalTime;

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getOriginalTime() {
		return originalTime;
	}

	public void setOriginalTime(String originalTime) {
		this.originalTime = originalTime;
	}

	public Analysis(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuppress() {
		return suppress;
	}

	public void setSuppress(boolean suppress) {
		this.suppress = suppress;
	}

	@Override
	public int compareTo(Analysis o) {
		return o.priority.compareTo(this.priority);
	}

}