package com.jrj.yqcm.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;

import com.jrj.yqcm.utils.HttpComponentUtils;
import com.jrj.yqcm.utils.UrlConstants;

public class TaskOpTask extends TimerTask {
	private String name;
	List<String> allowTask;
	private String mainCookie;
	private String loginUrl;
	private String host;

	public TaskOpTask(String name, List<String> allowTask, String mainCookie,
			String loginUrl, String host) {
		this.name = name;
		this.allowTask = allowTask;
		this.mainCookie = mainCookie;
		this.loginUrl = loginUrl;
		this.host = host;
	}

	public void run() {
		try {
			String taskUrl = (host + UrlConstants.TASK_INTERFACE).replace("$1",
					"2");
			HttpComponentUtils.login(loginUrl, mainCookie);
			String taskContent = HttpComponentUtils.get(taskUrl, mainCookie);
			Set<String> taskIds = getTaskIdList(taskContent, allowTask);
			System.out.println(taskIds);
			taskIds.add("88");
			for (String taskId : taskIds) {
				String taskOpUrl = (host + UrlConstants.TASK_OP).replace("$1",
						"1").replace("$2", taskId);
				String content = HttpComponentUtils.get(taskOpUrl, mainCookie);
				System.out.println(name + "," + ",TaskOpTask," + new Date()
						+ formatContent(content));
			}
			for (String taskId : taskIds) {
				List<String> matchIdList = addMatchId(taskId);
				for (String matchId : matchIdList) {
					HttpComponentUtils.login(loginUrl, mainCookie);
					String url = (host + UrlConstants.SIGN_COK).replace("$1",
							matchId);
					// System.out.println(url);
					String content = HttpComponentUtils.get(url, mainCookie);
					System.out.println(name + "," + ",TaskOpTask,taskId:"
							+ taskId + ",matchId:" + matchId + "," + new Date()
							+ formatContent(content));
					try {
						Thread.sleep(3 * 60 * 1000 + 5 * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				String completeTask = (host + UrlConstants.COMPLETE_TASK)
						.replace("$1", taskId);
				HttpComponentUtils.get(completeTask, mainCookie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> addMatchId(String taskId) {
		List<String> matchIdList = new ArrayList<String>();
		if ("74".equals(taskId)) {
			matchIdList.add("-348");
			matchIdList.add("-347");
			matchIdList.add("-346");
			matchIdList.add("-345");
			matchIdList.add("-344");
		} else if ("73".equals(taskId)) {
			matchIdList.add("-358");
			matchIdList.add("-357");
			matchIdList.add("-356");
			matchIdList.add("-355");
			matchIdList.add("-354");
		} else if ("75".equals(taskId)) {
			matchIdList.add("-368");
			matchIdList.add("-367");
			matchIdList.add("-366");
			matchIdList.add("-365");
			matchIdList.add("-364");
		} else if ("77".equals(taskId)) {
			matchIdList.add("-378");
			matchIdList.add("-377");
			matchIdList.add("-376");
			matchIdList.add("-375");
			matchIdList.add("-374");
		} else if ("79".equals(taskId)) {
			matchIdList.add("-388");
			matchIdList.add("-387");
			matchIdList.add("-386");
			matchIdList.add("-385");
			matchIdList.add("-384");
		} else if ("83".equals(taskId)) {
			for (int i = 0; i < 10; i++) {
				matchIdList.add("-344");
			}
		} else if ("82".equals(taskId)) {
			for (int i = 0; i < 10; i++) {
				matchIdList.add("-354");
			}
		} else if ("84".equals(taskId)) {
			for (int i = 0; i < 10; i++) {
				matchIdList.add("-364");
			}
		} else if ("86".equals(taskId)) {
			for (int i = 0; i < 10; i++) {
				matchIdList.add("-374");
			}
		} else if ("88".equals(taskId)) {
			for (int i = 0; i < 10; i++) {
				matchIdList.add("-384");
			}
		}
		return matchIdList;
	}

	public Set<String> getTaskIdList(String content, List<String> allowTask) {
		Set<String> set = new HashSet<String>();
		int index = 0;
		while (index != -1) {
			index = content.indexOf("PopupTask", index);
			if (index != -1) {
				int begin = content.indexOf(",", index) + 1;
				int end = content.indexOf(",", begin);
				if (begin != -1 && end != -1) {
					String taskId = content.substring(begin, end);
					// System.out.println("taskId:"+taskId);
					if (allowTask.contains(taskId)) {
						set.add(taskId);
					}
				}
				index++;
			}
		}
		return set;
	}

	public String formatContent(String content) {
		int index = -1;
		int begin = -1;
		int end = -1;
		if ((index = content.indexOf("public_d_process")) != -1) {
			begin = index + 18;
		} else if ((index = content.indexOf("public_d_content")) != -1) {
			begin = index + 18;
		}
		end = content.indexOf("</div>", index);
		if (begin != -1 && end != -1) {
			content = content.substring(begin, end);
		}
		return content;
	}
}
