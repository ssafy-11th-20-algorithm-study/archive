package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_모의_2477_차량정비소_손홍서 {
    static int N, M, K, A, B;
    static Desk[] receptionDesks;
    static Desk[] repairDesks;
    static int ans = 0;
    static class Desk {
        int servingTime;
        int endTime;

        public Desk(int servingTime, int endTime) {
            this.servingTime = servingTime;
            this.endTime = endTime;
        }
    }

    static class Customer implements Comparable<Customer>{
        int num;
        int index;
        int arrivalTime;

        public Customer(int num, int index, int arrivalTime) {
            this.num = num;
            this.index = index;
            this.arrivalTime = arrivalTime;
        }

        @Override
        public int compareTo(Customer c) {
            if(c.arrivalTime < arrivalTime) {
                return 1;
            } else if(c.arrivalTime == arrivalTime) {
                if(c.index < index) {
                    return 1;
                }
            }
            return -1;
        }
    }

    static PriorityQueue<Customer> a;

    public static void main(String[] args) throws Exception{
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            receptionDesks = new Desk[N];
            repairDesks = new Desk[M];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                receptionDesks[i] = new Desk(Integer.parseInt(st.nextToken()), 0);
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
                repairDesks[i] = new Desk(Integer.parseInt(st.nextToken()), 0);
            }


            st = new StringTokenizer(br.readLine());
            a = new PriorityQueue<>();
            for(int i = 0; i < K; i++) {
                a.offer(new Customer(i + 1, i, Integer.parseInt(st.nextToken())));
            }

            ans = 0;
            solution();
            if(ans == 0) {
                sb.append("-1").append("\n");
            } else {
                sb.append(ans).append("\n");
            }
        }
        System.out.println(sb);
    }
    static void solution() {
        PriorityQueue<Customer> b = new PriorityQueue<>();

        int time = a.peek().arrivalTime;

        //접수
        while(!a.isEmpty()) {
            //고객 대기 큐에서 꺼내시
            while(a.peek().arrivalTime > time) {
                time++;
            }

            Customer now = a.poll();
            boolean flag = false;
            //데스크 비어있으면 서브하기
            for(int i = 0; i < N; i++) {
                if(receptionDesks[i].endTime <= time) {
                    b.offer(new Customer(now.num, i, time + receptionDesks[i].servingTime));
                    receptionDesks[i].endTime = time + receptionDesks[i].servingTime;
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                a.offer(now);
                time++;
            }
        }

        time = b.peek().arrivalTime;
        //고치기
        while(!b.isEmpty()) {
            //고객 대기 큐에서 꺼내시
            while(b.peek().arrivalTime > time) {
                time++;
            }

            Customer now = b.poll();
            boolean flag = false;
            //데스크 비어있으면 서브하기
            for(int i = 0; i < M; i++) {
                if(repairDesks[i].endTime <= time) {
                    if(now.index + 1 == A && i + 1 == B) {
                        ans += now.num;
                    }
                    repairDesks[i].endTime = time + repairDesks[i].servingTime;
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                b.offer(now);
                time++;
            }
        }
    }
}