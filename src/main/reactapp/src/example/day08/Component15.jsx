import axios from "axios";

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


    return(
        <>
            <h5> AXIOS TEST </h5>
            <button onClick={axios1}> axios 1 </button>
            <button onClick={axios2}> axios 2 </button>
            <button onClick={axios3}> axios 3 </button>
        </>
    ) // return end
} // func end