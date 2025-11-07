package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static int N, M, R;
    static Map<Integer, Set<Integer>> map = new HashMap<>();
    static boolean[] visited;
    static int[] visitOrder;        // 각 노드의 방문 순서를 저장할 배열
    static int count;               // 방문 순서를 셀 카운트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 정점 N개
        M = Integer.parseInt(st.nextToken());   // 간선 M개
        R = Integer.parseInt(st.nextToken());   // 정점 R
        visited = new boolean[N + 1];
        visitOrder = new int[N + 1];
        count = 1;

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());   // 정점 u
            int v = Integer.parseInt(st.nextToken());   // 정점 v
            if (map.containsKey(u)){
                map.get(u).add(v);
            } else {
                Set<Integer> list = new TreeSet<>();
                list.add(v);
                map.put(u, list);
            } // if end
            if (map.containsKey(v)){
                map.get(v).add(u);
            } else {
                Set<Integer> list = new TreeSet<>();
                list.add(u);
                map.put(v, list);
            } // if end
        } // for end

        dfs(R);

        for (int i = 1; i <= N; i++){
            answer.append(visitOrder[i]).append("\n");
        } // for end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    public static void dfs(int node){
        visited[node] = true;
        visitOrder[node] = count++;

        if (map.containsKey(node)){
            for (Integer key : map.get(node)){
                if (!visited[key]) dfs(key);
            } // for end
        } // if end
    } // func end
} // class end