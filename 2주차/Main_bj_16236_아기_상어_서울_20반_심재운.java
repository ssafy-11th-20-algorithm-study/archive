package a0216;

import java.io.*;
import java.util.*;

public class Main_bj_16236_아기_상어_서울_20반_심재운 {

	static int N; // 맵 크기 N x N
	static int[][] arr; // 맵
	static int[] curr = new int[2]; // 현재 아기 상어 위치 x, y
	static int answer = 0; // 정답
	static final int[] dx = { -1, 0, 1, 0 }; // 방향 x축
	static final int[] dy = { 0, -1, 0, 1 }; // 방향 y축
	static int s = 2; // 아기상어 크기
	static int accu = 0; // 먹은 먹이 수

	static boolean bfs() { // 목표 찾기
		ArrayDeque<Integer[]> queue = new ArrayDeque<>(); // bfs 큐
		queue.offer(new Integer[] { curr[0], curr[1], 0 }); // 현재 아기 상어 위치, 이동한 거리
		boolean[][] v = new boolean[N][N]; // 매번 새롭게 맵을 탐색하므로 방문 배열 초기화
		v[curr[0]][curr[1]] = true; // 현재 위치는 방문 처리
		arr[curr[0]][curr[1]] = 0; // 이동하므로 현재 있었던 위치 0으로 처리
		List<Integer[]> sub = new ArrayList<>(); // 목적지 후보지를 저장하는 리스트

		while (!queue.isEmpty()) {
			Integer[] xy = queue.poll();
			int cx = xy[0];
			int cy = xy[1];
			int cc = xy[2];

			for (int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];

				if (0 <= nx && nx < N && 0 <= ny && ny < N && !v[nx][ny] && arr[nx][ny] <= s) { // 이동할 수 있는 경우
					if (arr[nx][ny] < s && arr[nx][ny] != 0) { // 먹을 수 있는 먹이일 경우
						sub.add(new Integer[] {nx, ny, cc + 1}); // 후보지에 포함
					}
					v[nx][ny] = true; // 방문처리
					queue.offer(new Integer[] { nx, ny, cc + 1 }); // 다음 방문 장소 저장
				}
			}
		}
		
		if (sub.isEmpty()) return false; // 먹을 수 있는 먹이가 더이상 없을 경우 종료.
		// 후보지 중에서 가장 위에 있고 가장 왼쪽에 있는 물고기 찾기 위해 정렬
		Collections.sort(sub, (x, y) -> x[2] != y[2] ? x[2] - y[2] : x[0] != y[0] ? x[0] - y[0] : x[1] - y[1]);
		accu++; // 물고기 먹었으므로 누적 물고기 ++
		// 목적지로 아기 상어의 위치 이동
		curr[0] = sub.get(0)[0];
		curr[1] = sub.get(0)[1];
		// 이동 거리 합산
		answer += sub.get(0)[2];
		
		// 아기상어의 현재 위치 표시
		arr[curr[0]][curr[1]] = 9;
		
		// true 리턴
		return true;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/0213/Main_bj_16236_아기_상어_서울_20반_심재운.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];

		StringTokenizer st;

		for (int x = 0; x < N; x++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int y = 0; y < N; y++) {
				arr[x][y] = Integer.parseInt(st.nextToken());
				if (arr[x][y] == 9) { // 아기 상어 현재 위치 저장
					curr[0] = x;
					curr[1] = y;
				}
			}
		}
		
		boolean flag;
		
		while (true) { // 아기 상어가 더 이상 먹이를 찾을 수 없을 때까지 반복
			flag = bfs();
			
			if (!flag) break; // 더 이상 먹을 수 있는 먹이가 없을 경우 종료
			
			if (accu == s) { // 만약에 먹이가 누적되어서 크기가 증가할 수 있다면 크기 증가 누적 먹이 초기화
				s++;
				accu = 0;
			}
		}
		System.out.println(answer);
		br.close();
	}

}
