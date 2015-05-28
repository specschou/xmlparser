package com.jrj.yqcm;

public class UrlConstants {
	public static final String FIN_INS = "/ClubOffice/FinancialInspector.aspx?Type=4";// 销售球票
	public static final String TASK_INTERFACE = "/Task/TaskInterface.aspx?Type=$1";// 任务,1:主线任务;2:日常任务;3:活动;7:联盟任务;9;活跃度
	public static final String SCENE_NPC = "/Scene/SceneNPC.aspx?Type=$1&TaskCategory=$2&CurrentNPCID=1";// 获取任务
	public static final String TASK_OP = "/Task/TaskOP.aspx?Type=$1&TaskCategory=$2";// 领取、放弃任务,$1,1:领取;2:放弃
	public static final String CoinToTool = "/ToolShop/CoinToTool.aspx?Type=15&GiftBag=83";// 领取在线礼包
	public static final String SIGN_COK = "/NPCMatch/SignForNPCOKNew.aspx?Tag=1&Type=$1";// 训练比赛
	public static final String MARKET_TRANINER = "/ClubOffice/MarketTrainer.aspx?Type=$1&PlayerID=$2";// 1:特别训练;2:激发;3:训练点;5:结束特训;6:尽心指导;7:开始特训
	public static final String COMPLETE_TASK = "/Scene/SceneNPC.aspx?Type=2&TaskCategory=$1&CurrentNPCID=192&RType=2&PlayerIDs=";// 交任务
}
