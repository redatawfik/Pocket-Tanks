import React, { memo, useState } from 'react'
import { Redirect } from 'react-router-dom'
import HomeCompenet from '../components/HomeCompenet';

export default memo(function HomeScreen() {
    const [authed , setauthed] = useState(false); 
        return (authed == true) ? (
            <HomeCompenet />
        ) : (<Redirect to = {{pathname : "/Auth" }}></Redirect>)

    
    
})
