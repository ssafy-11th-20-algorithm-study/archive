package a0416;

import java.util.*;

public class 디스크_컨트롤러 {
    public int solution(int[][] jobs) {
        int finish = 0;
        int answer = 0;
        
        // 정렬 중요! 도착한 시간 순서대로 정렬하고 짧은 시간 순서대로
        Arrays.sort(jobs, (x, y) -> x[0] != y[0] ? x[0] - y[0] : x[1] - y[1]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        int i = 0;
        
        while (i < jobs.length) {
            if (pq.isEmpty() && jobs[i][0] >= finish) {
                finish = jobs[i][1] + jobs[i][0];
                answer += jobs[i][1];
                i++;
                continue;
            }
            
            if (jobs[i][0] <= finish) {
                pq.offer(new int[]{jobs[i][0], jobs[i][1]});
                i++;
                continue;
            }
            
            if (!pq.isEmpty()) {
                int[] temp = pq.poll();
                answer += (finish - temp[0]) + temp[1];
                finish += temp[1];
            }
        }
        
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            answer += (finish - temp[0]) + temp[1];
            finish += temp[1];
        }
        
        return answer / jobs.length;
    }
}