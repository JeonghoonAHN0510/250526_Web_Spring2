package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static int N;
    static int[][] array;
    static Integer[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());    // 전깃줄 개수 N
        array = new int[N][2];
        result = new Integer[N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            array[i][0] = Integer.parseInt(st.nextToken());
            array[i][1] = Integer.parseInt(st.nextToken());
        } // for end
        Arrays.sort(array, (o1, o2) -> o1[0] - o2[0]);

        int max = 0;

        for (int i = 0; i < N; i++){
            max = Math.max(max, dp(i));
        } // for end

        answer.append(N - max);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    public static int dp(int index){                    // 설치 가능한 최대 전봇대 구하기
        if (result[index] == null){
            result[index] = 1;                          // 1로 초기화
            for (int i = index + 1; i < N; i++){        // index보다 뒤 전봇대를 순회하면서
                if (array[index][1] < array[i][1]){     // 전깃줄이 겹치지않는다면
                    // 연결할 수 있는 전깃줄 경우의 수 중에서 큰 값을 저장
                    result[index] = Math.max(result[index], dp(i) + 1);
                } // if end
            } // for end
        } // if end
        return result[index];
    } // func end
} // class end