import * as React from "react";
import {Field, reduxForm} from "redux-form";
import {signup} from "../action";
import {connect} from "react-redux";
import {compose} from "redux";


class Register extends React.Component {

    onSubmit = (form) => {
        console.log(form)
        this.props.signup(form, () => {
            this.props.history.push("/")
        })
    }

    render() {

        const {handleSubmit} = this.props

        return (
            <div>
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
                        <label>Name</label>
                        <Field
                            name="fullName"
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
                    <button>Signup</button>

                </form>


            </div>
        )
    }
}

export default compose(
    connect(null, {signup}),
    reduxForm({form: 'signup'})
)(Register)