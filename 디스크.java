import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Job> wait = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);
        PriorityQueue<Job> ready = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        int waitTime = 0;
        int costTime = 0;
        int idle = 0;
        int count = 0;
        
        for (int i = 0; i < jobs.length; i++) {
            wait.add(new Job(jobs[i][0], jobs[i][1]));
        }
        
        while (count < jobs.length) {
            while (!wait.isEmpty() && wait.peek().start <= costTime) {
                ready.offer(wait.poll());
            }
            
            if (ready.isEmpty()) {
                idle += wait.peek().start - costTime;
                costTime = wait.peek().start;
            }
            else {
                Job job = ready.poll();
                waitTime += costTime - job.start;
                costTime += job.cost;
                System.out.println(job.start + " " + job.cost);
                count++;
            }
        }
        
        return (waitTime + costTime - idle) / jobs.length;
    }
    
    class Job {
        int start;
        int cost;
        
        public Job(int start, int cost) {
            this.start = start;
            this.cost = cost;
        }
    }
}
