import React, { memo } from 'react'
import NavBar from 'react-bootstrap/Navbar'
import {Button} from 'react-bootstrap'
import './nabbarComponent.css';
import axios from 'axios';
import { connect } from 'react-redux';
import { setAuth } from '../redux/actions/rootAction';
import { useHistory } from 'react-router-dom';
export default connect()(function NavBarCompnent({dispatch}) {
    const history = useHistory()
    async function handleLogOut () {
        axios.get("http://localhost:63342/web-api/src/api/logout.php" , {withCredentials:true})
            .then(function (response) {
                //handle success
                dispatch(setAuth(''))
                //alert(authed);
                history.push('/Auth')
            })
            .catch(function (response) {
                //handle error
                alert(response);
            });
    }
    async function handleAbout () {
        alert("Front end: Yasser & emad \n API: Yasser \n Websocket&client: reada & saif")
     }
    
    return (
        <NavBar fixed="top" style = {{display : 'flex' , justifyContent : 'space-evenly' , 
            backgroundColor : 'black',
            
        }}>
            
            <NavBar.Brand>
            <span className = "logo">
                Pocket Tank
            </span>
            </NavBar.Brand>
            <div style = {{width : '50%'}}></div>
            <div style = {{display : 'flex' , justifyContent : 'space-between' , width : '25%'}} >
            <Button style={{color: 'black', fontWeight: 'bold'}} onClick = {handleLogOut} >
                LOG OUT
            </Button>
            <Button  style={{color: 'black', fontWeight: 'bold'}} onClick={(e) => {
      e.preventDefault();
      window.location.href='http://localhost:63342/web-api/src/api/download.php';
      }}>
                Download
            </Button>
            <Button  style={{color: 'black', fontWeight: 'bold'}} onClick= {handleAbout}>
                About Us
            </Button>
            </div>
         

        </NavBar>
    )
})
