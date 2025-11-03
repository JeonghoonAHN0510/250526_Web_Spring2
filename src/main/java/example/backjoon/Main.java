package example.backjoon;

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder answer = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 보석 개수 N
        int K = Integer.parseInt(st.nextToken());       // 가방 개수 K
        PriorityQueue<String> jewelPQ = new PriorityQueue<>(new  Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                StringTokenizer st1 = new StringTokenizer(o1);
                StringTokenizer st2 = new StringTokenizer(o2);
                int weight1 = Integer.parseInt(st1.nextToken());
                int price1 = Integer.parseInt(st1.nextToken());
                int weight2 = Integer.parseInt(st2.nextToken());
                int price2 = Integer.parseInt(st2.nextToken());
                if (weight1 - weight2 == 0){
                    return price2 - price1;
                } else {
                    return weight2 - weight1;
                } // if end
            } // func end
        });

        for (int i = 0; i < N; i++){
            jewelPQ.offer(br.readLine());
        } // for end


        PriorityQueue<Integer> bagPQ = new PriorityQueue<>();
        for (int i = 0; i < K; i++){
            bagPQ.offer(Integer.parseInt(br.readLine()));
        } // for end

        // poll과 bagPQ와 비교해서 totalWeight에 추가하기
        for (int i = 0; i < N; i++){
            System.out.println("jewelPQ = " + jewelPQ.poll());
        }

        long totalWeight = 0;
        answer.append(totalWeight);

        bw.write(answer.toString().trim());
        bw.flush();
        bw.close();
    } // main end
} // class end