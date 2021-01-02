import React from 'react'
import {connect} from 'react-redux';

function test(props){
    return(
        <div>
            <h2> loggedIn: {props.authed}</h2>
        </div>
    )
}

const mapStateToProps = (state, ownProps) =>{
    const userState = ownProps.login ? state.authed: false;
    return{
        loggedin: userState
    }
}

export default connect(mapStateToProps)(test)