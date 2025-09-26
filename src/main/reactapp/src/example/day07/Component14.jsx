import Button from '@mui/joy/Button';
import Avatar from '@mui/joy/Avatar';
import Box from '@mui/joy/Box';
import Input from '@mui/joy/Input';
import Select from '@mui/joy/Select';
import Option from '@mui/joy/Option';
import Switch from '@mui/joy/Switch';
import Badge from '@mui/joy/Badge';
import Typography from '@mui/joy/Typography';
import { useState } from 'react';
import SideBar from './SideBar';


export default function Comoponent14( props ){
    const handleChange = (event, newValue) => {
        alert(`You chose "${newValue}"`);
    };
    const [checked, setChecked] = useState(false);
    return(
        <>
        <h3> MUI 사용법 </h3>

        <h3> 버튼 </h3>
        <Box sx={{ display: 'flex', gap: 2, flexWrap: 'wrap' }}>
            <Button>Button</Button>
            <Button disabled>Disabled</Button>
            <Button loading>Loading</Button>
        </Box>
        <h3> 입력 </h3>
        <Input placeholder="Type in here…" color='red'/>
        <h3> 선택 </h3>
        <Select defaultValue="dog" onChange={handleChange}>
            <Option value="dog">Dog</Option>
            <Option value="cat">Cat</Option>
            <Option value="fish">Fish</Option>
            <Option value="bird">Bird</Option>
        </Select>
        <h3> 스위치 </h3>
        <Switch
            checked={checked}
            onChange={(event) => setChecked(event.target.checked)}
        />
        <h3> Avatar </h3>
        {/* Box는 Div와 같은 역할, style은 카멜표기법으로 작성 */}
        <Box sx={{ display: 'flex', gap: 2 }} style={{backgroundColor:'black'}}>
            <Avatar />
            <Avatar>JG</Avatar>
            <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
        </Box>
        <h3> Badge </h3>
        <Badge>
            <Typography sx={{ fontSize: 'xl' }}><Avatar /></Typography>
        </Badge>
        <h3> List : Collapsible list(Sidebar) </h3>
        <SideBar />
        <h3> List : Navigation menu(Header) </h3>
        </>
    ) // return end
} // func end

/*
- React에서 CSS 적용법
1. CSS 파일 생성
    1) CSS 파일을 생성한다.
    2) 적용할 컴포넌트에서 import한다.
        -> import 'CSS파일경로';
2. 컴포넌트에 직접 작성
    1) 컴포넌트 props로 객체 유형으로 작성한다.
    2) JS 작성 {} + 객체 {} 여서 {}가 2개 작성된다.
        -> style={{}}
        -> { font-size : 10px } ==> { fontSize : 10px } : '-'이 아닌 카멜표기법으로 작성한다.
*/