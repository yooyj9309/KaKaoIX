package com.example.demo.utils;

public class BoardPager extends Pager {
	public static final int PAGE_SCALE = 6;
	public static final int BLOCK_SCALE = 5;

	public BoardPager(int count, int curPage) {
		super(count, curPage, PAGE_SCALE, BLOCK_SCALE);
	}
}
