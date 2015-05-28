package com.jrj.yqcm;

import java.util.LinkedList;
import java.util.Queue;

public class Task {
	private String taskId;
	private String sceneId;
	private String type;
	private Queue<String> leftMatchId = new LinkedList<String>();

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Queue<String> getLeftMatchId() {
		return leftMatchId;
	}

	public void setLeftMatchId(Queue<String> leftMatchId) {
		this.leftMatchId = leftMatchId;
	}

	public String poll() {
		return leftMatchId.poll();
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", sceneId=" + sceneId + ", type="
				+ type + ", leftMatchId=" + leftMatchId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		return true;
	}

}
