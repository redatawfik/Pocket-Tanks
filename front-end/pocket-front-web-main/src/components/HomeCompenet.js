import { Button } from '@material-ui/core'
import React, { memo } from 'react'
import { connect } from 'react-redux'
import { setAuth } from '../redux/actions/rootAction'
import NavBarCompnent from './NavBarCompnent'
import RulesComponent from './RulesComponent'
import TimeLineCompnent from './TimeLineCompnent'
import TpComponent from './TpComponent'
import "./homeStyle.css";
export default connect()(function HomeCompenet({dispatch}) {
    return (
        <div className = "home-holderr">
           <NavBarCompnent/>
           <div style = {{marginTop : 86, paddingTop : 20 , display : 'flex' , justifyContent : 'space-between' , flexDirection : 'row'}}>
                <RulesComponent />
                <TimeLineCompnent />
                <TpComponent />
           </div>
        </div>
    )
})
