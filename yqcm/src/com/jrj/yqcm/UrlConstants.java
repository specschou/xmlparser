package com.jrj.yqcm;

public class UrlConstants {
	public static final String FIN_INS = "/ClubOffice/FinancialInspector.aspx?Type=4";// ������Ʊ
	public static final String TASK_INTERFACE = "/Task/TaskInterface.aspx?Type=$1";// ����,1:��������;2:�ճ�����;3:�;7:��������;9;��Ծ��
	public static final String SCENE_NPC = "/Scene/SceneNPC.aspx?Type=$1&TaskCategory=$2&CurrentNPCID=1";// ��ȡ����
	public static final String TASK_OP = "/Task/TaskOP.aspx?Type=$1&TaskCategory=$2";// ��ȡ����������,$1,1:��ȡ;2:����
	public static final String CoinToTool = "/ToolShop/CoinToTool.aspx?Type=15&GiftBag=83";// ��ȡ�������
	public static final String SIGN_COK = "/NPCMatch/SignForNPCOKNew.aspx?Tag=1&Type=$1";// ѵ������
	public static final String MARKET_TRANINER = "/ClubOffice/MarketTrainer.aspx?Type=$1&PlayerID=$2";// 1:�ر�ѵ��;2:����;3:ѵ����;5:������ѵ;6:����ָ��;7:��ʼ��ѵ
	public static final String COMPLETE_TASK = "/Scene/SceneNPC.aspx?Type=2&TaskCategory=$1&CurrentNPCID=192&RType=2&PlayerIDs=";// ������
}
