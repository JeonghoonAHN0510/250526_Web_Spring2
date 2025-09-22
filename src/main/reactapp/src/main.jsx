/*
** 아래 함수를 변수 선언없이 작성하면 이러한 코드 **

import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <App />
    </StrictMode>,
)
*/

// ================================= 고정 =================================
// main.jsx에서 index.html의 id=root 마크업에 최초의 화면함수(컴포넌트)를 렌더링하는 곳
// 1. 리액트 root 함수 호출
// -> react 라이브러리의 createRoot 함수를 import
import { createRoot } from 'react-dom/client'
// -> createRoot는 named export된 듯??

// 2. index.html(SPA)에서 root 마크업 가져오기      -> where
const root = document.querySelector('#root');

// 3. 가져온 root 마크업을 createRoot 함수의 매개변수로 전달한다.
const create = createRoot( root );

// ================================= 기본 예제 =================================
// // 4. 최종적으로 렌더링하기
// // 4-1. 렌더링할 컴포넌트(함수) 가져오기             -> what
// import App from './App.jsx';
// // -> App은 default export된 듯??

// // 4-2. 렌더링하기                                 -> render
// create.render( <App /> );

// ================================= Day01 =================================
// 4. 렌더링할 컴포넌트 import하기
// import Component1 from './example/Day01/Component1.jsx';
// import Component2 from './example/Day01/Component2.jsx';
// import Component3 from './example/Day01/Component3.jsx';
// import Task1 from './example/Day01/Task1.jsx';
// import Task2 from './example/Day01/Task2.jsx';

// 5. 렌더링하기 -> create.render()
// create.render( <Component1/> );
// create.render( <Component2/> );
// create.render( <Component3/> );
// create.render( <Task1/> );
// create.render( <Task2/> );

// ================================= Day02 =================================
// 4. 렌더링할 컴포넌트 import하기
// import Component4 from './example/Day02/Component4.jsx';
// import Component5 from './example/Day02/Component5.jsx';
// import Component6 from './example/Day02/Component6.jsx';
// import Component7 from './example/Day02/Component7.jsx';
// import Task3 from './example/Day02/Task3';
// import Task4 from './example/Day02/Task4';

// 5. 렌더링하기 -> create.render()
// create.render( <Component4/> );
// create.render( <Component5/> );
// create.render( <Component6/> );
// create.render( <Component7/> );
// create.render( <Task3/> );
// create.render( <Task4/> );

// ================================= Day03 =================================
// 4. 렌더링할 컴포넌트 import하기
// import Component8 from './example/Day03/Component8';
// import Component9 from './example/Day03/Component9';
// import Component10 from './example/Day03/Component10';
// import Task5 from './example/Day03/Task5';

// 5. 렌더링하기 -> create.render()
// create.render( <Component8/> );
// create.render( <Component9/> );
// create.render( <Component10/> );
// create.render( <Task5/> );

// ============================== Assignment5 ==============================
// 4. 렌더링할 컴포넌트 import하기
// import Assignment5 from './example/Assignment5/Assignment5';

// 5. 렌더링하기 -> create.render()
// create.render( <Assignment5/> );

// ================================= Day04 =================================
// 4. 렌더링할 컴포넌트 import하기
// import Component11 from './example/Day04/Component11';
import Component12 from './example/Day04/Compoenet12';

// 5. 렌더링하기 -> create.render()
// create.render( <Component11/> );
create.render( <Component12/> );