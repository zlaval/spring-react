import axios from "axios";
import {AUTH_USER, SIGNUP} from "./types";


export const signup = ({username, fullName, password}, callback) => async dispatch => {
    const resp = await axios.post('http://localhost:8080/api/v1/users/register', {
        username, fullName, password
    })
    dispatch({
        type: SIGNUP,
        payload: resp.data
    })
    callback()
}

export const signin = ({username, password}, callback) => async dispatch => {
    const resp = await axios.post('http://localhost:8080/api/v1/users/login', {
        username, password
    });
    dispatch({
        type: AUTH_USER,
        payload: resp.data.token
    });
    localStorage.setItem('token', resp.data.token);
    callback();
}