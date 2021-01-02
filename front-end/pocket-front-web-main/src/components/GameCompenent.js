import React, { memo } from 'react'
import EmojiEventsSharpIcon from '@material-ui/icons/EmojiEventsSharp';
export default memo(function GameCompenent({winner = "player1" , 
                        loser = "player2" , date = new Date(),
                        score1 = 1, score2 = 0
                            }) {
    return (
        <div style = {{display : 'flex' , 
        justifyContent : 'space-between', 
        flexDirection : 'column',
        width : '100%',
        height : 170,
        marginTop : 15,
        marginBottom : 30,
        
        }} >
        <div style = {{display : 'flex' , 
        justifyContent : 'space-between', 
        alignSelf : 'center',
        flexDirection : 'row', width : '50%'}}>
            <p> {winner} </p>
            <p> vs </p>
            <p> {loser} </p>
        </div>
        <div style = {{display : 'flex' , 
        justifyContent : 'space-between', 
        alignSelf : 'center',
        flexDirection : 'row', width : '50%',  fontWeight : 'bold'}}>
            <p> {
                (score1 > score2) ? score1 : score2
                } </p>
            <p>  </p>
            <p> {(score1 < score2) ? score1 : score2} </p>
        </div>
        <div style = {{textAlign : 'center'}}>
            the winner is <span style = {{fontWeight : 'bold'}}>{winner}</span> <EmojiEventsSharpIcon/>
        </div>
        </div>
    )
})
