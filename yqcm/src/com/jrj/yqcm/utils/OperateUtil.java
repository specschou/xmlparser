package com.jrj.yqcm.utils;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperateUtil {
	public static void updateScene(String cookie, String host, String sceneId) {
		try {
			String url = (host + UrlConstants.SCENE_UPDATE).replace("$1",
					sceneId);
			// System.out.println("url:" + url);
			get(url, cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void yearMatch(String cookie, String host) {
		try {
			String url = (host + UrlConstants.YEAR_MATCH);
			// System.out.println("url:" + url);
			get(url, cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void trainMatch(String cookie, String host) {
		try {
			updateScene(cookie, host, "8");
			String url = (host + UrlConstants.TRAIN_MATCH);
			// System.out.println("url:" + url);
			get(url, cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getActiveTask(String cookie, String host) {
		try {
			for (int i = 1; i <= 6; i++) {
				String url = (host + UrlConstants.GET_ACTIVE_TASK).replace(
						"$1", String.valueOf(i));
				get(url, cookie);
				// System.out.println("url:" + url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void stadiumSign(String cookie, String host) {
		try {
			String url = (host + UrlConstants.STADIUM_SIGN);
			get(url, cookie);
			// System.out.println("url:" + url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void president(String cookie, String host, String c) {
		try {
			String url = (host + UrlConstants.PRESIDENT_DO).replace("$1", c);
			get(url, cookie);
			// System.out.println("url:" + url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void changeLineip(String cookie, String host, String c) {
		try {
			String url = (host + UrlConstants.CHANGE_LINEUP).replace("$1", c);
			// System.out.println("url:" + url);
			get(url, cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void dayTask(String cookie, String host) {
		try {
			finishDayTask(cookie, host);
			String url = host + UrlConstants.TASK_INTERFACE.replace("$1", "2");
			String content = get(url, cookie);
			String[] items = content.split("PopupTask");
			for (String s : items) {
				int index = s.indexOf("FinishTaskByCoinAlert");
				if ((s.indexOf("训练赛") != -1 && (index == -1 || index > s
						.indexOf("训练赛")))
						|| (s.indexOf("友谊赛") != -1 && (index == -1 || index > s
								.indexOf("训练赛")))) {
					int begin = s.indexOf(",");
					int end = s.indexOf(",", begin + 1);
					if (begin != -1 && end != -1) {
						String taskId = s.substring(begin + 1, end);
						url = (host + UrlConstants.TASK_OP).replace("$1", "1")
								.replace("$2", taskId);
						get(url, cookie);
					}
				}
			}
			url = host + UrlConstants.SECRETARY_ALL.replace("$1", "8");
			System.out.println("url:" + url);
			String aa = get(url, cookie);
			System.out.println("aa:" + aa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String freshDayTask(String cookie, String host) {
		String url = host + UrlConstants.SECRETARY_ALL.replace("$1", "9");
		String content = get(url, cookie);
		return content;
	}

	public static void finishDayTask(String cookie, String host) {
		try {
			String url = host + UrlConstants.TASK_INTERFACE.replace("$1", "2");
			// System.out.println("url:" + url);
			String content = get(url, cookie);
			String[] items = content.split("PopupTask");
			for (String s : items) {
				if (s.indexOf("领取奖励") != -1) {
					int begin = s.indexOf(",");
					int end = s.indexOf(",", begin + 1);
					if (begin != -1 && end != -1) {
						String taskId = s.substring(begin + 1, end);
						// System.out.println("taskId:" + taskId);
						begin = s.indexOf(",", begin + 1);
						end = s.indexOf(",", begin + 1);
						if (begin != -1 && end != -1) {
							String npcId = s.substring(begin + 1, end);
							// System.out.println("npcId:" + npcId);
							url = (host + UrlConstants.COMPLETE_TASK).replace(
									"$1", taskId).replace("$2", npcId);
							// System.out.println("url2:" + url);
							get(url, cookie);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void unionTask(String cookie, String host) {
		try {
			finishUnionTask(cookie, host);
			String url = host + UrlConstants.ENTER_UNION;
			get(url, cookie);
			url = host + UrlConstants.UNION_OP_13;
			String s = get(url, cookie);
			// System.out.println("s:" + s);
			Pattern p = Pattern
					.compile("(PopupTask\\()(.*?)(,)(.*?)(,)(.*?)(\\))");
			Matcher m = p.matcher(s);
			while (m.find()) {
				String taskCategory = m.group(4);
				// System.out.println("taskCategory:" + taskCategory);
				url = host
						+ UrlConstants.GET_UNION_TASK.replace("$1",
								taskCategory);
				get(url, cookie);
				url = host + UrlConstants.SECRETARY;
				get(url, cookie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String freshUnionTask(String cookie, String host) {
		String url = host + UrlConstants.SECRETARY_13;
		String content = get(url, cookie);
		return content;
	}

	public static void finishUnionTask(String cookie, String host) {
		try {
			String url = host + UrlConstants.TASK_INTERFACE.replace("$1", "7");
			String content = get(url, cookie);
			Pattern p = Pattern
					.compile("(PopupTask\\()(.*?)(,)(.*?)(,)(.*?)(,)(.*?)(\\))");
			Matcher m = p.matcher(content);
			while (m.find()) {
				url = host
						+ UrlConstants.FINISH_UNION_TASK
								.replace("$1", m.group(2))
								.replace("$2", m.group(4))
								.replace("$3", m.group(6))
								.replace("$4", m.group(8));
				System.out.println("mmm:" + url);
				content = get(url, cookie);
				// System.out.println("finishUnionTask:" + content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void union_op(String cookie, String host, int unionSL) {
		try {
			String url = host + UrlConstants.UNION_OP_57;
			String content = get(url, cookie);
			Pattern p = Pattern
					.compile("(ShowExecutiveCommittee\\()(.*?)(</tr>)");
			Pattern p1 = Pattern
					.compile("(ShowExecutiveCommittee\\()(.*?)(\\))");
			Pattern p2 = Pattern.compile("(TeamAbility\" value=\")(.*?)(\")");
			Pattern p3 = Pattern
					.compile("(spanUnionWarUpdateTime)(.*?)(\">)(.*?)(</span>)");
			Matcher m = p.matcher(content);
			while (m.find()) {
				String line = m.group();
				Matcher m3 = p3.matcher(line);
				if (m3.find()) {
					// System.out.println("group:" + m3.group());
					String warID = m3.group(2);
					// System.out.println("warID:" + warID);
					// try {
					// String leftTime = m3.group(4);
					// // System.out.println("leftTime:" + leftTime);
					// leftTime = leftTime.replace(":", ".");
					// double lt = 0;
					// try {
					// lt = Double.parseDouble(leftTime);
					// // System.out.println("lt:" + lt);
					// } catch (Exception ex) {
					// ex.printStackTrace();
					// }
					// // System.out.println("warID:" + warID);
					// if (lt < 8) {
					// OperateUtil.changeLineip(cookie, host, "1");
					// doUnionWar(cookie, host, warID);
					// continue;
					// }
					// } catch (Exception ex1) {
					// ex1.printStackTrace();
					// }
					// System.out.println("line:" + line);
					Matcher m1 = p1.matcher(line);
					if (m1.find()) {
						String id = m1.group(2);
						// System.out.println(id);
						url = (host + UrlConstants.SHOW_CLUB).replace("$1", id);
						content = get(url, cookie);
						Matcher m2 = p2.matcher(content);
						if (m2.find()) {
							String sl = m2.group(2);
							// System.out.println(sl);
							double sld = 0;
							try {
								sld = Double.parseDouble(sl);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							// System.out.println(sld);
							// if (sld >= unionSL && sld < 200) {
							if (sld < unionSL) {
								// System.out.println("warID:" + warID);
								// OperateUtil.changeLineip(cookie, host, "1");
								doUnionWar(cookie, host, warID);
							}
						}
					}
				}
			}
			// OperateUtil.changeLineip(cookie, host, "1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void doUnionWar(String cookie, String host, String warID) {
		String url = (host + UrlConstants.UNION_OP_42).replace("$1", warID);
		get(url, cookie);
		url = (host + UrlConstants.UNION_OP_49).replace("$1", warID);
		String aa = get(url, cookie);
		System.out.println("unionSL success");
	}

	public static void test(String cookie, String host) {
		for (int i = 0; i < 100; i++) {
			String url = "http://f9.xba.com.cn/Task/TaskOP.aspx?Type=6&TaskCategory="
					+ i;
			String content = get(url, cookie);
			System.out.println(i);
			if (content.indexOf("曼") != -1) {
				System.out.println("content:" + content);
			}
		}
	}

	public static String scoutRunWeb(String cookie, String host, String football) {
		String url = host + UrlConstants.SCOUT_RUN;
		String content = getWeb(url, cookie, football);
		// System.out.println("content:" + content);
		return content;
	}

	public static String scoutRun(String cookie, String host) {
		String url = host + UrlConstants.SCOUT_RUN;
		String content = get(url, cookie);
		// System.out.println("content:" + content);
		return content;
	}

	public static String scoutResuoltWeb(String cookie, String host,
			String football) {
		String url = host + UrlConstants.SCOUT_RESULT;
		String content = getWeb(url, cookie, football);
		// System.out.println("content:" + content);
		return content;
	}

	public static String scoutResuolt(String cookie, String host) {
		String url = host + UrlConstants.SCOUT_RESULT;
		String content = get(url, cookie);
		// System.out.println("content:" + content);
		return content;
	}

	public static void buyAndSell(String cookie, String host, String content) {
		Pattern p = Pattern
				.compile("(ShowPlayerTemplet\\()(.*?)(,)(.*?)(</a>)");
		Matcher m = p.matcher(content);
		while (m.find()) {
			String s = m.group();
			String id = m.group(2);
			if (s.indexOf("color:#00db00") != -1) {
				String url = (host + UrlConstants.BUY_PLAYER).replace("$1", id);
				// System.out.println("aabb: url:" + url);
				get(url, cookie);
				sell(cookie, host);
				return;
			}
		}
	}

	public static void buyAndSellWeb(String cookie, String host,
			String football, String content) {
		Pattern p = Pattern
				.compile("(ShowPlayerTemplet\\()(.*?)(,)(.*?)(</a>)");
		Matcher m = p.matcher(content);
		while (m.find()) {
			String s = m.group();
			String id = m.group(2);
			if (s.indexOf("color:#00db00") != -1) {
				String url = (host + UrlConstants.BUY_PLAYER).replace("$1", id);
				// System.out.println("aabb: url:" + url);
				getWeb(url, cookie, football);
				sellWeb(cookie, host, football);
				return;
			}
		}
	}

	public static void sell(String cookie, String host) {
		String url = host + UrlConstants.PLAYER_LIST;
		String content = get(url, cookie);
		Pattern p = Pattern.compile("(<span)(.*?)(</span>)");
		Pattern idp = Pattern.compile("(id=\"sp)(.*?)(\">)");
		Matcher m = p.matcher(content);
		while (m.find()) {
			String s = m.group();
			if (s.indexOf("font") != -1 && (s.indexOf("00db00") != -1)) {
				// System.out.println(s);
				Matcher idM = idp.matcher(s);
				if (idM.find()) {
					String id = idM.group(2);
					// System.out.println(id);
					url = (host + UrlConstants.SELL_PLAYER).replace("$1", id);
					get(url, cookie);
				}
			}
		}
	}

	public static void sellWeb(String cookie, String host, String football) {
		String url = host + UrlConstants.PLAYER_LIST;
		String content = getWeb(url, cookie, football);
		Pattern p = Pattern.compile("(<span)(.*?)(</span>)");
		Pattern idp = Pattern.compile("(id=\"sp)(.*?)(\">)");
		Matcher m = p.matcher(content);
		while (m.find()) {
			String s = m.group();
			if (s.indexOf("font") != -1 && (s.indexOf("00db00") != -1)) {
				// System.out.println(s);
				Matcher idM = idp.matcher(s);
				if (idM.find()) {
					String id = idM.group(2);
					// System.out.println(id);
					url = (host + UrlConstants.SELL_PLAYER).replace("$1", id);
					getWeb(url, cookie, football);
				}
			}
		}
	}

	public static String npCokNew(String cookie, String host) {
		String url = host + UrlConstants.NPCOKNEW;
		String content = get(url, cookie);
		// System.out.println("content:" + content);
		return content;
	}

	public static String playerTrain(String id, String cookie, String host) {
		String url = (host + UrlConstants.MARKET_TRANINER).replace("$1", "6")
				.replace("$2", id);
		String content = get(url, cookie);
		// System.out.println("content:" + content);
		return content;
	}

	public static String exchange(String cost, String cookie, String host) {
		String url = (host + UrlConstants.EXCHAGNE).replace("$1", cost);
		String content = get(url, cookie);
		// System.out.println("content:" + content);
		return content;
	}

	public static String getChiefCupOp(String cookie, String host) {
		String url = host + UrlConstants.CHIEF_CUP_OP;
		String content = get(url, cookie);
		// System.out.println("content:" + content);
		return content;
	}

	public static String getStadiumAward(String cookie, String host) {
		String url = host + UrlConstants.STADIUM_AWARD;
		String content = get(url, cookie);
		return content;
	}

	public static String playPlot(String cookie, String host, String plotOrder,
			String plotCategory) {
		String url = (host + UrlConstants.MAP_MATCH_MATCH).replace("$1",
				plotOrder).replace("$2", plotCategory);
		String content = get(url, cookie);
		return content;
	}

	public static String cancelActiveBag(String cookie, String host,
			String giftBag) {
		String url = (host + UrlConstants.COIN_TO_TOOL).replace("$1", "-966")
				.replace("$2", giftBag);
		String content = get(url, cookie);
		return content;
	}

	public static String punishSlave(String name, String cookie, String host) {
		String content = "";
		try {
			Set<String> set = new HashSet<String>();
			String url = host + UrlConstants.SLAVE_LIST;
			// System.out.println("url:" + url);
			content = get(url, cookie);
			Pattern p = Pattern.compile("(<a)(.*?)(</a>)");
			Pattern p1 = Pattern.compile("(ScoldAssistant\\()(.*?)(,)");
			Matcher m = p.matcher(content);
			while (m.find()) {
				String s = m.group();
				// System.out.println(s);
				if (s.indexOf("ScoldAssistant") != -1) {
					Matcher m1 = p1.matcher(s);
					if (m1.find()) {
						String slaveId = m1.group(2);
						System.out.println(name + "，slaveId:" + slaveId);
						set.add(slaveId);
					}
				}
			}
			for (String slaveId : set) {
				url = (host + UrlConstants.SLAVE).replace("$1", slaveId)
						.replace("$2", "1");
				System.out.println("url:" + url);
				content = get(url, cookie);
				System.out.println(name + ",content:" + formatContent(content));
				url = (host + UrlConstants.SLAVE).replace("$1", slaveId)
						.replace("$2", "2");
				System.out.println("url:" + url);
				content = get(url, cookie);
				System.out.println(name + ",content:" + formatContent(content));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String getActiveBag(String cookie, String host) {
		for (int i = 1; i <= 6; i++) {
			String url = (host + UrlConstants.ENTER_BOSS).replace("$1",
					String.valueOf(i));
			String content = "";
			do {
				try {
					content = get(url, cookie);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} while (content.indexOf("没有活跃包") == -1
					&& content.indexOf("活跃包已领取") == -1);
		}
		return "活跃包已经领完";
	}

	public static void getGoldShop(String cookie, String host, String page) {
		String url = host + UrlConstants.GOLD_SHOP + "?Page=" + page;
		String content = get(url, cookie);
		Pattern p = Pattern.compile("(<input)(.*?)(\\);\")");
		Pattern p1 = Pattern.compile("(BuyGiftBag)(.*?)(,)(.*?)(,)");
		Matcher m = p.matcher(content);
		while (m.find()) {
			String s = m.group();
			if (s.indexOf("BuyGiftBag") != -1 && s.indexOf("免费") != -1) {
				Matcher m1 = p1.matcher(s);
				if (m1.find()) {
					// System.out.println("BuyGiftBag:" + m1.group(4));
					String id = m1.group(4);
					// System.out.println("id:" + id);
					url = (host + UrlConstants.FREE_COIN_TO_TOOL).replace("$1",
							"12").replace("$2", id);
					// System.out.println("url:" + url);
					content = get(url, cookie);
					System.out.println("content:" + formatContent(content));
				}
			}
		}
	}

	public static void getGoldShop(String cookie, String host) {
		getGoldShop(cookie, host, "1");
	}

	public static String enterBoss(String cookie, String host) {
		String url = host + UrlConstants.ENTER_BOSS;
		String content = get(url, cookie);
		return content;
	}

	public static String openGiftBag(String cookie, String host) {
		try {
			String itemBoxContent = itemBox(cookie, host);
			Pattern p = Pattern.compile("(\\{)(.*?)(\\})");
			Pattern p1 = Pattern.compile("(TargetID:)(.*?)(,)");
			Matcher m = p.matcher(itemBoxContent);
			String targetId = null;
			while (m.find()) {
				String s = m.group();
				if (s.indexOf("礼包") != -1
						&& s.toLowerCase().indexOf("boss") == -1) {
					// System.out.println("s:" + s);
					Matcher m1 = p1.matcher(s);
					if (m1.find()) {
						// System.out.println("TargetID:" + m1.group(2));
						targetId = m1.group(2);
					}
					break;
				}
			}
			return openGiftBag(targetId, cookie, host);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String openBossBag(String cookie, String host) {
		try {
			String itemBoxContent = itemBox(cookie, host);
			Pattern p = Pattern.compile("(\\{)(.*?)(\\})");
			Pattern p1 = Pattern.compile("(TargetID:)(.*?)(,)");
			Matcher m = p.matcher(itemBoxContent);
			String targetId = null;
			while (m.find()) {
				String s = m.group();
				if (s.toLowerCase().indexOf("boss") != -1) {
					// System.out.println("s:" + s);
					Matcher m1 = p1.matcher(s);
					if (m1.find()) {
						// System.out.println("TargetID:" + m1.group(2));
						targetId = m1.group(2);
					}
					break;
				}
			}
			return openGiftBag(targetId, cookie, host);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String openGiftBag(String targetId, String cookie, String host) {
		try {
			String url = (host + UrlConstants.OPEN_GIFT_BAG).replace("$1",
					targetId);
			System.out.println("url:" + url);
			String content = get(url, cookie);
			return content;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String itemBox(String cookie, String host) {
		String url = host + UrlConstants.ITEM_BOX;
		String content = get(url, cookie);
		return content;
	}

	public static void batchSellPalyers(String[] ids, String user) {
		try {
			// Properties props = PropUtil.getProperties(Constants.DIR + user
			// + ".property");
			Properties props = Config.list.get(Integer.parseInt(user));
			String cookie = props.getProperty("cookie");
			String host = props.getProperty("host");
			String loginUrl = props.getProperty("loginUrl");
			String name = props.getProperty("name");
			HttpComponentUtils.login(loginUrl, user);
			for (String id : ids) {
				String url = (host + UrlConstants.SELL_PLAYER)
						.replace("$1", id);
				System.out.println(name + ",url:" + url);
				String content = get(url, user);
				System.out.println(formatContent(content));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getPlayerList(String user) {
		try {
			// Properties props = PropUtil.getProperties(Constants.DIR + user
			// + ".property");
			Properties props = Config.list.get(Integer.parseInt(user));
			String loginUrl = props.getProperty("loginUrl");
			HttpComponentUtils.login(loginUrl, user);
			// String football = props.getProperty("Football");
			// String cookie = props.getProperty("cookie");
			// cookie = cookie + football;
			String host = props.getProperty("host");
			return getPlayerList(user, user, host);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getPlayerList(String user, String cookie, String host) {
		StringBuilder sb = new StringBuilder();
		String url = host + UrlConstants.PLAYER_LIST;
		String content = get(url, cookie);
		// System.out.println("content:" + formatContent(content));
		Pattern p = Pattern.compile("(<span)(.*?)(</span>)");
		Pattern idp = Pattern.compile("(id=\"sp)(.*?)(\">)");
		Matcher m = p.matcher(content);
		sb.append("<form action=\"/batchsell.jsp\">");
		sb.append("<input name=\"user\" type=\"hidden\" value=\"");
		sb.append(user);
		sb.append("\"/>");
		sb.append("<table>");
		while (m.find()) {
			String s = m.group();
			if (s.indexOf("font") != -1
					&& (s.indexOf("00db00") != -1 || s.indexOf("0080ff") != -1)) {
				// System.out.println(s);
				Matcher idM = idp.matcher(s);
				if (idM.find()) {
					String id = idM.group(2);
					// System.out.println(id);
					sb.append("<tr>");
					sb.append("<td>");
					sb.append(s);
					sb.append("</td>");
					sb.append("<td>");
					sb.append("<input name=\"ids\" type=\"checkbox\" value=\"");
					sb.append(id);
					sb.append("\"/>");
					sb.append("</td>");
					sb.append("</tr>");
				}
			}
		}
		sb.append("<tr>");
		sb.append("<td>");
		sb.append("<input name=\"submit\" type=\"submit\" value=\"确定\"/>");
		sb.append("</td>");
		sb.append("<td>");
		sb.append("<input type=\"checkbox\" name=\"chk_all\" id=\"chk_all\" />");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("</form>");
		return sb.toString();
	}

	public static void specialTrain(String name, String trainIds,
			String cookie, String host) {
		if (trainIds != null) {
			String[] a = trainIds.split(",");
			for (String s : a) {
				String url = (host + UrlConstants.MARKET_TRANINER).replace(
						"$1", "7").replace("$2", s);
				String content = get(url, cookie);
				if (content.indexOf("该球员正在特训中") == -1) {
					// System.out.println(name + ":" + formatContent(content) +
					// "," + trainIds);
				}
			}
		}
	}

	public static void enterStadium(String cookie, String host) {
		String url = host + UrlConstants.ENTER_STADIUM;
		String content = get(url, cookie);
		System.out.println("content:" + formatContent(content));
	}

	public static void getTicket(String cookie, String host) {
		String url = (host + UrlConstants.FIN_INS).replace("$1", "4");
		String content = get(url, cookie);
		if (content.indexOf("今日球票已全部售完") == -1) {
			System.out.println("content:" + formatContent(content));
		}
	}

	public static String getGiftBag(String cookie, String host) {
		String url = host + UrlConstants.GIFT_BAG;
		String content = get(url, cookie);
		int start = content.indexOf("OpenOnlineGiftBag");
		if (start != -1) {
			int begin = content.indexOf("(", start);
			int end = content.indexOf(")", begin + 1);
			if (begin != -1 && end != -1) {
				String giftBag = content.substring(begin + 1, end);
				if (giftBag != null && giftBag.length() < 10) {
					System.out.println("giftBag:" + giftBag);
					url = (host + UrlConstants.COIN_TO_TOOL)
							.replace("$1", "15").replace("$2", giftBag);
					System.out.println("url" + url);
					content = get(url, cookie);
					return content;
				}
				// System.out.println("content:" + formatContent(content));
			}
		}
		return null;
	}

	public static String getWeb(String url, String cookie, String football) {
		String content = "程序错误";
		try {
			try {
				content = HttpComponentUtilsWeb.getWeb(url, cookie, football);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String get(String url, String cookie) {
		String content = "程序错误";
		try {
			try {
				content = HttpComponentUtils.get(url, cookie);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String formatContent(String content) {
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
