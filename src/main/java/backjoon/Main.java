package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static Map<Integer, Integer> answerMap = new TreeMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());    // 전깃줄 개수 N
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            answerMap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        } // for end
        System.out.println("answerMap = " + answerMap);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end