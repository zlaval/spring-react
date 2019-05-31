import * as React from "react";
import {Field, reduxForm} from "redux-form";
import {compose} from "redux";
import {connect} from "react-redux";
import {signin} from "../action";

class Login extends React.Component {

    onSubmit = (form) => {
        this.props.signin(form, () => {
            this.props.history.push('/');
        });
    };

    render() {
        const {handleSubmit} = this.props;
        return (
            <form onSubmit={handleSubmit(this.onSubmit)}>
                <fieldset>
                    <label>Email</label>
                    <Field
                        name="username"
                        type="text"
                        component="input"
                        autoComplete="none"
                    />
                </fieldset>
                <fieldset>
                    <label>Password</label>
                    <Field
                        name="password"
                        type="password"
                        component="input"
                        autoComplete="none"
                    />
                </fieldset>
                <button>Login</button>
            </form>
        )

    }
}

export default compose(
    connect(null, {signin}),
    reduxForm({form: 'signin'})
)(Login)