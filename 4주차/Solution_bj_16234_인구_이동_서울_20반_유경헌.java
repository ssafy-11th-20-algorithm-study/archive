package com.algorithm.study.eclipse;

import java.io.*;
import java.util.*;

public class Solution_bj_16234_인구_이동_서울_20반_유경헌 {
	static final int[] dx = { -1, 0, 1, 0 };
	static final int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] area = new int[N][N]; // 현재 인구
		int[][] nxtArea = new int[N][N]; // 이동 후 인구
		int[][] vis = new int[N][N]; // day 기록해 방문 체크

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<int[]> q = new ArrayDeque<>();
		ArrayList<int[]> r = new ArrayList<>(); // 연합을 저장
		boolean flag; // 연합 이동 체크
		int day = 1; // vis 기록을 위해 1부터 시작
		while (true) {
			flag = true;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (vis[i][j] == day) // 오늘 이미 조회했으면 다음으로
						continue;
					r.clear(); // 연합을 기록할 ArrayList 비우기
					vis[i][j] = day; // 오늘 날로 방문 체크
					int cnt = 1; // 연합의 수 1로 초기화
					int total = area[i][j]; // 연합 인구 합
					q.offer(new int[] { i, j });
					r.add(new int[] { i, j });
					while (!q.isEmpty()) {
						int[] cur = q.poll();
						for (int d = 0; d < 4; d++) {
							int nx = cur[0] + dx[d];
							int ny = cur[1] + dy[d];
							if (nx >= 0 && nx < N && ny >= 0 && ny < N && vis[nx][ny] < day
									&& Math.abs(area[cur[0]][cur[1]] - area[nx][ny]) >= L
									&& Math.abs(area[cur[0]][cur[1]] - area[nx][ny]) <= R) {
								vis[nx][ny] = day;
								cnt++;
								total += area[nx][ny];
								q.offer(new int[] { nx, ny });
								r.add(new int[] { nx, ny });
							}
						}
					}
					if (r.size() == 1) { // 연합에 포함된 나라가 없다면
						nxtArea[i][j] = area[i][j];
					} 
					else { // 있으면 인구 갱신
						flag = false;
						for (int[] p : r) {
							nxtArea[p[0]][p[1]] = total / cnt; 
						}
					}
				}
			}
			if (flag) // 인구 이동이 없었다면 멈추기
				break;
			// 이동 후 상태로 갱신
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					area[i][j] = nxtArea[i][j];
				}
			}
			day++;
		}
		System.out.println(day - 1);
	}
}
