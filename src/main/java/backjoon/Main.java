package backjoon;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 문제 개수 N
        int M = Integer.parseInt(st.nextToken());       // 정보 개수 M
        int[] indegree = new int[N + 1];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        } // for end

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());   // 먼저 풀어야 좋은 문제
            int y = Integer.parseInt(st.nextToken());   // 나중에 풀어야 좋은 문제

            graph.get(x).add(y);
            indegree[y]++;                              // y를 풀기 위한 선행조건 개수 증가
        } // for end

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 1; i <= N; i++){
            // 바로 풀 수 있는 문제를 큐에 넣기
            if (indegree[i] == 0) priorityQueue.add(i);
        } // for end

        while (!priorityQueue.isEmpty()){
            int current = priorityQueue.poll();
            answer.append(current).append(" ");

            for (int next : graph.get(current)){
                // 조건 하나 해결했으니 감소
                indegree[next]--;
                // 0개가 되었으면, 풀 수 있으므로 큐에 넣기
                if (indegree[next] == 0) priorityQueue.add(next);
            } // for end
        } // while end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end