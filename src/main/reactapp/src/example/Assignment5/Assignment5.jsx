import { useEffect, useState } from 'react'
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Table, Button, Form, Modal } from 'react-bootstrap';
import './Assignment5.css'; 

export default function Assignment5( props ){
    // ============================= useState =============================
    const [ mtitle, setMtitle ] = useState('');             // 제목
    const [ mdirector, setMdirector ] = useState('');       // 감독
    const [ mgenre, setMgenre ] = useState('');             // 장르
    const [ mcomment, setMcomment ] = useState('');         // 간단한 소개
    const [ mpwd, setMpwd ] = useState('');                 // 비밀번호
    const [ movieList, setMovieList ] = useState([]);       // 추천영화 목록
    const [ show, setShow ] = useState({show: false, mtitle : '', mno : 0});    // 모달
    const [ discussionListByMno, setDiscussionListByMno ] = useState([]);       // 영화별 토론목록
    const [ dcontent, setDcontent ] = useState('');         // 토론내용
    const [ dpwd, setDpwd ] = useState('');                 // 토론 비밀번호
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

    const deletePrompt = ( mno, type ) => {
        const pwdInput = prompt('비밀번호를 입력하세요.');
        if ( type == 'movie' ){
            deleteMovie( mno, pwdInput );
        } else if ( type == 'discussion' ){
            deleteDicussion( mno, pwdInput );
        } // if end
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

    const postDiscussion = async ( mno ) => {
        // 1. 입력값 객체로 만들기
        const obj = { dcontent, dpwd, mno };
        // 2. axios post
        const response = await axios.post( 'http://localhost:8080/discussion/post', obj );
        // 3. result
        if ( response.status == 200 ){
            alert('등록 성공');
            getDiscussionByMno( mno );
        } else {
            alert('등록 실패');
        } // if end
    } // func end

    const getDiscussionByMno = async ( mno ) => {
        // 1. axios get
        const response = await axios.get( `http://localhost:8080/discussion/getByMno?mno=${mno}` );
        // 2. result
        if ( response.data != null ){
            setDiscussionListByMno( response.data );
        } // if end
        console.log( response.data );
    } // func end

    const deleteDicussion = async ( dno, dpwd ) => {
        // 1. 입력값 객체로 만들기
        const obj = { dno, dpwd };
        // 2. axios put(delete)
        const response = await axios.put( 'http://localhost:8080/discussion/delete', obj );
        // 3. result
        if ( response.status == 200 ){
            alert('삭제 성공');
            let newList = discussionListByMno.filter( (d) => {
                return d.dno != dno;
            })
            setDiscussionListByMno( newList );
        } else if ( response.status == 400 ) {
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
            <div class="inputGroup">
                <Form.Control type="text" placeholder='제목' value={mtitle} onChange={(e) => {setMtitle(e.target.value)}}/>
                <Form.Control type="text" placeholder='감독' value={mdirector} onChange={(e) => {setMdirector(e.target.value)}}/>
                <Form.Control type="text" placeholder='장르' value={mgenre} onChange={(e) => {setMgenre(e.target.value)}}/>
                <Form.Control type="text" placeholder='간단한 소개' value={mcomment} onChange={(e) => {setMcomment(e.target.value)}}/>
                <Form.Control type="password" placeholder='비밀번호' value={mpwd} onChange={(e) => {setMpwd(e.target.value)}}/>
                <Button variant="primary" onClick={postMovie}>등록</Button>
            </div>
            <div>
                <Table striped bordered hover>
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
                                        <td>
                                            <Button variant="primary" onClick={() => {setShow({show:true, mtitle : movie.mtitle, mno : movie.mno}); getDiscussionByMno(movie.mno)}}>
                                                토론 페이지
                                            </Button>
                                            <Button variant="danger" onClick={() => {deletePrompt(movie.mno, 'movie')}}
                                                >삭제
                                            </Button>
                                        </td>
                                    </tr>
                                    <Modal show={show.show} onHide={() => setShow(false)} dialogClassName="modal-90w" aria-labelledby="example-custom-modal-styling-title">
                                        <Modal.Header closeButton>
                                        <Modal.Title id="example-custom-modal-styling-title">
                                            {show.mtitle}의 토론페이지
                                        </Modal.Title>
                                        </Modal.Header>
                                        <Modal.Body>
                                            <div>
                                                {
                                                    discussionListByMno.map( (discussion) => {
                                                        return(
                                                            <>
                                                            <div>
                                                                <span>
                                                                    {discussion.dcontent}
                                                                </span>
                                                                <Button variant="danger" onClick={() => {deletePrompt( discussion.dno, 'discussion')}}
                                                                    >삭제
                                                                </Button>
                                                            </div>
                                                            </>
                                                        ) // return end
                                                    }) // map end
                                                }
                                            </div>
                                            <div class="inputGroup">
                                                <Form.Control type="text" placeholder='토론내용' value={dcontent} onChange={(e) => {setDcontent(e.target.value)}}/>
                                                <Form.Control type="password" placeholder='비밀번호' value={dpwd} onChange={(e) => {setDpwd(e.target.value)}}/>
                                                <Button variant="primary" onClick={() => {postDiscussion(show.mno)}}>등록</Button>
                                            </div>
                                        </Modal.Body>
                                    </Modal>
                                    </>
                                ) // return end
                            }) // map end
                        }
                    </tbody>
                </Table>
            </div>
        </div>
        </>
    ) // return end
} // func end