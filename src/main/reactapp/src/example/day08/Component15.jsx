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
    return(
        <>
            <h5> AXIOS TEST </h5>
            <button onClick={axios1}> axios 1 </button>
        </>
    ) // return end
} // func end