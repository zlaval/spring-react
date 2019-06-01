import {AUTH_USER} from "../action/types";

const INITIAL_STATE = {
    jwtToken: {},
    authenticated: false
};

const validToken = payload => {
    if (payload) {
        return true
    } else {
        return false
    }
};

export default function (state = INITIAL_STATE, action) {
    switch (action.type) {
        case AUTH_USER:
            return {...state, jwtToken: action.payload, authenticated: validToken(action.payload)};
        default:
            return state;
    }
}