package com.jrj.yqcm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.jrj.yqcm.utils.Config;
import com.jrj.yqcm.utils.Constants;
import com.jrj.yqcm.utils.HttpComponentUtils;
import com.jrj.yqcm.utils.OperateUtil;
import com.jrj.yqcm.utils.UrlConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TaskUtil {
	private static Map<String, Task> map = new HashMap<String, Task>();
	static {
//		String fileName = Constants.DIR + "task.data";
//		File file = new File(fileName);
//		BufferedReader br = null;
//		try {
//			// 从文件中对应的用户列表
//			String line = null;
//			br = new BufferedReader(new FileReader(file));
//			StringBuilder sb = new StringBuilder();
//			while ((line = br.readLine()) != null) {
//				sb.append(line);
//			}
//			JSONArray ja = JSONArray.fromObject(sb.toString());
//			for (Object o : ja) {
//				JSONObject jo = (JSONObject) o;
//				Task task = new Task();
//				String taskId = jo.getString("taskId");
//				task.setTaskId(taskId);
//				task.setSceneId(jo.getString("sceneId"));
//				task.setType(jo.getString("type"));
//				JSONArray leftMatchId = jo.getJSONArray("leftMatchId");
//				for (Object ob : leftMatchId) {
//					task.getLeftMatchId().offer(ob.toString());
//				}
//				map.put(taskId, task);
//			}
//			// System.out.println("map:" + map);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (br != null) {
//				try {
//					br.close();
//					br = null;
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		JSONArray ja = JSONArray.fromObject(Config.task);
		for (Object o : ja) {
			JSONObject jo = (JSONObject) o;
			Task task = new Task();
			String taskId = jo.getString("taskId");
			task.setTaskId(taskId);
			task.setSceneId(jo.getString("sceneId"));
			task.setType(jo.getString("type"));
			JSONArray leftMatchId = jo.getJSONArray("leftMatchId");
			for (Object ob : leftMatchId) {
				task.getLeftMatchId().offer(ob.toString());
			}
			map.put(taskId, task);
		}
		 System.out.println("map:" + map);
	}

	public static boolean getTask(String taskId, String cookie, String host) {
		String url = (host + UrlConstants.TASK_OP).replace("$1", "1").replace(
				"$2", taskId);
		String content = HttpComponentUtils.get(url, cookie);
		if (content != null && content.indexOf("您成功地领取了该任务") != -1) {
			return true;
		}
		return false;
	}

	public static void getTaskAward(String cookie, String host) {
		String url = host + UrlConstants.TASK_INTERFACE.replace("$1", "2");
		String content = HttpComponentUtils.get(url, cookie);
		String[] items = content.split("PopupTask");
		for (String s : items) {
			try {
				if (s.indexOf("领取奖励") != -1) {
					// System.out.println("s:" + s);
					int begin = s.indexOf(",");
					int end = s.indexOf(",", begin + 1);
					if (begin != -1 && end != -1) {
						try {
							String taskId = s.substring(begin + 1, end);
							begin = s.indexOf(",", begin + 1);
							end = s.indexOf(",", begin + 1);
							if (begin != -1 && end != -1) {
								String npcId = s.substring(begin + 1, end);
								System.out.println("taskId:" + taskId
										+ ",npcId:" + npcId);
								url = (host + UrlConstants.COMPLETE_TASK)
										.replace("$1", taskId).replace("$2",
												npcId);
								System.out.println("url:" + url);
								content = HttpComponentUtils.get(url, cookie);
								System.out.println("content:"
										+ OperateUtil.formatContent(content));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void updateScene(String sceneId, String cookie, String host) {
		String url = (host + UrlConstants.SCENE_UPDATE).replace("$1", sceneId);
		System.out.println("更新场景:" + sceneId);
		HttpComponentUtils.get(url, cookie);
	}

	public static int[] getLeftRefreshTime(String cookie, String host) {
		int[] result = { 1, 7200 };
		String url = (host + UrlConstants.TASK_INTERFACE).replace("$1", "2");
		String content = HttpComponentUtils.get(url, cookie);
		if (content != null) {
			if (content.indexOf("<font color='red'>20&nbsp;/&nbsp;20</font>") != -1) {
				result[0] = 0;
			} else {
				int start = content.indexOf("spTaskRefreshTime");
				if (start != -1) {
					int begin = content.indexOf(",", start);
					int end = content.indexOf(",", begin + 1);
					if (begin != -1 && end != -1) {
						try {
							result[1] = Integer.parseInt(content.substring(
									begin + 1, end));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return result;
	}

	public static Task get(String taskId) {
		Task newTask = null;
		try {
			Task oldTask = map.get(taskId);
			if (oldTask != null) {
				newTask = new Task();
				newTask.setType(oldTask.getType());
				newTask.setTaskId(oldTask.getTaskId());
				newTask.setSceneId(oldTask.getSceneId());
				Queue<String> leftMatchId = new LinkedList<String>();
				for (String s : oldTask.getLeftMatchId()) {
					leftMatchId.add(s);
				}
				newTask.setLeftMatchId(leftMatchId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newTask;
	}

	public static void main(String[] args) {

	}

	public static void refreshTask(String cookie, String host) {
		String url = host + UrlConstants.REFRESH_TASK;
		HttpComponentUtils.get(url, cookie);
	}
}
