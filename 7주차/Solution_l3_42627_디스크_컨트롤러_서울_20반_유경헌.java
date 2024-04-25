package com.algorithm.study.eclipse;

import java.util.*;

class Solution_l3_42627_디스크_컨트롤러_서울_20반_유경헌 {
	public int solution(int[][] jobs) {
		PriorityQueue<int[]> inPq = new PriorityQueue<>(
				(o1, o2) -> o1[0] == o2[0] ? Integer.compare(o1[1], o2[1]) : Integer.compare(o1[0], o2[0]));
		PriorityQueue<int[]> runPq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		for (int i = 0; i < jobs.length; i++) {
			inPq.offer(new int[] { jobs[i][0], jobs[i][1] });
		}
		int answer = 0;
		int cnt = 0;
		int wait = 0;
		while (cnt < jobs.length) {
			while (!inPq.isEmpty() && inPq.peek()[0] <= wait) {
				runPq.offer(inPq.poll());
			}
			if (!runPq.isEmpty()) {
				int cur[] = runPq.poll();
				answer += cur[1] + wait - cur[0];
				wait += cur[1];
				cnt++;
			} else {
				int cur[] = inPq.poll();
				answer += cur[1] + wait - cur[0];
				wait += cur[1];
				cnt++;
			}
		}
		return answer / jobs.length;
	}
}