import { useState } from "react";

import './Task4.css';

// 회원삭제를 위한 mno
let mno = 0;
export default function Task4( props ){
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
    // [4-2] add list
    const addList = ( ) => {
        if ( name == '' || phone == '' || age == '' ) return;
        // member 객체를 만들어서, list.push( obj )로 진행 -> 스프레드 연산자를 통해 setList()
        setList( [ ...list, { name : name, phone : phone, age : age, mno : mno++ } ] )
    } // func end
    // [4-3] remove list
    const removeList = ( e ) => {
        const newList = list.filter( (member) => {
            return member.mno != e.target.value;
        }) // filter end
        setList( newList );
    } // func end

    return(
        <>
        <div class="container">
            <h2>전화번호부</h2>
            <p class="inputBox">
                <input placeholder="성명" value={name} onChange={nameInput}/>
                <input placeholder="연락처 (예 : 010-1234-5678)" value={phone} onChange={phoneInput}/>
                <input type="number" placeholder="나이" value={age} onChange={ageInput}/>
                <button onClick={addList}>등록</button>
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
                                    <button value={member.mno} onClick={removeList}>삭제</button>
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