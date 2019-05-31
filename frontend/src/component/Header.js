import * as React from "react";
import {Link} from "react-router-dom";
import './Header.css'

export default class Header extends React.Component {

    render() {
        return (
            <div className="header">
                <Link to="/">Home</Link>
                <Link to="/register">Register</Link>
                <Link to="/login">Login</Link>
            </div>
        )
    }

}