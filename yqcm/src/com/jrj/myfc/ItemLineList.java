package com.jrj.myfc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemLineList {
	Map<String, List<ItemLine>> lineTypeMap = new HashMap<String, List<ItemLine>>();
	Map<String, List<ItemLine>> lineDirectionMap = new HashMap<String, List<ItemLine>>();

	public void add(Item item) {
		for (int i = 1; i <= 4; i++) {
			String key = i + "-" + item.getX();
			if (i == 2) {
				key = i + "-" + item.getY();
			}
			List<ItemLine> directionList = lineDirectionMap.get(key);
			if (directionList == null) {
				directionList = new ArrayList<ItemLine>();
				directionList.add(ItemLine.add(item, 1, 1,
						getHeadBlank(item.getX(), item.getY(), i),
						getTailBlank(item.getX(), item.getY(), i), i));
			} else {
				ItemLine headItemLine = this.findNeighborItemLine(
						directionList, item, i, 1);
				if (headItemLine != null) {
					int distance = headItemLine.getTailItem().getX()
							- item.getX();
					if (i == 2) {
						distance = headItemLine.getTailItem().getY()
								- item.getY();
					}
					if (distance == 1) {
						if (headItemLine.getColor() == item.getColor()) {
							headItemLine.update(item);
						} else {
							headItemLine.setTailBlank(distance - 1);
						}
					} else {
						headItemLine.setTailBlank(distance - 1);
						directionList.add(ItemLine.add(item, 1, 1,
								getHeadBlank(item.getX(), item.getY(), i),
								getTailBlank(item.getX(), item.getY(), i), i));
					}
				}
				ItemLine tailItemLine = this.findNeighborItemLine(
						directionList, item, i, 2);
				if (tailItemLine != null) {
					int distance = tailItemLine.getHeadItem().getX()
							- item.getX();
					if (i == 2) {
						distance = tailItemLine.getHeadItem().getY()
								- item.getY();
					}
					if (distance == 1) {
						if (tailItemLine.getColor() == item.getColor()) {
							tailItemLine.update(item);
						} else {
							tailItemLine.setTailBlank(distance - 1);
						}
					} else {
						tailItemLine.setTailBlank(distance - 1);
					}
				}
			}
		}
	}

	public ItemLine findNeighborItemLine(List<ItemLine> directionList,
			Item item, int direction, int type) {
		int n = item.getX();
		int i = 0;
		int j = 0;
		if (direction == 2) {
			n = item.getY();
			i = n;
			j = Panel.HEIGHT;
		}
		if (type == 1) {
			i = n;
			j = Panel.WIDTH;
			n++;
		} else {
			i = -1;
			j = n;
			n--;
		}
		while (n > i && n < j) {
			for (ItemLine itemLine : directionList) {
				for (Item compareItem : itemLine.getList()) {
					if (direction == 2) {
						if (compareItem.getY() == n) {
							return itemLine;
						}
					} else {
						if (compareItem.getX() == n) {
							return itemLine;
						}
					}
				}
			}
			if (type == 1) {
				n++;
			} else {
				n--;
			}
		}
		return null;
	}

	public int getHeadBlank(int x, int y, int direction) {
		if (direction == 2) {
			return Panel.HEIGHT - y - 1;
		} else {
			return Panel.WIDTH - x - 1;
		}
	}

	public int getTailBlank(int x, int y, int direction) {
		if (direction == 2) {
			return y;
		} else {
			return x;
		}
	}
}
