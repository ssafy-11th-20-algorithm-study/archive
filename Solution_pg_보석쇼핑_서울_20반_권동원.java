import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String[] gems) {
        int kind = new HashSet<>(Arrays.asList(gems)).size(); // 종류 갯수
        int[] answer = new int[2]; // 정답 배열
        int length = Integer.MAX_VALUE; // 최소 구간 길이 MAX_VALUE로 초기화
        int start = 0;
        
        Map<String, Integer> map = new HashMap<>();
        
        for(int end=0; end<gems.length; end++){
            map.put(gems[end], map.getOrDefault(gems[end],0) + 1); // map에 있으면 +1, 없으면 0으로 초기화
            
            while(map.get(gems[start]) > 1){ // start번째의 보석이 0이 될때까지
                map.put(gems[start], map.get(gems[start])-1); // start번째의 보석 -1 해줌
                start++; // start 증가
            }
            
            if(map.size() == kind && length > (end - start)){ // map의 크기가 종류 갯수이고(종류 갯수 만큼 저장 되어있고), 그 전 구간길이 보다 작으면
                length = end - start; // 구간길이 저장
                answer[0] = start + 1; // 구간 저장
                answer[1] = end + 1;
            }
        }
        return answer;
    }
}
