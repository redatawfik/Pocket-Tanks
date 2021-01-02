import { Button, TextField } from '@material-ui/core'
import React, { memo, useState } from 'react'
import { useHistory } from 'react-router-dom';
import axios from 'axios'
import './regsteir.css'
export default memo(function Regsteir() {
    const history = useHistory();
    const [email , setemail] = useState('');
    const [userName , setuserName] = useState('');
    const [pass , setpass] = useState('')
    async function handleSubmit() {
        axios.post("http://localhost:63342/web-api/src/api/register.php" , {
            username : userName,    
            email,
            password : pass}, {withCredentials:true})
            .then(function (response) {
                //handle success
                alert("User Created Go to login");
            })
            .catch(function (response) {
                //handle error
                alert(response.response.data['Msg']);
            });
    }
    return (
        <div style = {{width : "100%" , display : 'flex' , justifyContent : 'center'}}  >
            <div style = {{width : "70%"}} >
                <h2 style = {{fontWeight : 'bold' , textAlign : 'center'}}>
                    Register 
                </h2>
                <form className = "auth-data-holder">
                    <TextField 
                    value = {userName}
                    onChange = {e => setuserName(e.target.value)}
                    style = {{width : '50%'}} id="filled-basic" label="UserName" variant="filled" />
                    <TextField 
                    value = {email}
                    onChange = {e => {setemail(e.target.value)}}
                    style = {{width : '50%'}} id="filled-basic" label="email" variant="filled" />
                    <TextField
                    value = {pass}
                    onChange = {e => {setpass(e.target.value)}}
                    style = {{width : '50%'}} id="filled-basic" label="Password" 
                    variant="filled" data = "Password" />
                    <Button 
                    onClick = {handleSubmit}
                    color = "primary" variant = "outlined">
                        Submit
                    </Button>
                </form>
            </div>
        </div>
    )
})
