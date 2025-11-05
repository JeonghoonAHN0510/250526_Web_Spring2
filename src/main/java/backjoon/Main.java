package backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());    // 테스트 케이스 T개
        for (int i = 0; i < T; i++){                // 테스트 케이스만큼 반복
            int N = Integer.parseInt(br.readLine());// 팀 N개
            int[] ranking = new int[N];             // 작년 랭킹
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++){
                ranking[j] = Integer.parseInt(st.nextToken());
            } // for end
            System.out.println("ranking = " + Arrays.toString(ranking));

            int M = Integer.parseInt(br.readLine());// 바뀐 쌍 M개
            if (M == 0){
                for (int rank : ranking){
                    answer.append(rank).append(" ");
                } // for end
                answer.append("\n");
                continue;
            } // if end
            Map<Integer, List<Integer>> relatedDegree = new HashMap<>();
            int[] indegree = new int[N + 1];        // 진입차수 배열
            for (int j = 0; j < M; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                indegree[a]++;
                if (relatedDegree.containsKey(b)){
                    relatedDegree.get(b).add(a);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(a);
                    relatedDegree.put(b, list);
                } // if end
            } // for end
            System.out.println("indegree = " + Arrays.toString(indegree));
            System.out.println("relatedDegree = " + relatedDegree);
            System.out.println("===========================");
            // ranking을 순회하면서 stack에 넣고, map에 key가 있으면 while로 작업
            Queue<Integer> queue = new LinkedList<>();
        } // for end

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end