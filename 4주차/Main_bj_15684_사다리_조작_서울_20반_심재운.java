package a0216;

import java.io.*;
import java.util.*;

public class Main_bj_15684_사다리_조작_서울_20반_심재운 {

	static int N, M, H;
	static int[][] arr;
	static int answer;
	static boolean flag;
	static int limit; // 제한

	static boolean ladder(int y) { // 사다리 검사
		int result = y;
		int x = 1;
		while (x <= H) {
			if (arr[x][result] != 0) { // 사다리가 연결되었을 경우 이동
				result = arr[x][result];
			}
			x++;
		}
		return y == result ? true : false;
	}

	static void perm(int cnt) { // 모든 경우의 수를 찾아야 하므로 순열로 해결
		if (cnt == limit) { // 순열이 완성되었다면
			for (int i = 1; i <= N; i++) { // 출발지와 목표가 같는지 확인
				if (!ladder(i))
					return; // 정답 안되면 리턴
			}

			flag = true; // 정답일 경우 flag true
			answer = limit; // 사다리 갯수
			return;
		}
		for (int h = 1; h <= H; h++) {
			for (int n = 1; n < N; n++) {
				if (arr[h][n] != 0 || arr[h][n + 1] != 0) { // 만약에 연결 안된 사다리일 경우
					continue;
				}
				// 바꿔주고 다시 해제
				arr[h][n] = n + 1;
				arr[h][n + 1] = n;
				perm(cnt + 1);
				arr[h][n] = 0;
				arr[h][n + 1] = 0;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/0216/Main_bj_15684_사다리_조작_서울_20반_심재운.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H + 1][N + 1]; // 1, 1 부터 시작하므로

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			arr[x][y] = y + 1; // 사다리 연결 표현
			arr[x][y + 1] = y;
		}

		limit = 0; // 사다리 연결 수
		while (limit <= 3 && !flag) { // 사다리가 3개 이하고 아직 연결 안되었을 경우 진행
			perm(0);
			limit++;
		}
		System.out.println(flag == false ? -1 : answer);
		br.close();
	}
}