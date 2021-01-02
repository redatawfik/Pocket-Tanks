import { Button } from '@material-ui/core'
import React, { memo, useState } from 'react'
import SignUp from '../components/Regsteir'
import LogIn from '../components/SignIn'
import './auth.css'
export default memo(function Auth() {
    const [authChoise , setauth] = useState(true)

    function changeAuth () {
        setauth(!authChoise);
    }
    return (
        <div className="home-holder">
            <div className = "btn-holder">
                <Button 
                onClick = {changeAuth}
                color="primary" variant = "contained">
                {
                    (authChoise == true) ? ("LogIn") : ("SignUP")
                }
                </Button>
            </div>
            <div className = "auth-staff">
                {
                    (authChoise == true) ? (
                        <SignUp />
                    ) : (<LogIn />)
                }
            </div>
        </div>
    )
})
