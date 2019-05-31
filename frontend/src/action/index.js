import axios from "axios";
import {SIGNUP} from "./types";


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