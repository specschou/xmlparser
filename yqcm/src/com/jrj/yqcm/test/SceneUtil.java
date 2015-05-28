package com.jrj.yqcm.test;

import java.util.HashSet;
import java.util.Set;

public class SceneUtil {
	public static final Scene scene1 = new Scene(23, "开普敦", -364, -365, -366,
			-367, -368, 84, 75, 0);
	public static final Scene scene2 = new Scene(25, "悉尼", -374, -375, -376,
			-377, -378, 86, 77, 0);
	public static final Scene scene3 = new Scene(27, "洛杉矶", -384, -385, -386,
			-387, -388, 88, 79, 110);
	public static final Scene scene4 = new Scene(29, "布宜诺斯艾利斯", -394, -395,
			-396, -397, -398, 89, 80, 111);
	public static final Scene scene5 = new Scene(31, "都灵", -404, -405, -406,
			-407, -408, 85, 76, 0);
	public static final Scene scene6 = new Scene(33, "利物浦", -414, -415, -416,
			-417, -418, 87, 78, 0);
	public static Set<Scene> sceneSet = new HashSet<Scene>();
	static {
		sceneSet.add(scene1);
		sceneSet.add(scene2);
		sceneSet.add(scene3);
		sceneSet.add(scene4);
		sceneSet.add(scene5);
		sceneSet.add(scene6);
	}

	/**
	 * 根据id获取场景
	 * 
	 * @param id
	 * @return
	 */
	public static Scene getSceneById(int id) {
		for (Scene scene : sceneSet) {
			if (scene.getId() == id) {
				return scene;
			}
		}
		return null;
	}

	/**
	 * 根据名称获取场景
	 * 
	 * @param name
	 * @return
	 */
	public static Scene getSceneByName(String name) {
		for (Scene scene : sceneSet) {
			if (scene.getName().equals(name)) {
				return scene;
			}
		}
		return null;
	}

	/**
	 * 根据训练赛任务获取场景
	 * 
	 * @param task1
	 * @return
	 */
	public static Scene getSceneByTask1(String task1) {
		try {
			return getSceneByTask1(Integer.parseInt(task1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据训练赛任务获取场景
	 * 
	 * @param task1
	 * @return
	 */
	public static Scene getSceneByTask1(int task1) {
		for (Scene scene : sceneSet) {
			if (scene.getTask1() == task1) {
				return scene;
			}
		}
		return null;
	}

	/**
	 * 根据友谊赛任务获取场景
	 * 
	 * @param task2
	 * @return
	 */
	public static Scene getSceneByTask2(String task2) {
		try {
			return getSceneByTask2(Integer.parseInt(task2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据友谊赛任务获取场景
	 * 
	 * @param task2
	 * @return
	 */
	public static Scene getSceneByTask2(int task2) {
		for (Scene scene : sceneSet) {
			if (scene.getTask2() == task2) {
				return scene;
			}
		}
		return null;
	}

	/**
	 * 根据对话任务获取场景
	 * 
	 * @param task3
	 * @return
	 */
	public static Scene getSceneByTask3(String task3) {
		try {
			return getSceneByTask3(Integer.parseInt(task3));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据对话任务获取场景
	 * 
	 * @param task3
	 * @return
	 */
	public static Scene getSceneByTask3(int task3) {
		for (Scene scene : sceneSet) {
			if (scene.getTask3() == task3) {
				return scene;
			}
		}
		return null;
	}
}
