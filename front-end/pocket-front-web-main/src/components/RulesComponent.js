import {Button} from 'react-bootstrap'
import Axios from 'axios'
import React, { memo, useEffect, useState } from 'react'    
import CloudDownloadRoundedIcon from '@material-ui/icons/CloudDownloadRounded';
import './7ambola.css';
import axios from 'axios'

export default memo(function RulesComponent() {
    const [data , setdata] = useState([])
    useEffect(async () => {
        axios.get("http://localhost:63342/web-api/src/api/profile.php" , {withCredentials:true})
            .then(function (response) {
                //handle success
                //alert(response)
                setdata(response.data)
            })
            .catch(function (response) {
                //handle error
                //alert(response);
            });
    } , [])
    return (
        <div style = {{width : '23%' , marginLeft : 17 , position : 'relative' , backgroundColor : 'rgba(0,0,0,.5)',height: '20%', paddingTop : 15}} >
            <div style = {{position : 'relative' , width : '100%'}}>
            <div style = {{textAlign : 'center' , marginBottom : 20}}>
                
            </div>
            <div className="mbola">
                <img src='https://icons-for-free.com/iconfiles/png/512/boy+guy+man+icon-1320166733913205010.png' className='avatar'/>
                <p style = {{textAlign : 'center'}}>
                    Username: {data['userName']}
                </p>
                <p style = {{textAlign : 'center'}}>
                    Email: {data['email']}
                </p>
                <p style = {{textAlign : 'center'}}>
                    Score: {data['score']}
                </p>
            </div>
            </div>
        </div>
    )
})
