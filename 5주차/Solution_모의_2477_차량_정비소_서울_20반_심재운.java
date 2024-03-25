package a0315;

import java.io.*;
import java.util.*;

public class Solution_모의_2477_차량_정비소_서울_20반_심재운 {
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
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			int[] ai = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				ai[i] = Integer.parseInt(st.nextToken());
			}
			int[] bi = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				bi[i] = Integer.parseInt(st.nextToken());
			}
			int[][] tk = new int[K][3];// 도착시간, 고객번호,이용 창구
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				tk[i][0] = Integer.parseInt(st.nextToken());
				tk[i][1] = i + 1;
			} // 입력 완료
			int ans = 0;
			int[] time = new int[N];// 대기시간
			for (int i = 0; i < K; i++) { // 고객 입장 시작 , 이거 들어오는 거 고객 번호순이니 막 하면 됨
				int mi = 0;
				for (int n = 0; n < N; n++) { // 접수 창구 돌면서
					// 우선순위 체크
					if (time[n] <= tk[i][0]) { // 들어갈 수 있는 곳이면 들어가고 브레이크
						mi = n;
						break;
					} else if (time[n] < time[mi]) { // 들어갈 수 없으면 최소 시간 창구 찾기
						mi = n;
					}
				}
				tk[i][2] = mi; // 최소 창구 찾아서 입력
				time[mi] = time[mi] < tk[i][0] ? tk[i][0] + ai[mi] : time[mi] + ai[mi]; // 고객 끝나는 시간 계산
				tk[i][0] = time[mi]; // 고객 도착 시간 입력
			} // 접수 끝
			Arrays.sort(tk, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[2] - b[2])); // 조건에 따른 정렬
			time = new int[M]; // 같이 우선순위 값 찾기
			for (int i = 0; i < K; i++) {
				int mi = 0;
				for (int n = 0; n < M; n++) {
					if (time[n] <= tk[i][0]) {
						mi = n;
						break;
					} else if (time[n] < time[mi]) {
						mi = n;
					}
				}
				time[mi] = time[mi] < tk[i][0] ? tk[i][0] + bi[mi] : time[mi] + bi[mi];
				if (mi == B && tk[i][2] == A) { // 조건에 맞으면 사람 번호 더하기
					ans += tk[i][1];
				}
			}
			sb.append('#').append(tc).append(' ').append(ans == 0 ? -1 : ans).append('\n');
		}
		System.out.println(sb);
	}
}