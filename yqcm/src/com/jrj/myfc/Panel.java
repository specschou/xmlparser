package com.jrj.myfc;

import java.util.ArrayList;
import java.util.List;

public class Panel {
	public static final int WIDTH = 15;
	public static final int HEIGHT = 15;
	private int next = 1;
	private int step = 0;
	public List<Item> itemList = new ArrayList<Item>();
	public ItemLineList blackItemLine = new ItemLineList();
	public ItemLineList whiteItemLine = new ItemLineList();
	private Item[][] items = new Item[WIDTH][HEIGHT];

	public void add() {
		int x = 0, y = 0;
		if (step == 0) {
			x = WIDTH / 2;
			y = HEIGHT / 2;
		} else if (step == 1) {
			x = WIDTH / 2 + 1;
			y = HEIGHT / 2 + 1;
		}
		this.addItem(x, y);
		Item item = items[x][y];
		itemList.add(item);
	}

	public int win() {
		int result = 0;
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				result = checkItem(items[i][j]);
				if (result != 0) {
					return result;
				}
			}
		}
		return result;
	}

	public int checkItem(Item first) {
		if (first == null) {
			return 0;
		}
		Item neighbor = null;
		int i = 0;
		for (int n = 1; n <= 4; n++) {
			StringBuilder sb = new StringBuilder();
			i = 0;
			neighbor = getNeighbor(first.getX(), first.getY(), n, 1);
			while (neighbor != null && first.getColor() == neighbor.getColor()) {
				i++;
				sb.append(neighbor);
				neighbor = getNeighbor(neighbor.getX(), neighbor.getY(), n, 1);
			}
			neighbor = getNeighbor(first.getX(), first.getY(), n, 2);
			while (neighbor != null && first.getColor() == neighbor.getColor()) {
				i++;
				sb.append(neighbor);
				neighbor = getNeighbor(neighbor.getX(), neighbor.getY(), n, 2);
			}
			if (i == 5) {
				System.out.println(first.getColor() + ":" + sb.toString());
				return first.getColor();
			}
		}
		return 0;
	}

	public Item getNeighbor(int x, int y, int direction, int type) {
		switch (direction) {
		case 1:
			if (type == 1) {
				return getItem(x + 1, y);
			} else {
				return getItem(x - 1, y);
			}
		case 2:
			if (type == 1) {
				return getItem(x, y + 1);
			} else {
				return getItem(x, y - 1);
			}
		case 3:
			if (type == 1) {
				return getItem(x + 1, y + 1);
			} else {
				return getItem(x - 1, y - 1);
			}
		case 4:
			if (type == 1) {
				return getItem(x + 1, y - 1);
			} else {
				return getItem(x - 1, y + 1);
			}
		}
		return null;
	}

	public Item getItem(int x, int y) {
		if (inPanel(x, y)) {
			return items[x][y];
		}
		return null;
	}

	public boolean addItem(int x, int y) {
		if (!inPanel(x, y) || items[x][y] != null) {
			return false;
		}
		Item item = Item.addItem(x, y, next);
		items[x][y] = item;
		itemList.add(item);
		if (next == 1) {
			next = 2;
		} else {
			next = 1;
		}
		step++;
		return true;
	}

	private boolean inPanel(int x, int y) {
		if (x >= WIDTH || x < 0 || y >= HEIGHT || y < 0) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int j = HEIGHT - 1; j >= 0; j--) {
			for (int i = 0; i < WIDTH; i++) {
				if (items[i][j] != null) {
					sb.append(items[i][j].getColor());
				} else {
					sb.append(0);
				}
			}
			sb.append("\r\n");
		}
		return sb.toString();
	}

}
