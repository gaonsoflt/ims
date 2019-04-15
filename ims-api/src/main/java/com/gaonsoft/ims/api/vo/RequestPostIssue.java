package com.gaonsoft.ims.api.vo;

/**
 * @author wsbaek
 *
 */
public class RequestPostIssue {
	private int trackerId; // 일감구분
	private String subject; // 제목
	private String description; // 내용

	@Override
	public String toString() {
		return "RequestPostIssue [trackerId=" + trackerId + ", subject=" + subject + ", description=" + description
				+ "]";
	}

	public int getTrackerId() {
		return trackerId;
	}

	public void setTrackerId(int trackerId) {
		this.trackerId = trackerId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
