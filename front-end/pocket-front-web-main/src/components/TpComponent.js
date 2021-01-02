import { Divider, List, ListItem } from '@material-ui/core'
import Axios from 'axios'
import React, { memo, useEffect, useState } from 'react'
import { connect } from 'react-redux'
import './7ambola.css';
import Player from './player/Player.js';

const mapStateToProps = ({user}) => ({
    user
})
export default connect(mapStateToProps)(function TpComponent({user}) {
    const [data , setdata] = useState([])
    useEffect(async () => {
        const {data , status} = await Axios.get('http://localhost:63342/web-api/src/api/scoreboard.php')
        if(status == 200) {
            setdata(data)
        }
    } , [])
    return (
        <div style = {{width : '22%' , paddingRight : 17 ,
         position : 'relative', 
         backgroundColor : 'rgba(0,0,0,.5)', paddingTop : 15, marginRight : 15, height: '10%'}} >
            <div style = {{position : 'relative' , width : '100%'}}>
            <div className='test'>
                <div style = {{textAlign : 'center'}}>
                    <p style = {{fontSize : 25}}> Top Players Rate</p>
                </div>
                <List>
                   {
                       data.map(item => (
                        <Player name={item.userName} score={item.score}/>))
                   }
                    
                </List>
            </div>
            </div>
        </div>
    )
})
