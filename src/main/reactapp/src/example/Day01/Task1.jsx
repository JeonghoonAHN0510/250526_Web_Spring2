const data = [
    {
        name: 'Hedy Lamarr',
        imageUrl: 'https://i.pravatar.cc/150?img=47'
    },
    {
        name: 'Grace Hopper',
        imageUrl: 'https://i.pravatar.cc/150?img=48'
    }
];

export default function Task1( props ) {
    return (
        <>
        <Profile user={data[0]}/>
        <Profile user={data[1]}/>
        </>
    ) // return end
} // func end

function Profile( props ) {
    return (
        <>
        <h2> { props.user.name } </h2>
        <img src={ props.user.imageUrl }/>
        </>
    ) // return end
} // func end