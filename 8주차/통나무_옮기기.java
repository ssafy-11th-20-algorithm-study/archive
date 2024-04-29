package a0425;

import java.io.*;
import java.util.*;

public class 통나무_옮기기 {

	static int N, answer = Integer.MAX_VALUE;
	static char[][] map;
	static boolean[][][] v; // 가로면 0 세로면 1
	static int[][] loc = new int[3][2];
	static int[][] goal = new int[3][2];
	static final int[] dy = { 0, 0, -1, 1, 1, 1, -1, -1 };
	static final int[] dx = { 1, -1, 0, 0, -1, 1, -1, 1 };

	static boolean check33(int y, int x) {
		int ny, nx;
		for (int d = 0; d < 8; d++) {
			ny = y + dy[d];
			nx = x + dx[d];
			if (!rangeCheck(ny, nx) || map[ny][nx] == 1)
				return false;
		}
		return true;
	}

	static boolean rangeCheck(int y, int x) {
		if (0 <= y && y < N && 0 <= x && x < N) {
			return true;
		}
		return false;
	}

	static void dfs(int y, int x, int d, int c) {
		int[][] temp = new int[3][2];
		v[y][x][d] = true;
		temp[1][0] = y;
		temp[1][1] = x;
		if (d == 0) {
			temp[0][0] = y;
			temp[0][1] = x - 1;
			temp[2][0] = y;
			temp[2][1] = x + 1;
		} else {
			temp[0][0] = y - 1;
			temp[0][1] = x;
			temp[2][0] = y + 1;
			temp[2][1] = x;
		}

		int count = 0;

		for (int i = 0; i < 3; i++) {
			if (temp[i][0] == goal[i][0] && temp[i][1] == goal[i][1]) {
				count++;
			}
		}

		if (count == 3) {
			v[y][x][d] = false;
			answer = Math.min(c, answer);
			return;
		}

		int ny, nx;

		label: for (int dr = 0; dr < 4; dr++) {
			for (int l = 0; l < 3; l++) {
				ny = dy[dr] + temp[l][0];
				nx = dx[dr] + temp[l][1];
				if (!rangeCheck(ny, nx) || map[ny][nx] == 1) {
					continue label;
				}
			}
			ny = temp[1][0] + dy[dr];
			nx = temp[1][1] + dx[dr];
//			for (int[] t: temp) System.out.println(Arrays.toString(t));
//			System.out.print(y);
//			System.out.print(x);
//			System.out.print(ny);
//			System.out.print(nx);
//			System.out.println();
			if (!v[ny][nx][d]) {
				dfs(ny, nx, d, c + 1);
			}
		}

		if (check33(y, x) && !v[y][x][Math.abs(d - 1)]) {
			dfs(y, x, Math.abs(d - 1), c + 1);
		}

		v[y][x][d] = false;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/0425/통나무_옮기기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		v = new boolean[N][N][2];
		String str;
		int idx = 0, idx2 = 0;
		for (int y = 0; y < N; y++) {
			str = br.readLine();
			for (int x = 0; x < N; x++) {
				map[y][x] = str.charAt(x);
				if (map[y][x] == 'B') {
					loc[idx][0] = y;
					loc[idx][1] = x;
					idx++;
				}
				if (map[y][x] == 'E') {
					goal[idx2][0] = y;
					goal[idx2][1] = x;
					idx2++;
				}
			}
		}

		if (loc[0][0] == loc[1][0] && loc[1][0] == loc[2][0]) { // 가로로 있을 경우
			dfs(loc[1][0], loc[1][1], 0, 0);
		} else {
			dfs(loc[1][0], loc[1][1], 1, 0);
		}

		System.out.println(answer);

	}
}