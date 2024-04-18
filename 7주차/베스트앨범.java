package a0416;

import java.util.*;

public class 베스트앨범 {
    public int[] solution(String[] genres, int[] plays) {
        
        int[][] idxPlays = new int[100][2]; // 장르별 총 합 재생시간 0: idx, 1 : 재생시간
        
        HashMap<String, Integer> hm = new HashMap<>(); // 장르 인덱스 저장 해쉬맵
        ArrayList<ArrayList<Integer[]>> gp = new ArrayList<>(); // 각 장르별 음악 정보
        int cnt = 0;
        
        for (int i = 0 ; i < genres.length; i ++) { // 장르 노래 반복하면서
            if (!hm.containsKey(genres[i])) { // 만약 장르가 없다면
                hm.put(genres[i], cnt); // 장르 index 추가
                gp.add(new ArrayList<Integer[]>()); // 새롭게 인덱스 추가
                idxPlays[hm.get(genres[i])][0] = cnt;
                cnt++;
                
            }
            gp.get(hm.get(genres[i])).add(new Integer[]{i, plays[i]}); // 추가
            idxPlays[hm.get(genres[i])][1] += plays[i];
        }
        
        Arrays.sort(idxPlays, (x, y) -> y[1] - x[1]);
        
        for (int i = 0; i < gp.size(); i ++) {
            Collections.sort(gp.get(i), (x, y) -> y[1] - x[1]);
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < cnt; i ++) {
            for (int j = 0; j < Math.min(2, gp.get(idxPlays[i][0]).size()); j ++) {
                result.add(gp.get(idxPlays[i][0]).get(j)[0]);
            }
        }
        
        int[] answer = new int[result.size()];
        
        for (int i = 0; i < result.size(); i ++) {
            answer[i] = result.get(i);
        }
        
        
        return answer;
    }
}