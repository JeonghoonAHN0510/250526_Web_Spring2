package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static int[] files;
    static int[][] dp;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());        // 테스트케이스 T
        for (int i = 0; i < T; i++){
            K = Integer.parseInt(br.readLine());    // 소설 장의 수 K
            files = new int[K+1];
            dp = new int[K+1][K+1];
            st = new StringTokenizer(br.readLine());
            files[1] = Integer.parseInt(st.nextToken());
            for (int j = 2; j <= K; j++){
                files[j] = Integer.parseInt(st.nextToken()) + files[j - 1];
            } // for end

            answer.append(getMinFileSize()).append("\n");
        } // for end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    public static int getMinFileSize(){
        for (int gap = 1; gap < K; gap++){
            for (int start = 1; start + gap <= K; start++){
                int end = start + gap;
                dp[start][end] = Integer.MAX_VALUE;
                for (int mid = start; mid < end; mid++){
                    dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid + 1][end] + files[end] - files[start - 1]);
                } // for end
            } // for end
        } // for end
        return dp[1][K];
    } // func end
} // class end