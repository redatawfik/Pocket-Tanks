import React, { memo, useState } from 'react'
import { Button, TextField } from '@material-ui/core'
import axios from 'axios'
import './signin.css'
import { Redirect ,useHistory } from 'react-router-dom'
export default memo(function SignIn() {
    const history = useHistory();
    const [email , setemail] = useState('');
    const [pass , setpass] = useState('')
    async function handleSubmit() {
        const {data, status} = await axios.post("http://localhost:63342/web-api/src/api/login.php" , {
            email,
            password : pass
        })
        if(status == "200" && data['Status'] !== 'Error') {
            alert('userid is ' + data['id'] + '\n' + 
            'username is ' + data['username'] + '\n'+
            'email is ' + data['email']);
            console.log(data);
            history.push('/')
        }
        else{
            console.log(data);
            alert(data['Msg']);
        }
    }
    return (
        <div style = {{width : "100%" , display : 'flex' , justifyContent : 'center'}}  >
            <div style = {{width : "70%"}} >
                <h2 style = {{fontWeight : 'bold' , textAlign : 'center'}}>
                    LOG IN 
                </h2>
                <form className = "auth-data-holder">
                    <TextField 
                    value = {email}
                    onChange = {e => {setemail(e.target.value)}}
                    style = {{width : '50%'}} id="filled-basic" label="email" variant="filled" />
                    <TextField
                    value = {pass}
                    onChange = {e => {setpass(e.target.value)}}
                    style = {{width : '50%'}} id="filled-basic" label="Password" 
                    variant="filled" type = "Password" />
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
