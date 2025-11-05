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
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());   // 정점 u
            int v = Integer.parseInt(st.nextToken());   // 정점 v
            if (map.containsKey(u)){
                map.get(u).add(v);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(v);
                map.put(u, list);
            } // if end
            if (map.containsKey(v)){
                map.get(v).add(u);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(u);
                map.put(v, list);
            } // if end
        } // for end

        boolean[] visited = new boolean[N];
        visited[R] = true;

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end