package com.jrj.yqcm.test;

public class Scene {
	private int id;
	private String name;
	private int match1;// 业余队
	private int match2;// 青年二队
	private int match3;// 青年一队
	private int match4;// 预备一队
	private int match5;// 预备二队
	private int task1;// 训练赛
	private int task2;// 友谊赛
	private int task3;// 对话

	public Scene(int id, String name, int match1, int match2, int match3,
			int match4, int match5, int task1, int task2, int task3) {
		super();
		this.id = id;
		this.name = name;
		this.match1 = match1;
		this.match2 = match2;
		this.match3 = match3;
		this.match4 = match4;
		this.match5 = match5;
		this.task1 = task1;
		this.task2 = task2;
		this.task3 = task3;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMatch1() {
		return match1;
	}

	public void setMatch1(int match1) {
		this.match1 = match1;
	}

	public int getMatch2() {
		return match2;
	}

	public void setMatch2(int match2) {
		this.match2 = match2;
	}

	public int getMatch3() {
		return match3;
	}

	public void setMatch3(int match3) {
		this.match3 = match3;
	}

	public int getMatch4() {
		return match4;
	}

	public void setMatch4(int match4) {
		this.match4 = match4;
	}

	public int getMatch5() {
		return match5;
	}

	public void setMatch5(int match5) {
		this.match5 = match5;
	}

	public int getTask1() {
		return task1;
	}

	public void setTask1(int task1) {
		this.task1 = task1;
	}

	public int getTask2() {
		return task2;
	}

	public void setTask2(int task2) {
		this.task2 = task2;
	}

	public int getTask3() {
		return task3;
	}

	public void setTask3(int task3) {
		this.task3 = task3;
	}

	@Override
	public String toString() {
		return "Scene [id=" + id + ", name=" + name + ", match1=" + match1
				+ ", match2=" + match2 + ", match3=" + match3 + ", match4="
				+ match4 + ", match5=" + match5 + ", task1=" + task1
				+ ", task2=" + task2 + ", task3=" + task3 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Scene other = (Scene) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
