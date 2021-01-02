import {applyMiddleware} from 'redux'
import thunk from 'redux-thunk'
const logger = store => next => action => {
    //console.warn('type :' , action.type)
    let ret = next(action);
    console.warn('new state :' , store.getState());
    return ret;
}
export default applyMiddleware(thunk , logger);