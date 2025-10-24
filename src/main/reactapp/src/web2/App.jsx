import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';

export default function App(props){
    return(
        <>
        <Router>
            <div> header </div>
            <Routes>
                {/* 권한에 따른 조건 */}
                {/* 1. 누구자 접근 가능 */}


                {/* 2. USER || 그 외 접근 가능 */}


                {/* 3. ADMIN || 그 외 접근 가능 */}


            </Routes>
        </Router>
        </>
    ) // return end
} // func end