package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    static Map<Integer, Set<Integer>> DfsMap = new HashMap<>();
    static Map<Integer, PriorityQueue<Integer>> BfsMap = new HashMap<>();
    static boolean[] DfsVisited;
    static boolean[] BfsVisited;
    static int[] DfsVisiting;
    static int[] BfsVisiting;
    static int DfsCount = 1;
    static int BfsCount = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 정점 개수 N
        int M = Integer.parseInt(st.nextToken());   // 간선 개수 M
        int V = Integer.parseInt(st.nextToken());   // 탐색 시작번호 V
        DfsVisited = new boolean[N + 1];
        BfsVisited = new boolean[N + 1];
        DfsVisiting = new int[N + 1];
        BfsVisiting = new int[N + 1];
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // DFS
            DfsMap.computeIfAbsent(x, k -> new TreeSet<>()).add(y);
            DfsMap.computeIfAbsent(y, k -> new TreeSet<>()).add(x);

            // BFS
            BfsMap.computeIfAbsent(x, k -> new PriorityQueue<>()).add(y);
            BfsMap.computeIfAbsent(y, k -> new PriorityQueue<>()).add(x);
        } // for end

        dfs(V);
        for (int i = 1; i <= N; i++){
            if (DfsVisiting[i] == 0) break;
            answer.append(DfsVisiting[i]).append(" ");
        } // for end

        answer.append("\n");

        bfs(V);
        for (int i = 1; i <= N; i++){
            if (BfsVisiting[i] == 0) break;
            answer.append(BfsVisiting[i]).append(" ");
        } // for end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    public static void dfs(int node){
        if (!DfsVisited[node]) {
            DfsVisited[node] = true;
            DfsVisiting[DfsCount++] = node;
            Set<Integer> set = DfsMap.getOrDefault(node, new TreeSet<>());
            for (int i : set){
                if (!DfsVisited[i]){
                    dfs(i);
                } // if end
            } // for end
        } // if end
    } // func end
    public static void bfs(int node){
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        BfsVisited[node] = true;
        while (!q.isEmpty()){
            int index = q.poll();
            BfsVisiting[BfsCount++] = index;
            PriorityQueue<Integer> pq = BfsMap.getOrDefault(index, new PriorityQueue<>());
            while (!pq.isEmpty()){
                int u = pq.poll();
                if (!BfsVisited[u]){
                    BfsVisited[u] = true;
                    q.offer(u);
                } // if end
            } // while end
        } // while end
    } // func end
} // class end