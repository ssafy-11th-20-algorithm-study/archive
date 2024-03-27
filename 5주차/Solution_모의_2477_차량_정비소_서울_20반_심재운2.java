package a0315;

import java.io.*;
import java.util.*;

public class Solution_모의_2477_차량_정비소_서울_20반_심재운2 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/0315/Solution_모의_2477_차량_정비소_서울_20반_심재운.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			int[][] mainCounter = new int[N][2]; // 접수대 [0]: 끝나는 시간 [1]: 처리 시간
			int[][] fixCounter = new int[M][2]; // 정비소 [0]: 끝나는 시간 [1]: 처리 시간
			int[][] waiting = new int[K][3]; // 고객 상태 [0]: 접수대에서 접수 마친 시간 [1]: 이용 접수대 번호 [2]: 고객 번호

			st = new StringTokenizer(br.readLine());

			for (int n = 0; n < N; n++) {
				mainCounter[n][0] = 0;
				mainCounter[n][1] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());

			for (int m = 0; m < M; m++) {
				fixCounter[m][0] = 0;
				fixCounter[m][1] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());

			// 어차피 순서대로 들어오니깐 처리
			for (int k = 0; k < K; k++) {
				waiting[k][2] = k + 1; // 고객 번호
				int cus = Integer.parseInt(st.nextToken());
				int minV = Integer.MAX_VALUE; // 최소 접수대를 찾기 위한 변수
				int j = -1;

				for (int n = 0; n < N; n++) {
					if (mainCounter[n][0] <= cus) { // 이용할 수 있는 접수대가 있을 경우 이용
						j = n;
						break;
					} else if (mainCounter[n][0] < minV) { // 이용할 수 있는 접수대가 없을 경우 제일 빨리 끝나는 접수대 찾기
						minV = mainCounter[n][0];
						j = n;
					}
				}

				mainCounter[j][0] = waiting[k][0] = mainCounter[j][0] <= cus ? cus + mainCounter[j][1]
						: mainCounter[j][0] + mainCounter[j][1]; // !! 이것 때문에 3시간 고민했음..
				waiting[k][1] = j + 1; // 이용 접수대 입력
			}

			Arrays.sort(waiting, (x, y) -> x[0] != y[0] ? x[0] - y[0] : x[1] - y[1]); // 고객이 먼저 도착한 시간, 이용한 접수대 순으로 정렬
			// for (int[] a: waiting) System.out.println(Arrays.toString(a));
			int answer = 0;

			for (int k = 0; k < K; k++) {
				int cus = waiting[k][0];
				int minV = Integer.MAX_VALUE;
				int j = -1;

				for (int m = 0; m < M; m++) {
					if (fixCounter[m][0] <= cus) {
						j = m;
						break;
					} else if (fixCounter[m][0] < minV) {
						minV = fixCounter[m][0];
						j = m;
					}
				}

				fixCounter[j][0] = fixCounter[j][0] <= cus ? cus + fixCounter[j][1]
						: fixCounter[j][0] + fixCounter[j][1];
				if (waiting[k][1] == A && j + 1 == B) {
					answer += waiting[k][2];
				}
			}
			sb.append("#").append(tc).append(" ").append(answer == 0 ? -1 : answer).append("\n");
		}
		System.out.println(sb);
	}
}