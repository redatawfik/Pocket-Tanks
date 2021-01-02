import React, { memo } from 'react'
import Axios from 'axios'

export default memo(function SignOut() {
    useEffect( async () => {
        axios.get("http://localhost:63342/web-api/src/api/logout.php" , {withCredentials:true})
            .then(function (response) {
                //handle success
                alert(response);
                //history.push('/')
            })
            .catch(function (response) {
                //handle error
                alert(response);
            });
    } , [])
    return (
        <div>
        </div>
    )
})
