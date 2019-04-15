package com.gaonsoft.ims.vo;

import java.util.Date;

/**
 * @author wsbaek
 *
 */
public class Issue {
	private int id;
	private int projectId;
	private int trackerId; // 일감구분
	private String subject; // 제목
	private String description; // 내용
	private int statusId; // 상태(신규:2(default))
	private int fixedVersionId; // 버전
	private int assignedToId; // 담당자
	private int authorId; // 등록자
	private int priorityId; // 우선순위(신규:3(보통))
	private int lockVersion; // 수정횟수(신규:0)
	private int rootId;
	private int lft;
	private int rgt;
	private int isPrivate; // 비공개
	private Date startDate; // 시작시간
	private Date dueDate; // 완료기한
	private Date createdOn; // 생성
	private Date updatedOn; // 수정

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
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

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getFixedVersionId() {
		return fixedVersionId;
	}

	public void setFixedVersionId(int fixedVersionId) {
		this.fixedVersionId = fixedVersionId;
	}

	public int getAssignedToId() {
		return assignedToId;
	}

	public void setAssignedToId(int assignedToId) {
		this.assignedToId = assignedToId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public int getLockVersion() {
		return lockVersion;
	}

	public void setLockVersion(int lockVersion) {
		this.lockVersion = lockVersion;
	}

	public int getRootId() {
		return rootId;
	}

	public void setRootId(int rootId) {
		this.rootId = rootId;
	}

	public int getLft() {
		return lft;
	}

	public void setLft(int lft) {
		this.lft = lft;
	}

	public int getRgt() {
		return rgt;
	}

	public void setRgt(int rgt) {
		this.rgt = rgt;
	}

	public int getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(int isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "Issue [id=" + id + ", projectId=" + projectId + ", trackerId=" + trackerId + ", subject=" + subject
				+ ", description=" + description + ", statusId=" + statusId + ", fixedVersionId=" + fixedVersionId
				+ ", assignedToId=" + assignedToId + ", authorId=" + authorId + ", priorityId=" + priorityId
				+ ", lockVersion=" + lockVersion + ", rootId=" + rootId + ", lft=" + lft + ", rgt=" + rgt
				+ ", isPrivate=" + isPrivate + ", startDate=" + startDate + ", dueDate=" + dueDate + ", createdOn="
				+ createdOn + ", updatedOn=" + updatedOn + "]";
	}

}
