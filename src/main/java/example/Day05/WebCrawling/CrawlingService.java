package example.Day05.WebCrawling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service    // 컨테이너에 빈 등록 -> IOC(제어의 역전)
public class CrawlingService {

    // 1. 다음 날씨정보 크롤링 -> https://weather.daum.net/
    public Map< String, String > task1(){
        // 1-1. 크롬 설치하기 -> WebDriverManager
        WebDriverManager.chromedriver().setup();
        // 1-2. 크롬 옵션 객체 생성하기
        ChromeOptions options = new ChromeOptions();
        // 1-3. 크롬을 사용하되, 브라우저는 실행하지않는 옵션 설정
        options.addArguments( "--headless=new", "--disable-gpu" );
        // 1-4. 웹드라이버(셀레니움) 객체 생성 -> 크롬 옵션 설정
        WebDriver driver = new ChromeDriver( options );
        // 1-5. 크롤링할 웹주소 설정하기
        String URL = "https://weather.daum.net/";
        // 1-6. 셀레니움에 웹주소 주입하기
        driver.get( URL );
        // 1-7. 셀레니움 대기 시키기 -> 동적 페이지여서, 데이터를 가져올 시간을 기다려야한다.
        WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds( 3 ) );
        // 1-8. 크롤링할 HTML 가져오기 -> 지역, 온도, 상태 -> .부모식별자 .해당식별자 를 사용해서 중복을 방지
        // (1) 지역 -> .info_location .tit_location
        WebElement location = wait.until( ExpectedConditions.presenceOfElementLocated( By.cssSelector( ".info_location .tit_location" ) ) );
        System.out.println("location = " + location);
        // (2) 온도 -> .wrap_weather .num_deg
        WebElement degree = wait.until( ExpectedConditions.presenceOfElementLocated( By.cssSelector( ".wrap_weather .num_deg" ) ) );
        System.out.println("degree = " + degree);
        // (3) 상태 -> .wrap_weather .txt_sub
        WebElement status = wait.until( ExpectedConditions.presenceOfElementLocated( By.cssSelector( ".wrap_weather .txt_sub" ) ) );
        System.out.println("status = " + status);
        // (4) 미세먼지 -> .now_airinfo .num_now
        WebElement air = wait.until( ExpectedConditions.presenceOfElementLocated( By.cssSelector( ".now_airinfo .num_now" ) ) );
        System.out.println("air = " + air);
        // 1-9. 크롤링한 요소의 텍스트를 추출하여 Map에 저장하기 -> .getText()
        Map< String, String > map = new HashMap<>();
        map.put( "location", location.getText() );
        map.put( "degree", degree.getText() );
        map.put( "status", status.getText() );
        map.put( "air", air.getText() );
        // 1-10. 셀레니움 웹드라이버 수동 종료시키기
        driver.quit();
        // 1-11. 정보를 저장한 Map 반환하기
        return map;
    } // func end

    // 2. 영화 리뷰정보 크롤링 -> https://cgv.co.kr/cnm/cgvChart/movieChart/89706
    // -> 무한 스크롤링을 통한 페이징처리가 되어있음.
    public List< String > task2(){
        // 2-1. 크롬 설치하기 -> WebDriverManager
        WebDriverManager.chromedriver().setup();
        // 2-2. 크롬 옵션 객체 생성하기
        ChromeOptions options = new ChromeOptions();
        // 2-3. 크롬을 사용하되, 브라우저는 실행하지않는 옵션 설정
        options.addArguments( "--headless=new", "--disable-gpu" );
        // 2-4. 웹드라이버(셀레니움) 객체 생성 -> 크롬 옵션 설정
        WebDriver driver = new ChromeDriver( options );
        // 2-5. 크롤링할 웹주소 설정하기
        String URL = "https://cgv.co.kr/cnm/cgvChart/movieChart/89706";
        // 2-6. 셀레니움에 웹주소 주입하기
        driver.get( URL );
        // 2-7. 스크롤 내리는 작업 5번 실행하기
        List<String> contents = new ArrayList<>();
        // 2-8. JS 조작하는 객체 생성하기 -> 셀레니움 객체를 스크립트 객체로 변환
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for ( int i = 0; i < 5; i++ ){
            //======================== Java에서 JS 실행시키기 : 스크롤을 내리기 위해서 ========================\\
            // 2-9. 화면을 최하단으로 이동시키기
            js.executeScript( "window.scrollTo( 0, document.body.scrollHeight ); " );
            // 2-10. 데이터 가져올 시간 1초 기다리기
            try {
                Thread.sleep( 1000 );
            } catch ( Exception e ){
                System.out.println( e );
            } // try-catch end
        } // for end
        // 2-11. 리뷰 여러개 -> findElements -> .reveiwCard_txt__RrTgu
        List<WebElement> reviews = driver.findElements( By.cssSelector( ".reveiwCard_txt__RrTgu" ) );
        // 2-12. 가져온 리뷰들을 반복문을 통해 리스트에 담기
        for ( WebElement review : reviews ){
            contents.add( review.getText() );
        } // for end
        return contents;
    } // func end
} // class end