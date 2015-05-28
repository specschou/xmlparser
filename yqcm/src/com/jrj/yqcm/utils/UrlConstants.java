package com.jrj.yqcm.utils;

public class UrlConstants {
	public static final String FIN_INS = "/ClubOffice/FinancialInspector.aspx?Type=$1";// 4,������Ʊ
	public static final String TASK_INTERFACE = "/Task/TaskInterface.aspx?Type=$1";// ����,1:��������;2:�ճ�����;3:�;7:��������;9;��Ծ��
	public static final String REFRESH_TASK = "/Task/TaskInterface.aspx?Type=6&CostType=2";// �ֽ�ˢ���ճ�����
	public static final String SCENE_NPC = "/Scene/SceneNPC.aspx?Type=$1&TaskCategory=$2&CurrentNPCID=1";// ��ȡ����
	public static final String TASK_OP = "/Task/TaskOP.aspx?Type=$1&TaskCategory=$2";// ��ȡ����������,$1,1:��ȡ;2:����;6:�鿴����
	public static final String COIN_TO_TOOL = "/ToolShop/CoinToTool.aspx?Type=$1&GiftBag=$2";// ��ȡ�������
	public static final String FREE_COIN_TO_TOOL = "/ToolShop/CoinToTool.aspx?Type=$1&GiftBagID=$2&CostType=3&CostCoin=0";// ��ȡ������
	public static final String SIGN_COK = "/NPCMatch/SignForNPCOKNew.aspx?Tag=1&Type=$1";// ѵ������
	public static final String MARKET_TRANINER = "/ClubOffice/MarketTrainer.aspx?Type=$1&PlayerID=$2";// 1:�ر�ѵ��;2:����;3:ѵ����;5:������ѵ;6:����ָ��;7:��ʼ��ѵ
	public static final String COMPLETE_TASK = "/Scene/SceneNPC.aspx?Type=2&TaskCategory=$1&CurrentNPCID=$2&RType=2&PlayerIDs=";// ������
	public static final String SCENE_UPDATE = "/Scene/SceneUpdate.aspx?ID=$1";// �����仯
	public static final String GIFT_BAG = "/ToolShop/GiftBag.aspx";// ��ʾ���
	public static final String GOLD_SHOP = "/ToolShop/GoldShop.aspx";// ����̳�
	public static final String ENTER_STADIUM = "/StadiumMatch/StadiumMatch.aspx?Type=3";// ������
	public static final String PLAYER_LIST = "/Player/PlayerListNew.aspx";// ��Ա�б�
	public static final String SELL_PLAYER = "/Player/PlayerOP.aspx?Type=44&PlayerID=$1";// ����Ա
	public static final String ENTER_WORLDCUP = "/WorldCup/WorldCupRoomMatch.aspx?Type=2&WCRC=$1";// ���븱��
	public static final String CREATE_ROOM = "/WorldCup/WorldCupRoomMatch.aspx?Type=6&WCRC=$1&Difficulty=5&WCTID=$2&RewardType=0&PassWord=134";// ��������
	public static final String ENTER_ROOM = "/WorldCup/WorldCupRoomMatch.aspx?Type=8&WCTRID=$1&PW=134";// ���븱��
	public static final String SHOW_TEAM = "/WorldCup/WorldCupRoomMatch.aspx?Type=3&WCRC=$1&WCST=1";// ��ʾ���ж���
	public static final String START_MATCH = "/WorldCup/WorldCupRoomMatch.aspx?Type=11&WCTRID=$1&WCRC=$2";// ��ʼ����
	public static final String NO_MATCH = "/WorldCup/WorldCupRoomMatch.aspx?Type=13&IR=2&WCRC=$1";// ����ʵ��
	public static final String GO_MATCH = "/WorldCup/WorldCupRoomMatch.aspx?Type=13&IR=1&WCRC=$1";// ��Ҫ��ս
	public static final String PRE_WORLDCUP = "/WorldCup/WorldCupRoomMatch.aspx?Type=7";// ���籭׼��
	public static final String EXIT_WORLDCUP = "/WorldCup/WorldCupRoomMatch.aspx?Type=12&WCRC=$1";// �˳����籭
	public static final String ITEM_BOX = "/PlayerTool/ItemBox.aspx?From=MS";// ��ʾ����
	public static final String OPEN_GIFT_BAG = "/ToolShop/CoinToTool.aspx?Type=13&GiftBagLinkID=$1&Refresh=1";// �����
	public static final String ENTER_BOSS = "/BossWar/BossWarInterface.aspx?Type=12";// ����BOSS
	public static final String SLAVE_LIST = "/Assistant/AssistantOP.aspx?Type=16";// ū���б�
	public static final String SLAVE = "/Assistant/AssistantOP.aspx?Type=2&AID=$1&ScoldType=$2";// 1:���2���ͷ�
	public static final String PLOT_SCENE = "/Scene/SceneUpdate.aspx?ID=45";
	public static final String MAP_MATCH_MAP = "/MapMatch/MapMatch.aspx?Type=1&SceneID=45&Category=0";
	public static final String MAP_MATCH_MATCH = "/MapMatch/MapMatch.aspx?Type=4&Order=$1&Category=$2";
	public static final String STADIUM_AWARD = "/StadiumMatch/StadiumMatch.aspx?Type=4&MID=1";
	public static final String CHIEF_CUP_OP = "/ChiefCup/ChiefCupOP.aspx?Type=3";
	public static final String EXCHAGNE = "/ToolShop/CoinToTool.aspx?Type=11&Exchange=1&Cost=$1&CostType=1";
	public static final String NPCOKNEW = "/NPCMatch/SignForNPCOKNew.aspx?Type=-814&S=0";
	public static final String SCOUT_RUN = "/ClubOffice/MarketScout.aspx?Type=6&Action=2&TempletPlayerID=0&SearchCount=0&IsReSch=1";
	public static final String SCOUT_RESULT = "/ClubOffice/MarketScout.aspx?Type=7";
	public static final String UNION_OP_57 = "/Union/UnionOP.aspx?Type=57&UnionID=583";
	public static final String UNION_OP_42 = "/Union/UnionOP.aspx?Type=42&UnionWarID=$1";
	public static final String UNION_OP_49 = "/Union/UnionOP.aspx?Type=49&FinishType=3&UWID=$1";
	public static final String SHOW_CLUB = "/Club/ShowClub.aspx?UserID=$1";
	public static final String ENTER_UNION = "/Scene/SceneUpdate.aspx?ID=29";
	public static final String GET_UNION_TASK = "/Task/TaskOP.aspx?Type=1&TaskCategory=$1";
	public static final String SECRETARY = "/Club/Secretary.aspx?Type=12";
	public static final String FINISH_UNION_TASK = "/Scene/SceneNPC.aspx?Type=$1&TaskCategory=$2&CurrentNPCID=$3&RType=$4&PlayerIDs=&timeStamp=";
	public static final String BUY_PLAYER = "/ClubOffice/MarketScout.aspx?Type=11&PlayerTempletID=$1&Category=1";
	public static final String SECRETARY_13 = "/Club/Secretary.aspx?Type=13";
	public static final String CLUB_OFFICE = "/ClubOffice/ClubOffice.aspx?Type=5";
	public static final String UNION_OP_13 = "/Union/UnionOP.aspx?Type=13";
	public static final String CHANGE_LINEUP = "/Lineup/SetTacticUse.aspx?c=$1&t=3&s=1";
	public static final String PRESIDENT_ENTER = "/President/PresidentInterface.aspx?Type=60";
	public static final String PRESIDENT_DO = "/President/PresidentInterface.aspx?Type=30&Category=$1";
	public static final String SECRETARY_ALL = "/Club/Secretary.aspx?Type=$1";
	public static final String STADIUM_SIGN = "/StadiumMatch/StadiumMatch.aspx?Type=1";
	public static final String GET_ACTIVE_TASK = "/Task/TaskInterface.aspx?Type=10&GT=$1";
	public static final String TRAIN_MATCH = "/NPCMatch/NPCHoldTraining.aspx?Type=1&GameCount=24&Level=-133";
	public static final String YEAR_MATCH = "/NPCMatch/NPCHoldTraining.aspx?Type=5&NPCID=-814&MC=2&C=50";
}
