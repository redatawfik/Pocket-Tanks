import {SET_AUTH} from '../constants'

export const setAuth = (user) => ({
    type : SET_AUTH,
    user
})
export const setTest = (test) => ({
    type : 'SET_TEST',
    test
})
