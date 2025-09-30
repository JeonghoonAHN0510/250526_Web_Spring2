import axios from "axios";
import {useRef} from "react";

export default function Component15(){
    // [1] 예제 1
    const axios1 = async () => {
        try{
            // spring과 react는 같은 도메인이 아니기에, http부터 작성해야한다.
            const response = await axios.get("http://localhost:8080/axios");
            const data = response.data;
            console.log("[1] : " + data);
        } catch (e) {
            console.log(e);
        } // try-catch end
    } // func end

    // [2] 예제 2
    const axios2 = async () => {
        try{
            // spring과 react는 같은 도메인이 아니기에, http부터 작성해야한다.
            const obj = { id : "qwe123", pwd : "qwe123" };
            const option = { withCredentials: true };       // JS에서도 Credentials를 허용해야한다.
            // axios.post("URL", body, option)
            const response = await axios.post("http://localhost:8080/axios/login", obj, option);
            const data = response.data;
            console.log("[2] : " + data);
        } catch (e) {
            console.log(e);
        } // try-catch end
    } // func end

    // [3] 예제 3
    const axios3 = async () => {
        try{
            // spring과 react는 같은 도메인이 아니기에, http부터 작성해야한다.
            const option = { withCredentials: true };       // JS에서도 Credentials를 허용해야한다.
            // axios.get("URL", option)
            const response = await axios.get("http://localhost:8080/axios/info", option);
            const data = response.data;
            console.log("[3] : " + data);
        } catch (e) {
            console.log(e);
        } // try-catch end
    } // func end

    // fetch는 기본전송 타입이 'form', axios는 기본전송 타입이 'json'
    // [4] 일반 Form : form은 name 속성으로 식별(자바의 멤버변수와 같아야함)
    const formRef1 = useRef();
    const axios4 = async () => {
        try{
            // 4-1. useRef가 참조중인 dom객체 가져오기
            const form = formRef1.current;
            const option = {headers: {"Content-Type": "application/x-www-form-urlencoded"}}
            const response = await axios.post("http://localhost:8080/axios/form", form, option);
            const data = response.data;
            console.log("[4] : " + data);
        } catch (e) {
            console.log(e);
        } // try-catch end
    } // func end

    // [5] 첨부파일 Form
    const formRef2 = useRef();
    const axios5 = async () => {
        try{
            const form = formRef2.current;
            const formData = new FormData(form);    // form 데이터를 바이트 형식으로 변환
            const option = {headers: {"Content-Type": "multipart/form-data"}}
            const response = await axios.post("http://localhost:8080/axios/formdata", formData, option);
            const data = response.data;
            console.log("[5] : " + data);
        } catch (e) {
            console.log(e);
        } // try-catch end
    } // func end

    return(
        <>
            <h5> AXIOS TEST </h5>
            <button onClick={axios1}> axios 1 </button>
            <button onClick={axios2}> axios 2 </button>
            <button onClick={axios3}> axios 3 </button>
            <form ref={formRef1}>
                <input name="id"/>  <br/>
                <input name="pwd"/> <br/>
                <button type="button" onClick={axios4}> axios 4</button>
            </form>
            <form ref={formRef2}>
                <input type="file"/> <br/>
                <button type="button" onClick={axios5}> axios 5 </button>
            </form>
        </>
    ) // return end
} // func end