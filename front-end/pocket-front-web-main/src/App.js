
import { useEffect, useState } from 'react';
import './App.css';
import {Redirect , Route, Switch} from 'react-router-dom'
import { BrowserRouter } from "react-router-dom";
import HomeScreen from './screens/HomeScreen';
import Auth from './screens/Auth';
import { connect } from 'react-redux';
import {store} from './redux/storeCaller'
function App({user}) {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={HomeScreen} />
        <Route path="/auth" component={Auth} />
    </Switch>
    </BrowserRouter>
  );
}
const mapStateToProps = ({user}) => ({
  user
})
export default connect(mapStateToProps)(App)
