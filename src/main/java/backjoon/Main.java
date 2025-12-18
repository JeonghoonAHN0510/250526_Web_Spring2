package backjoon;

import java.io.*;
import java.util.*;

class Node{
    int node;
    int cost;

    public Node(int node, int cost){
        this.node = node;
        this.cost = cost;
    } // func end
} // class end

public class Main {
    static StringBuilder answer = new StringBuilder();
    static List<Node>[] adjList;
    static boolean[] visited;
    static Map<Integer, int[]> costMap = new HashMap<>();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 방향이 없는 그래프이며
        // 임의로 주어진 두 정점은 반드시 통과하며
        // 1번 정점에서 N번 정점으로 이동해야한다.
        // 한번 이동했던 정점과 간선을 이용할 수 있다.
        // 1번 정점에서 N번 정점까지의 최단 경로의 길이를 출력하라. 그러한 경로가 없다면, -1 출력

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 정점의 개수 N
        int E = Integer.parseInt(st.nextToken());   // 간선의 개수 E
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++){
            adjList[i] = new ArrayList<>();
        } // for end

        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());       // 정점 a
            int b = Integer.parseInt(st.nextToken());       // 정점 b
            int cost = Integer.parseInt(st.nextToken());    // 거리 c

            adjList[a].add(new Node(b, cost));
            adjList[b].add(new Node(a, cost));
        } // for end

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());          // 임의 정점 v1
        int v2 = Integer.parseInt(st.nextToken());          // 임의 정점 v2

        dijkstra(1);
        dijkstra(v1);
        dijkstra(v2);

        int[] cost_1 = costMap.get(1);
        int[] cost_v1 = costMap.get(v1);
        int[] cost_v2 = costMap.get(v2);

        // 1 -> v1 -> v2 -> N
        int way1;
        if (cost_1[v1] == Integer.MAX_VALUE || cost_v1[v2] == Integer.MAX_VALUE || cost_v2[N] == Integer.MAX_VALUE){
            way1 = Integer.MAX_VALUE;
        } else {
            way1 = cost_1[v1]  + cost_v1[v2] + cost_v2[N];
        } // if end

        // 1 -> v2 -> v1 -> N
        int way2;
        if (cost_1[v2] == Integer.MAX_VALUE || cost_v2[v1] == Integer.MAX_VALUE || cost_v1[N] == Integer.MAX_VALUE){
            way2 = Integer.MAX_VALUE;
        } else {
            way2 = cost_1[v2] + cost_v2[v1] + cost_v1[N];
        } // if end

        if (Math.min(way1, way2) == Integer.MAX_VALUE){
            answer.append("-1");
        } else {
            answer.append(Math.min(way1, way2));
        } // if end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    static void dijkstra(int K){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        int[] costs = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++){
            costs[i] = Integer.MAX_VALUE;
        } // for end
        costs[K] = 0;
        priorityQueue.add(new Node(K, 0));

        while (!priorityQueue.isEmpty()){
            Node current = priorityQueue.poll();
            if (!visited[current.node]){
                visited[current.node] = true;
                for (Node neighbor : adjList[current.node]){
                    if (!visited[neighbor.node] && current.cost + neighbor.cost < costs[neighbor.node]){
                        costs[neighbor.node] = current.cost + neighbor.cost;
                        priorityQueue.add(new Node(neighbor.node, costs[neighbor.node]));
                    } // if end
                } // for end
            } // if end
        } // while end
        costMap.put(K, costs);
    } // func end
} // class end