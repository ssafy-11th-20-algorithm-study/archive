package a0226;

import java.io.*;
import java.util.*;

public class Solution_모의_2383_점심_식사시간_서울_20반_심재운 {

	static int N, answer;
	static int[][] arr;
	// 사람들 위치, 문 위치, 움직이는 사람, 계단1 상태, 계단2 상태
	static ArrayList<Integer[]> people, doors, moving, stair1, stair2;
	static ArrayList<Integer> stairD; // 계단 길이
	static int[] combList; // 중복 조합 저장 배열 -> 가야하는 계단 방향 저장

	// 계단 입구와 사람간의 거리 계산
	static int getDistance(int person, int door) { // 사람의 인덱스, 계단 입구의 인덱스
		int nx = Math.abs(people.get(person)[0] - doors.get(door)[0]);
		int ny = Math.abs(people.get(person)[1] - doors.get(door)[1]);
		return nx + ny;
	}

	static void oper() { // 계산 메소드
		int minTime = 0; // 최소시간
		int complete = 0; // 계단 이동 완료 사람 수
		int[] dc = new int[] { 0, 0 }; // 각 계단에 올라가 있는 사람 수

		while (true) {
			if (complete == people.size()) { // 사람이 전부 들어온다면 종료
				answer = Math.min(answer, minTime);
				return;
			}

			// 이동 관리
			for (int m = 0; m < moving.size(); m++) {
				if (moving.get(m)[1] > 0) { // 이동할 거리가 있을 경우
					moving.get(m)[1] -= 1; // 이동
				} else {
					// 도착 했을 때, 0일 때
					if (moving.get(m)[3] == 1) { // 도착했지만 아직 기다리지 않았을 경우
						moving.get(m)[3] = 0; // 기다림
						continue;
					}
					if (dc[moving.get(m)[0] - 1] < 3 && moving.get(m)[2] == 1) { // 아직 계단을 이용하지 않았고 계단을 이용할 수 있다면
						dc[moving.get(m)[0] - 1] += 1; // 계단에 사람 한명 추가
						moving.get(m)[2] = 0; // 계단 이용 표시
						if (moving.get(m)[0] == 1) { // 1번 계단일경우
							stair1.add(new Integer[] { stairD.get(0), 1 }); // 1번 계단에 사람 추가, 계단 높이, 계단 완료 여부
						} else { // 2번 계단일 경우
							stair2.add(new Integer[] { stairD.get(1), 1 }); // 2번 계단에 사람 추가
						}
					}
				}
			}

			for (int d1 = 0; d1 < stair1.size(); d1++) { // 계단을 탐색하면서
				if (stair1.get(d1)[0] > 0) { // 아직 계단을 내려가고 있다면 
					stair1.get(d1)[0] -= 1; // 계단 이동
				}

				if (stair1.get(d1)[1] == 1 && stair1.get(d1)[0] == 0) { // 만약에 계단을 내려가는 중이었고 도착했다면 (이미 다 내려가 있는 사람 따로 제거 안하므로)
					stair1.get(d1)[1] = 0; // 계단 도착 완료
					dc[0] -= 1; // 계단에 있는 사람 한명 제거
					complete++; // 완료된 사람 추가
				}
			}
			for (int d2 = 0; d2 < stair2.size(); d2++) {
				if (stair2.get(d2)[0] > 0) {
					stair2.get(d2)[0] -= 1;
				}
				if (stair2.get(d2)[1] == 1 && stair2.get(d2)[0] == 0) {
					stair2.get(d2)[1] = 0;
					dc[1] -= 1;
					complete++;
				}
			}
			minTime++; // 1초 추가
		}

	}

	static void comb(int cnt) { // 각 계단을 택했을 때 나올 수 있는 순열
		// 중복 조합이 끝났을 경우
		if (cnt == people.size()) {
			moving = new ArrayList<>();
			stair1 = new ArrayList<>();
			stair2 = new ArrayList<>();
			// 사람들이 각자의 계단까지 걸리는 시간을 저장하는 리스트
			for (int p = 0; p < people.size(); p++) {
				if (combList[p] == 1) { // 1번 문일 경우
					moving.add(new Integer[] { 1, getDistance(p, 0), 1, 1 }); // 1번 문, 거리, 움직이는 중, ready
				} else {
					moving.add(new Integer[] { 2, getDistance(p, 1), 1, 1 });
				}
			}

			oper(); // 계산

			return;
		}

		combList[cnt] = 0;
		comb(cnt + 1);
		combList[cnt] = 1;
		comb(cnt + 1);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/0226/Solution_모의_점심_식사시간_서울_20반_심재운.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			arr = new int[N][N];
			people = new ArrayList<>();
			doors = new ArrayList<>();
			stairD = new ArrayList<>();

			for (int x = 0; x < N; x++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int y = 0; y < N; y++) {
					arr[x][y] = Integer.parseInt(st.nextToken());
					if (arr[x][y] == 1) {
						people.add(new Integer[] { x, y }); // 사람 좌표 입력
					}
					if (arr[x][y] >= 2) {
						doors.add(new Integer[] { x, y }); // 계단 좌표 입력
						stairD.add(arr[x][y]); // 계단 높이 입력
					}
				}
			}

			answer = Integer.MAX_VALUE;
			combList = new int[people.size()];
			comb(0);
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}