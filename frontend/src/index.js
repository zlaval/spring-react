import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import * as serviceWorker from './serviceWorker';
import {applyMiddleware, compose, createStore} from "redux";
import reducers from './reducer'
import thunk from 'redux-thunk'
import {Provider} from "react-redux";
import {BrowserRouter, Route} from 'react-router-dom'
import App from "./App";
import Home from "./component/Home";
import Register from "./component/Register";
import Login from "./component/Login";
import setJwtToken from "./utils/setJwtToken";
import jwt_decode from "jwt-decode"
import {AUTH_USER} from "./action/types";

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;


const store = createStore(
    reducers,
    composeEnhancers(
        applyMiddleware(thunk)
    )
);

const token = localStorage.getItem("token")
if (token) {
    const decoded = jwt_decode(token);
    const currentTime = Date.now() / 1000;
    if (currentTime > decoded.exp) {
        window.location.href = "/login"
        localStorage.removeItem("token")
        setJwtToken(null)
        store.dispatch({
            type: AUTH_USER,
            payload: {}
        })
    } else {
        setJwtToken(token)
        store.dispatch({
            type: AUTH_USER,
            payload: decoded
        })
    }


}

ReactDOM.render(
    <Provider store={store}>
        <BrowserRouter>
            <App>
                <Route path="/register" component={Register}/>
                <Route path="/login" component={Login}/>
                <Route path="/" exact={true} component={Home}/>
            </App>
        </BrowserRouter>
    </Provider>
    , document.getElementById('root'));

serviceWorker.unregister();
