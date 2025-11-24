package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
    static boolean[] visited;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());    // 컴퓨터 개수 N
        int M = Integer.parseInt(br.readLine());    // 연결 개수 M
        visited = new boolean[N + 1];
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map.computeIfAbsent(x, k -> new PriorityQueue<>()).add(y);
            map.computeIfAbsent(y, k -> new PriorityQueue<>()).add(x);
        } // for end

        bfs(1);

        answer.append(count);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    public static void bfs(int node){
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        visited[node] = true;
        while (!q.isEmpty()){
            int index = q.poll();
            PriorityQueue<Integer> pq = map.getOrDefault(index, new PriorityQueue<>());
            while (!pq.isEmpty()){
                int u = pq.poll();
                if (!visited[u]){
                    count++;
                    visited[u] = true;
                    q.offer(u);
                } // if end
            } // while end
        } // while end
    } // func end
} // class end