package com.example.demo.utils;

public class ReplyPager extends Pager {
	// 페이지당 게시물 수
	public static final int PAGE_SCALE = 5;
	// 화면당 페이지 수
	public static final int BLOCK_SCALE = 4;

	public ReplyPager(int count, int curPage) {
		super(count, curPage, PAGE_SCALE, BLOCK_SCALE);
	}

}
