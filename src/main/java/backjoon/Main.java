package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static int N, K;
    static int[][] array;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 물품의 수 N
        K = Integer.parseInt(st.nextToken());   // 최대 무게 K
        array = new int[N][2];
        result = new int[K + 1];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            array[i][0] = Integer.parseInt(st.nextToken());
            array[i][1] = Integer.parseInt(st.nextToken());
        } // for end

        for (int i = 0; i < N; i++) {
            int weight = array[i][0];       // i번째 무게
            int value = array[i][1];        // i번째 가치
            for (int j = K; j >= weight; j--) {
                // j번째 가치 VS i번쨰 가치 + j-i 무게의 가치
                result[j] = Math.max(result[j], result[j - weight] + value);
            } // for end
        } // for end

        answer.append(result[K]);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end