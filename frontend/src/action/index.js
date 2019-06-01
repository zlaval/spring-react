import axios from "axios";
import {AUTH_USER, SIGNUP} from "./types";
import setJwtToken from "../utils/setJwtToken";
import jwt_decode from "jwt-decode"


export const signup = ({username, fullName, password}, callback) => async dispatch => {
    const resp = await axios.post('http://localhost:8080/api/v1/users/register', {
        username, fullName, password
    });
    dispatch({
        type: SIGNUP,
        payload: resp.data
    });
    callback()
};

export const signin = ({username, password}) => async dispatch => {
    const resp = await axios.post('http://localhost:8080/api/v1/users/login', {
        username, password
    });

    const jwtToken = resp.data.token;
    localStorage.setItem('token', jwtToken);
    setJwtToken(jwtToken);

    const decodedToken = jwt_decode(jwtToken);

    dispatch({
        type: AUTH_USER,
        payload: decodedToken
    });

};