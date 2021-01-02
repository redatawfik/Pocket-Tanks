import React, { memo, useState , useEffect } from 'react'
import { Button, TextField } from '@material-ui/core'
import axios from 'axios'
import './signin.css'
import { Redirect ,useHistory } from 'react-router-dom'
import { connect } from 'react-redux'
import {setAuth, setTest} from '../redux/actions/rootAction'

const SignIn = function ({dispatch , user}) {
    const history = useHistory();
    const [email , setemail] = useState('');
    const [pass , setpass] = useState('')
    useEffect(() => {
        console.log(user);
    }, )
    async function handleSubmit() {
        axios.post("http://localhost:63342/web-api/src/api/login.php" , {
            email,
            password : pass}, {withCredentials:true})
            .then(function (response) {
                //handle success
                dispatch(setAuth('authed'))
                history.push('/')
            })
            .catch(function (response) {
                //handle error
                alert(response.response.data['Msg']);
            });
        /*const {data, status} = await axios.post("http://localhost:63342/web-api/src/api/login.php" , {
            email,
            password : pass,
            withCredentials:true})
        if(status == "200") {
            await dispatch(setAuth(data.username))
            history.push('/')
        } else {
            alert(data);
           alert(data["Msg"])

        }*/
        
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
}
const mapStateToProps = ({user , test}) => ({
    user : user,
})
export default connect(mapStateToProps)(SignIn)
