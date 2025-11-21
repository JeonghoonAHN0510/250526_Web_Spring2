package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
    static boolean[] visited;
    static int[] visiting;
    static int count = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 정점의 수 N
        int M = Integer.parseInt(st.nextToken());       // 간선의 수 M
        int R = Integer.parseInt(st.nextToken());       // 시작 정점 R
        visited = new boolean[N+1];
        visiting = new int[N+1];

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(u, f -> new PriorityQueue<>()).add(v);
            map.computeIfAbsent(v, f -> new PriorityQueue<>()).add(u);
        } // for end

        bfs(R);

        for (int i = 1; i <= N; i++){
            answer.append(visiting[i]).append("\n");
        } // for end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    public static void bfs(int R){
        Queue<Integer> q = new LinkedList<>();
        q.offer(R);
        visited[R] = true;
        while (!q.isEmpty()){
            int index = q.poll();
            visiting[index] = count++;
            PriorityQueue<Integer> pq = map.get(index);
            while (!pq.isEmpty()){
                int u = pq.poll();
                if (!visited[u]){
                    visited[u] = true;
                    q.offer(u);
                } // if end
            } // while end
        } // while end
    } // func end
} // class end