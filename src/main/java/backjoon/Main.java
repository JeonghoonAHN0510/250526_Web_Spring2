package backjoon;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int node;
    int count;

    public Node(int node, int count){
        this.node = node;
        this.count = count;
    } // func end

    @Override
    public int compareTo(Node o) {
        return this.count - o.count;
    } // func end
} // class end

public class Main {
    static StringBuilder answer = new StringBuilder();
    static int[] possibleMove = {2, 1, -1};
    static boolean[] visited = new boolean[100001];
    static int N, K, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 시작 위치
        K = Integer.parseInt(st.nextToken());   // 도착 위치

        dijkstra(N);

        answer.append(result);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    static void dijkstra(int start){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()){
            Node current = queue.poll();
            if (visited[current.node]) continue;
            visited[current.node] = true;

            if (current.node == K){
                result = current.count;
                return;
            } // if end

            for (int i : possibleMove){
                int next;
                int count;
                if (i == 2){
                    next = current.node * i;
                    count = current.count;
                } else {
                    next = current.node + i;
                    count = current.count + 1;
                } // if end
                if (next < 0 || next > 100000) continue;
                if (!visited[next]){
                    queue.add(new Node(next, count));
                } // if end
            } // for end
        } // while end
    } // func end
} // class end