package backjoon;

import java.io.*;
import java.util.*;

class Node{
    int node;
    int count;

    public Node(int node, int count){
        this.node = node;
        this.count = count;
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

        bfs(N);

        answer.append(result);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
    static void bfs(int start){
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()){
            Node current = queue.pollFirst();
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
                    if (next < 0 || next > 100000) continue;
                    if (!visited[next]){
                        queue.addFirst(new Node(next, count));
                    } // if end
                } else {
                    next = current.node + i;
                    count = current.count + 1;
                    if (next < 0 || next > 100000) continue;
                    if (!visited[next]){
                        queue.addLast(new Node(next, count));
                    } // if end
                } // if end
            } // for end
        } // while end
    } // func end
} // class end