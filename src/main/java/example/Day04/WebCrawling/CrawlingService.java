package example.Day04.WebCrawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlingService {

    // 1. 뉴스 크롤링 -> https://www.karnews.or.kr/
    public List<String> task1(){
        List<String> list = new ArrayList<>();
        try {
            // 1-1. 크롤링할 웹페이지 주소
            String URL = "https://www.karnews.or.kr/news/articleList.html?sc_section_code=S1N1&view_type=sm";
            // 1-2. Jsoup을 이용한 웹주소의 HTML 문서 가져오기 -> Document import 주의!!
            // Jsoup.connect( 크롤링할주소 ).get()
            Document document = Jsoup.connect( URL ).get();
            System.out.println("document = " + document);
            // 1-3. 가져올 HTML.select( "가져올 HTML의 CCS선택자" );
            // JS : document.querySelector
            // Jsoup : document.select
            Elements aList = document.select( ".titles > a" );
            System.out.println("aList = " + aList);
            // 1-4. 가져온 마크업들을 반복하여, 텍스트만 추출
            // .text() : 마크업 사이의 텍스트만 추출
            for ( Element a : aList ) {
                String title = a.text();
                System.out.println("title = " + title);
                // 만일 제목이 없으면, 다음 제목으로
                if ( title.isBlank() ) continue;
                list.add( title );
            } // for end
        } catch ( Exception e ) {
            System.out.println( e );
        } // try-catch end
        return list;
    } // func end
} // class end