import rootReducer from './reducers/rootReducer'
import middleWare from './middleWares/rootMiddleWare'
import {createStore} from 'redux'
import {persistStore} from 'redux-persist'

export const store = createStore(rootReducer , middleWare);
export const persistor = persistStore(store);