package com.jrj.yqcm;

import java.util.LinkedList;
import java.util.Queue;

import com.jrj.yqcm.utils.HttpComponentUtils;
import com.jrj.yqcm.utils.UrlConstants;

public class TaskQueue {
	private Queue<Task> queue = new LinkedList<Task>();
	private String host;
	private String mainCookie;
	private String name;

	public TaskQueue(String host, String mainCookie, String name) {
		super();
		this.host = host;
		this.mainCookie = mainCookie;
		this.name = name;
	}

	public boolean offer(Task task) {
		if (!queue.contains(task)) {
			// System.out.println(name+":"+"offer");
			queue.offer(task);
			return true;
		}
		// System.out.println(name+":"+"not offer");
		return false;
	}

	public Task poll() {
		return queue.poll();
	}

	public Task peek() {
		return queue.peek();
	}

	public Task remove() {
		return queue.remove();
	}

	public void getTask(String type) {
		if (type != null) {
			String[] types = type.split(",");
			String url = host + UrlConstants.TASK_INTERFACE.replace("$1", "2");
			String content = HttpComponentUtils.get(url, mainCookie);
			// System.out.println(name+":"+"content:" + content);
			if (content != null) {
				addNewTask(content, types);
				addOldTask(content, types);
			}
		}
	}

	private void addOldTask(String content, String[] types) {
		String[] items = content.split("onclick");
		for (String s : items) {
			try {
				if (s.indexOf("训练赛") != -1
						&& s.indexOf("FinishTaskByCoinAlert") != -1
						&& types[0].equals("1")) {
					addOldTask(s);
				} else if (s.indexOf("友谊赛") != -1
						&& s.indexOf("FinishTaskByCoinAlert") != -1
						&& types[1].equals("1")) {
					addOldTask(s);
				} else if (s.indexOf("队徽") != -1
						&& s.indexOf("FinishTaskByCoinAlert") != -1
						&& types[2].equals("1")) {
					addOldTask(s);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void addOldTask(String s) {
		// System.out.println(name+":"+s);
		int begin = s.indexOf(",");
		int end = s.indexOf(")", begin + 1);
		if (begin != -1 && end != -1) {
			String taskId = s.substring(begin + 1, end);
			// System.out.println(taskId);
			Task task = TaskUtil.get(taskId);
			if (task != null) {
				if (this.offer(task)) {
					// System.out.println(name + ":checkOldTask:" + task);
					checkOldTask(taskId, task);
				}
			}
			// System.out.println(task);
		}
	}

	private void checkOldTask(String taskId, Task task) {
		String url = host
				+ UrlConstants.TASK_OP.replace("$1", "6").replace("$2", taskId);
		// if("specschou1".equals(name)){
		// System.out.println(url);
		// }
		String content = HttpComponentUtils.get(url, mainCookie);
		// System.out.println(name+":"+content);
		if (task.getType().equals("1")) {
			int begin = content.indexOf(">(");
			int end = content.indexOf("&", begin + 1);
			if (begin != -1 && end != -1) {
				try {
					int games = Integer.parseInt(content.substring(begin + 2,
							end));
					// System.out.println(name + ":games:" + games);
					for (int i = 0; i < games; i++) {
						// System.out.println(name + ":taskId poll:" + taskId);
						task.poll();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (task.getType().equals("2")) {
			int isMatch = isMatch(content, "业余队");
			if (isMatch == 1) {
				task.poll();
				isMatch = isMatch(content, "青年二队");
				if (isMatch == 1) {
					task.poll();
					isMatch = isMatch(content, "青年一队");
					if (isMatch == 1) {
						task.poll();
						isMatch = isMatch(content, "预备二队");
						if (isMatch == 1) {
							task.poll();
						}
					}
				}
			}
		} else if (task.getType().equals("3")) {
			int begin = content.indexOf(">(");
			int end = content.indexOf("&", begin + 1);
			if (begin != -1 && end != -1) {
				try {
					int games = Integer.parseInt(content.substring(begin + 2,
							end));
					// System.out.println(name + ":games:" + games);
					for (int i = 0; i < games; i++) {
						// System.out.println(name + ":taskId poll:" + taskId);
						task.poll();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private int isMatch(String content, String name) {
		int start = content.indexOf(name);
		int begin = content.indexOf(">(", start);
		int end = content.indexOf("&", begin + 1);
		if (begin != -1 && end != -1) {
			try {
				int games = Integer.parseInt(content.substring(begin + 2, end));
				// System.out.println("games:" + games);
				return games;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	private void addNewTask(String content, String[] types) {
		String[] items = content.split("PopupTask");
		for (String s : items) {
			try {
				int index = s.indexOf("FinishTaskByCoinAlert");
				if (s.indexOf("训练赛") != -1 && types[0].equals("1")
						&& (index == -1 || index > s.indexOf("训练赛"))) {
					addNewTask(s);
				} else if (s.indexOf("友谊赛") != -1 && types[1].equals("1")
						&& (index == -1 || index > s.indexOf("训练赛"))) {
					addNewTask(s);
				} else if (s.indexOf("队徽") != -1 && types[2].equals("1")
						&& (index == -1 || index > s.indexOf("训练赛"))) {
					addNewTask(s);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void addNewTask(String s) {
		// System.out.println(s);
		int begin = s.indexOf(",");
		int end = s.indexOf(",", begin + 1);
		if (begin != -1 && end != -1) {
			String taskId = s.substring(begin + 1, end);
			// System.out.println(taskId);
			Task task = TaskUtil.get(taskId);
			if (task != null) {
				if (TaskUtil.getTask(taskId, mainCookie, host)) {
					this.offer(task);
				}
			}
			// System.out.println(task);
		}
	}

	public int size() {
		return queue.size();
	}

	public boolean empty() {
		return size() == 0;
	}

	@Override
	public String toString() {
		return "TaskQueue [queue=" + queue + ", host=" + host + ", mainCookie="
				+ mainCookie + "]";
	}
}
