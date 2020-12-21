
import { useEffect, useState } from 'react';
import './App.css';
import {Redirect , Route, Switch} from 'react-router-dom'
import { BrowserRouter } from "react-router-dom";
import HomeScreen from './screens/HomeScreen';
import Auth from './screens/Auth';
function App() {
  
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={HomeScreen} />
        <Route path="/Auth" component={Auth} />
    </Switch>
    </BrowserRouter>
  );
}

export default App;
