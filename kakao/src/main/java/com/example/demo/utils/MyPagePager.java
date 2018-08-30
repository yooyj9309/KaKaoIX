package com.example.demo.utils;

public class MyPagePager extends Pager {
	public static final int PAGE_SCALE = 10;
	public static final int BLOCK_SCALE = 10;

	public MyPagePager(int count, int curPage) {
		super(count, curPage, PAGE_SCALE, BLOCK_SCALE);
	}
}
