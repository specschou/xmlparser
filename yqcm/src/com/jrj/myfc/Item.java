package com.jrj.myfc;

public class Item {
	private int color = 1;// 1:black,2:white
	private int x = 0;
	private int y = 0;
	private Item eastItem;
	private Item southItem;
	private Item westItem;
	private Item northItem;

	public Item getEastItem() {
		return eastItem;
	}

	public void setEastItem(Item eastItem) {
		this.eastItem = eastItem;
	}

	public Item getSouthItem() {
		return southItem;
	}

	public void setSouthItem(Item southItem) {
		this.southItem = southItem;
	}

	public Item getWestItem() {
		return westItem;
	}

	public void setWestItem(Item westItem) {
		this.westItem = westItem;
	}

	public Item getNorthItem() {
		return northItem;
	}

	public void setNorthItem(Item northItem) {
		this.northItem = northItem;
	}

	public int getColor() {
		return color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static Item addItem(int x, int y, int color) {
		Item item = new Item();
		item.x = x;
		item.y = y;
		item.color = color;
		return item;
	}

	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]\r\n";
	}

}
