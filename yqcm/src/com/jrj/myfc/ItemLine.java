package com.jrj.myfc;

import java.util.ArrayList;
import java.util.List;

public class ItemLine {
	private int type = 1;
	private int num = 0;
	private int headBlank = 0;
	private int tailBlank = 0;
	private int direction = 1;
	private int color = 1;
	private Item headItem;
	private Item tailItem;
	private List<Item> list = new ArrayList<Item>();

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getHeadBlank() {
		return headBlank;
	}

	public void setHeadBlank(int headBlank) {
		this.headBlank = headBlank;
	}

	public int getTailBlank() {
		return tailBlank;
	}

	public void setTailBlank(int tailBlank) {
		this.tailBlank = tailBlank;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public List<Item> getList() {
		return list;
	}

	public void setList(List<Item> list) {
		this.list = list;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Item getHeadItem() {
		return headItem;
	}

	public void setHeadItem(Item headItem) {
		this.headItem = headItem;
	}

	public Item getTailItem() {
		return tailItem;
	}

	public void setTailItem(Item tailItem) {
		this.tailItem = tailItem;
	}

	public static ItemLine add(Item item, int type, int num, int headBlank,
			int tailBlank, int direction) {
		ItemLine itemLine = new ItemLine();
		itemLine.type = type;
		itemLine.num = num;
		itemLine.headBlank = headBlank;
		itemLine.tailBlank = tailBlank;
		itemLine.direction = direction;
		itemLine.color = item.getColor();
		itemLine.list.add(item);
		itemLine.headItem = item;
		itemLine.tailItem = item;
		return itemLine;
	}

	public void update(Item item) {
		this.list.add(item);
		this.num++;
		if (direction == 2) {
			if (item.getY() > this.headItem.getY()) {
				this.headBlank--;
				this.headItem = item;
			} else if (item.getY() < this.tailItem.getY()) {
				this.tailBlank--;
				this.tailItem = item;
			}
		} else {
			if (item.getX() > this.headItem.getX()) {
				this.headBlank--;
				this.headItem = item;
			} else if (item.getX() < this.tailItem.getX()) {
				this.tailBlank--;
				this.tailItem = item;
			}
		}
	}
}
