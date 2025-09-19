import { useEffect, useState } from 'react'
import './Assignment5.css'
import axios from 'axios';

export default function Assignment5( props ){
    // ============================= useState =============================
    const [ mtitle, setMtitle ] = useState('');             // 제목
    const [ mdirector, setMdirector ] = useState('');       // 감독
    const [ mgenre, setMgenre ] = useState('');             // 장르
    const [ mcomment, setMcomment ] = useState('');         // 간단한 소개
    const [ mpwd, setMpwd ] = useState('');                 // 비밀번호
    const [ movieList, setMovieList ] = useState([]);       // 추천영화 목록
    // ============================= function =============================
    const postMovie = async ( ) => {
        // 1. 입력값 객체로 만들기
        const obj = { mtitle, mdirector, mgenre, mcomment, mpwd };
        // 2. axios post
        const response = await axios.post( 'http://localhost:8080/movie/post', obj );
        // 3. result
        if ( response.status == 200 ){
            alert('등록 성공');
            getMovies();
        } else if ( response.status == 400 ){
            alert('등록 실패');
        } // if end
    } // func end
    const getMovies = async ( ) => {
        // 1. axios get
        const response = await axios.get( 'http://localhost:8080/movie/getMovies' );
        // 2. result
        if ( response.data != null ){
            setMovieList( response.data );
        } // if end
    } // func end
    const deletePrompt = ( mno ) => {
        const pwdInput = prompt('비밀번호를 입력하세요.');
        if ( pwdInput != '' ) deleteMovie( mno, pwdInput );
    } // func end
    const deleteMovie = async ( mno, mpwd ) => {
        // 1. 입력값 객체로 만들기
        const obj = { mno, mpwd };
        // 2. axios put(delete)
        const response = await axios.put( 'http://localhost:8080/movie/delete', obj );
        // 3. result
        if ( response.status == 200 ){
            alert('삭제 성공');
            getMovies();
        } else if ( response.status == 400 ){
            alert('삭제 실패');
        } // if end
    } // func end
    // ============================ useEffect =============================
    useEffect( ( ) => { getMovies() }, [ ] );
    // ============================== return ==============================
    return(
        <>
        <div class="container">
            <div>
                <h3> 영화 토론 플랫폼 </h3>
            </div>
            <div>
                <input type="text" placeholder='제목' value={mtitle} onChange={(e) => {setMtitle(e.target.value)}}/>
                <input type="text" placeholder='감독' value={mdirector} onChange={(e) => {setMdirector(e.target.value)}}/>
                <input type="text" placeholder='장르' value={mgenre} onChange={(e) => {setMgenre(e.target.value)}}/>
                <input type="text" placeholder='간단한 소개' value={mcomment} onChange={(e) => {setMcomment(e.target.value)}}/>
                <input type="password" placeholder='비밀번호' value={mpwd} onChange={(e) => {setMpwd(e.target.value)}}/>
                <button onClick={postMovie}>등록</button>
            </div>
            <div>
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>감독</th>
                            <th>장르</th>
                            <th>간단한 소개</th>
                            <th>비고</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            movieList.map( (movie) =>{
                                return(
                                    <>
                                    <tr>
                                        <td>{movie.mno}</td>
                                        <td>{movie.mtitle}</td>
                                        <td>{movie.mdirector}</td>
                                        <td>{movie.mgenre}</td>
                                        <td>{movie.mcomment}</td>
                                        <td><button onClick={() => {deletePrompt(movie.mno)}}>삭제</button></td>
                                    </tr>
                                    </>
                                ) // return end
                            }) // map end
                        }
                    </tbody>
                </table>
            </div>
        </div>
        </>
    ) // return end
} // func end