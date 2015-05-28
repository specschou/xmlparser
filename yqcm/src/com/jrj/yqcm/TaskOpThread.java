package com.jrj.yqcm;

import java.util.Calendar;
import java.util.Date;

import com.jrj.yqcm.Task;
import com.jrj.yqcm.TaskQueue;
import com.jrj.yqcm.TaskUtil;
import com.jrj.yqcm.utils.HttpComponentUtils;
import com.jrj.yqcm.utils.UrlConstants;

public class TaskOpThread extends Thread {
	private String name;
	TaskQueue taskQueue;
	private String mainCookie;
	private String loginUrl;
	private String host;
	private String taskType;
	private int lrt;
	private int refreshCount = 0;
	private boolean start = false;
	private String trainTime;

	public TaskOpThread(String name, TaskQueue taskQueue, String mainCookie,
			String loginUrl, String host, String taskType, int lrt,
			String trainTime) {
		this.name = name;
		this.taskQueue = taskQueue;
		this.mainCookie = mainCookie;
		this.loginUrl = loginUrl;
		this.host = host;
		this.taskType = taskType;
		this.lrt = lrt;
		this.trainTime = trainTime;
	}

	public void run() {
		while (true) {
			try {
				Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minute = c.get(Calendar.MINUTE);
				if (hour == 7 && minute <= 10) {
					reset();
				}
				int start = 13;
				if (trainTime != null) {
					try {
						String[] a = trainTime.split("-");
						start = Integer.parseInt(a[1]);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (hour > start || hour < 7) {
					System.out.println(name + ": tast start! " + refreshCount);
					HttpComponentUtils.login(loginUrl, mainCookie);
					taskQueue.getTask(taskType);
					System.out.println(name + ",queue:" + taskQueue.size()
							+ "," + taskQueue);
					if (taskQueue.empty()) {
						int[] taskInfo = TaskUtil.getLeftRefreshTime(
								mainCookie, host);
						if (taskInfo[0] == 0) {
							try {
								System.out.println(name + ":complete tasks");
								Thread.sleep(30 * 60 * 1000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						} else {
							int leftRefreshTime = taskInfo[1];
							System.out.println(name + ":" + "leftRefreshTime:"
									+ leftRefreshTime);
							if (leftRefreshTime > lrt && refreshCount < 5) {
								refreshCount++;
								HttpComponentUtils.login(loginUrl, mainCookie);
								System.out.println(name + ":" + "refreshTask");
								TaskUtil.refreshTask(mainCookie, host);
							} else {
								try {
									Thread.sleep(60 * 1000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							}
						}
					} else {
						Task t = taskQueue.poll();
						if (t != null) {
							HttpComponentUtils.login(loginUrl, mainCookie);
							TaskUtil.updateScene(t.getSceneId(), mainCookie,
									host);
							while (t.getLeftMatchId().size() > 0) {
								try {
									String matchId = t.getLeftMatchId().poll();
									String url = (host + UrlConstants.SIGN_COK)
											.replace("$1", matchId);
									HttpComponentUtils.login(loginUrl, mainCookie);
									String content = HttpComponentUtils.get(
											url, mainCookie);
									while (content.indexOf("挑战成功") == -1) {
										if (content.indexOf("您不在该场景中") != -1) {
											TaskUtil.updateScene(
													t.getSceneId(), mainCookie,
													host);
										}
										try {
											System.out.println(name + ","
													+ "content:"
													+ formatContent(content));
											Thread.sleep(60 * 1000);
											HttpComponentUtils.login(loginUrl, mainCookie);
											content = HttpComponentUtils.get(
													url, mainCookie);
										} catch (Exception e) {
											e.printStackTrace();
											try {
												Thread.sleep(60 * 1000);
											} catch (InterruptedException e1) {
												e1.printStackTrace();
											}
										}
									}
									System.out.println(name + ","
											+ ",TrainMatchTask," + new Date()
											+ formatContent(content));
								} catch (Exception e) {
									e.printStackTrace();
									try {
										Thread.sleep(60 * 1000);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								}
							}
						} else {
							try {
								Thread.sleep(60 * 1000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					}
					HttpComponentUtils.login(loginUrl, mainCookie);
					TaskUtil.getTaskAward(mainCookie, host);
				} else {
//					System.out.println(name + ": tast not start! "
//							+ refreshCount);
					try {
						Thread.sleep(60 * 1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void reset() {
		refreshCount = 0;
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

	public int getRefreshCount() {
		return refreshCount;
	}

	public void setRefreshCount(int refreshCount) {
		this.refreshCount = refreshCount;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

}
