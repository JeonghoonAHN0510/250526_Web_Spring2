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
    static int[] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;


        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());   // 정점의 개수 V
        int E = Integer.parseInt(st.nextToken());   // 간선의 개수 E
        int K = Integer.parseInt(br.readLine());    // 시작 정점 K
        visited = new boolean[V + 1];
        costs = new int[V + 1];

        adjList = new List[V + 1];
        for (int i = 1; i <= V; i++){
            adjList[i] = new ArrayList<>();
            costs[i] = Integer.MAX_VALUE;
        } // for end

        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());   // 출발 정점 u
            int v = Integer.parseInt(st.nextToken());   // 도착 정점 v
            int w = Integer.parseInt(st.nextToken());   // 가중치 w

            adjList[u].add(new Node(v, w));
        } // for end

        dijkstra(K);

        for (int i = 1; i <= V; i++){
            if (costs[i] == Integer.MAX_VALUE){
                answer.append("INF").append("\n");
            } else {
                answer.append(costs[i]).append("\n");
            } // if end
        } // for end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    static void dijkstra(int K){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
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
    } // func end
} // class end