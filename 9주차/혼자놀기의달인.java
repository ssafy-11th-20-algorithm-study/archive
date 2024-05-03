package a0430;

import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        int len = cards.length;
        boolean[] v;
        int start = 0;
        int first = 0;
        
        for (int i = 0; i < len; i ++) {
            v = new boolean[len];
            start = i;
            first = 0;
            while (!v[start]) {
                first++;
                v[start] = true;
                start = cards[start] - 1;
            }
            int second = 0;
            for (int k = 0; k < len; k ++) {
                if (!v[k]) {
                    int ss = 0;
                    boolean[] temp = new boolean[len];
                    for (int j = 0; j < len; j ++) {
                        temp[j] = v[j];
                    }
                    start = k;
                    while (!temp[start]) {
                        ss++;
                        temp[start] = true;
                        start = cards[start] - 1;
                    }
                    second = Math.max(second, ss);
                }
            }
            answer = Math.max(answer, second * first);
        }
        
        return answer;
    }
}