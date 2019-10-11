import React, {Component} from 'react';
import navbarGuestReduxHoc from "../../../../redux/hocs/navbar.guest.hoc";
import FormHoc from "../../../../hocs/form.hoc";

class GuestForgottenPasswordDropdown extends Component {


    constructor(props) {
        super(props);

        this.onForggotenPassword = this.onForggotenPassword.bind(this);
    }

    onForggotenPassword() {
        let {usernameOrEmail} = this.props.formData;

        console.log(usernameOrEmail);
    }

    render() {
        let {onChange} = this.props.formMethods;

        return (
            <div>
                <form className="px-4 py-3">
                    <div className="form-group">
                        <div className="input-group mb-2">

                            <div className="input-group-prepend">
                                <div className="input-group-text">👤/📧</div>
                            </div>
                            <input type="text" className="form-control"
                                   autoComplete="off"
                                   name="usernameOrEmail" placeholder="Username or Email..." onChange={onChange}/>
                        </div>
                    </div>

                    <button type="button"
                            className="btn btn-primary btn-sm" onClick={this.props.selectLogin}>Back
                    </button>
                    <button type="button" data-style="zoom-in"
                            className="btn btn-success btn-sm" onClick={this.onForggotenPassword}>Send
                    </button>

                    <div className="dropdown-divider"/>
                    <div>
                        <div className="alert alert-danger p-1">
                            <strong>⚡</strong> No user exists with the provided credentials!
                        </div>
                        <div className="alert alert-info p-1">
                            <strong>🔔</strong> Please wait!
                        </div>
                        <div className="alert alert-success p-1">
                            <strong>✔</strong> Password reset token has been sent to your email!
                        </div>
                    </div>
                </form>
            </div>
        );
    }

}

export default FormHoc(navbarGuestReduxHoc(GuestForgottenPasswordDropdown));