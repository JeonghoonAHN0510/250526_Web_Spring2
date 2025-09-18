import { useEffect, useState } from "react";
import './Task5.css';
import axios from "axios";

export default function Task5( props ){
    // [1-1] name useState
    const [ name, setName ] = useState('');
    // [1-2] name input
    const nameInput = ( e ) => {
        setName( e.target.value );
    } // func end

    // [2-1] phone useState
    const [ phone, setPhone ] = useState('');
    // [2-2] phone input
    const phoneInput = ( e ) => {
        setPhone( e.target.value );
    } // func end

    // [3-1] age useState
    const [ age, setAge ] = useState('');
    // [3-2] age input
    const ageInput = ( e ) => {
        setAge( e.target.value );
    } // func end

    // [4-1] list useState
    const [ list, setList ] = useState([]);
    // [4-2] get list
    const getList = async ( ) => {
        // 1. axios get
        const response = await axios.get( "http://localhost:8080/phone" );
        // 2. useState를 이용한 재렌더링
        setList( response.data );
    } // func end
    // [4-3] post list
    const postList = async ( ) => {
        if ( name == '' || phone == '' || age == '' ) return;
        // 1. 입력값으로 객체 만들기
        const obj = { name, phone, age };
        // 2. axios post
        const response = await axios.post( "http://localhost:8080/phone", obj );
        // 3. result
        if ( response.status == 200 ) getList();
    } // func end
    // [4-4] delete list
    const deleteList = async ( mno ) => {
        // 1. axios delete
        const response = await axios.delete( `http://localhost:8080/phone?mno=${mno}` );
        // 2. result
        if ( response.status == 200 ) getList();
    } // func end

    // [5] useEffect를 이용한 최초 1회 실행
    useEffect( ( ) => { getList() }, [ ] )

    // * 엔터키 입력 시, 등록 진행
    const enterKey = ( ) => {
        if ( event.keyCode == 13 ){
            postList();
        } // if end
    } // func end

    return(
        <>
        <div class="container">
            <h2>전화번호부</h2>
            <p class="inputBox">
                <input placeholder="성명" value={name} onChange={nameInput} onKeyUp={enterKey}/>
                <input placeholder="연락처 (예 : 010-1234-5678)" value={phone} onChange={phoneInput} onKeyUp={enterKey}/>
                <input type="number" placeholder="나이" value={age} onChange={ageInput} onKeyUp={enterKey}/>
                <button onClick={postList}>등록</button>
            </p>
            <div>
                {
                    list.map( (member) => {
                        return(
                            <>
                            <div class="member">
                                <div class="info">
                                    <span><strong>성명</strong> : {member.name}</span>
                                    <span><strong>연락처</strong> : {member.phone}</span>
                                    <span><strong>나이</strong> : {member.age}</span>
                                </div>
                                <div class="removeBox">
                                    <button onClick={ () => {deleteList(member.mno)}}>삭제</button>
                                    {/* onClick={ () => { removeList(member.mno) } } */}
                                </div>
                            </div>
                            </>
                        ) // return end
                    }) // map end
                }
            </div>
            <span class="amount"> 총 { list.length }명</span>
        </div>
        </>
    ) // return end
} // func end