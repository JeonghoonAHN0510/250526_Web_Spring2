package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 정점 N개
        int M = Integer.parseInt(st.nextToken());   // 간선 M개
        int R = Integer.parseInt(st.nextToken());   // 정점 R

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end