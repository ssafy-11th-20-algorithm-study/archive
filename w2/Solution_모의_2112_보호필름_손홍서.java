import java.util.*;
import java.io.*;

/**
 * <문제>
 * 두께(행) D, 가로(열) W인 보호 필름이 있다.
 * 해당 보호 필름 성늠 검사를 하려고 한다.
 * 보호필름 각 셀에는 A, B 특성이 있다.
 * 모든 세로방향으로 탐색을 했을때 동일 특성의 셀들이 K개 이상 연속적으로 있는 경우에만 성능 검사 통과가 가능하다.
 * 성능검사를 통과하기 위해서 특정 행에 약품을 투입할 수 있다.
 * A약품을 투입하면, 해당 셀 특성이 모두 A로 변경되고 B약품을 투입하면, 해당 셀 특성이 모두 B로 변경된다.
 * 문제는 성능검사를 통과히기 위한 최소 약품 투입 횟수를 구하는 것이다!

 * 1. 성능검사
 * 2-1. 성능 검사 통과하면, 최솟값 검증
 * 2-2. 성능 검사 통과 못하면, 약품 투입
 */
public class S2112 {
    static int ans;
    static int[][] map;
    static int D, W, K;
    public static void main(String[] args) throws Exception {
        //0. 입력
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[D][W];
            for(int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            /**
             * 같은 특성의 약품을 연속으로 K개 투입시키면 무조건 성능검사 통과
             * K를 ans에 대입시킨 후 이 값보다 약품 투입 횟수가 크면 탐색을 종료하게 함
             */
            ans = K;
            solution(0, 0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
    static void solution(int cnt, int curr) {
        //현재까지의 최솟값보다 약품주입 횟수가 크거나 같으면 어차피 답 아니니깐 탐색 종료
        if(curr >= ans) {
            return;
        }

        //2-1. 성능 검사 통과하면, 최솟값 검증
        if(isValid()) {
            ans = curr; // 위에서 ans보다 작은 것 검증했으므로 무조건 대입해버림
            return;
        }

        //종료조건
        if(cnt == D) {
            return;
        }

        //2-2. 성능 검사 통과 못하면, 약품 투입
        int[] temp = Arrays.copyOf(map[cnt], W);
        Arrays.fill(map[cnt], 0);
        solution(cnt + 1, curr + 1);
        Arrays.fill(map[cnt], 1);
        solution(cnt + 1, curr + 1);
        map[cnt] = Arrays.copyOf(temp, W);
        solution(cnt + 1, curr);
    }


    static boolean isValid() {
        boolean flag;
        int old, cnt;
        for(int i = 0; i < W; i++) {
            flag = false;
            old = map[0][i];
            cnt = 1;
            for(int j = 1; j < D; j++) {
                //동일 특성이 K개 연속되어있는지 확인
                if(old == map[j][i]) {
                    cnt++;
                } else {
                    cnt = 1;
                    old = map[j][i];
                }
                //만약 K개를 충족하면 해당 열은 더이상 탐색할 필요없으므로 중단
                if(cnt == K) {
                    flag = true;
                    break;
                }
            }
            //만약 한 열을 탐색하면서 flag가 변경되지 않으면 성능검사 통과 못한것이므로 false 리턴
            if(!flag) {
                return false;
            }
        }
        return true;
    }
}
