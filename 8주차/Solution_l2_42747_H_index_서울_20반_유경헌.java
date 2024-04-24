package com.algorithm.study.eclipse;

import java.util.*;

class Solution_l2_42747_H_index_서울_20반_유경헌 {
	public int solution(int[] citations) {
		Arrays.sort(citations);
		int ans = 0;
		for (int i = citations.length - 1; i >= 0; i--) {
			ans = Math.max(ans, Math.min(citations.length - i, citations[i]));
		}
		return ans;
	}
}