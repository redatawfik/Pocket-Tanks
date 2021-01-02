import {SET_AUTH} from '../constants'
import {persistReducer} from 'redux-persist' 
import {combineReducers} from 'redux'
import storage from 'redux-persist/lib/storage';

const intialUser = {
    user : null,
    
}
const intialTest = {
    test : "null"
}
const user = (state = intialUser, action) => {
    switch (action.type) {
        case SET_AUTH : return {...state , user : action.user}
        default : return state
    }
}
const test = (state = intialTest , action) => {
    switch (action.type) {
        case 'SET_TEST' : return {...state , test : action.test}
        default : return state
    }
}
const persistConf = {
    key : 'root',
    storage,
    whiteList : ['user']
}
const root =  combineReducers({user ,test})
export default persistReducer(persistConf , root)
