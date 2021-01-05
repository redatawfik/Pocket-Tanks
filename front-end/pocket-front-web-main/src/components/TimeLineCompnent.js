import { Container } from '@material-ui/core'
import Axios from 'axios'
import React, { memo, useEffect, useState } from 'react'
import { Redirect ,useHistory } from 'react-router-dom'
import {Button} from 'react-bootstrap'
import { connect } from 'react-redux'
import axios from 'axios'

import GameCompenent from './GameCompenent'
const mapStateToProps = ({user}) => ({
    user
})
export default connect(mapStateToProps)(function TimeLineCompnent({user , dispatch}) {
    const [data , setdata] = useState([])
    const history = useHistory();
    useEffect( async () => {
        axios.get("http://localhost:63342/web-api/src/api/matchHistory.php" , {withCredentials:true})
            .then(function (response) {
                //handle success
                console.log(response.data);
                setdata(response.data)

            })
            .catch(function (response) {
                //handle error
                history.push('/Auth');
            });
    } , [])
    return (
        <div style = {{width : '46%' , backgroundColor : 'rgba(0,0,0,.5)' , borderRadius : 50,
            height : (data.length < 5) ? 1000 : 'auto'
        }}>
             <div style = {{width : '100%',}}>
                    <p style = {{textAlign : 'center' ,
                     fontSize : 20, fontWeight : 'bolder'}}>Last Matches</p>
                    <div>
                        {
                            data.map(item => (
                                <GameCompenent winner = {item.winner_uname}
                                loser = {item.looser_uname}
                                score1 = {item.winner_score}
                                score2 = {item.looser_score}
                                />
                            ))
                        }
                    </div>
                </div> 
        </div>
    )
})