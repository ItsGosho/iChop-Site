import React, {Component} from 'react';
import navbarGuestReduxHoc from "../../../../redux/hocs/navbar.guest.hoc";
import FormHoc from "../../../../hocs/form.hoc";

class GuestRegisterDropdown extends Component {

    constructor(props) {
        super(props);

        this.onRegister = this.onRegister.bind(this);
    }

    onRegister() {
        let {username, password,confirmPassword,email} = this.props.formData;

        console.log(username);
        console.log(password);
        console.log(confirmPassword);
        console.log(email);
    }

    render() {
        let {onChange} = this.props.formMethods;

        return (
            <div id="div-register-dropdown">
                <form className="px-4 py-3">

                    <div className="form-group">
                        <small id="error-usernameAlreadyExists-registerForm">⚡Username already
                            exists!
                        </small>
                        <small id="error-usernameIsShort-registerForm">⚡Username must be at
                            least 3 characters long!
                        </small>
                        <div className="input-group mb-2">
                            <div className="input-group-prepend">
                                <div className="input-group-text">👤</div>
                            </div>
                            <input type="text" className="form-control" id="input-username-registerForm"
                                   name="username" placeholder="Username..." autoComplete="off" onChange={onChange}/>
                        </div>
                    </div>
                    <div className="form-group">
                        <div className="input-group mb-2">
                            <div className="input-group-prepend">
                                <div className="input-group-text">🔒</div>
                            </div>
                            <input type="password" className="form-control" id="input-password-registerForm"
                                   autoComplete="off"
                                   data-placement="top"
                                   data-toggle="popover" title="Password Requirements:" data-html="true"
                                   data-trigger="focus"
                                   data-content="-At least one uppercase character</br>-At least one lowercase character</br>-At least 6 characters"
                                   name="password" placeholder="Password..." onChange={onChange}/>
                            <div className="input-group-append">
                                <button className="btn btn-outline-success"
                                        id="button-showPassword-registerForm"
                                        type="button">Show
                                </button>
                            </div>
                        </div>
                    </div>
                    <div className="form-group">
                        <small id="error-passwordsDoesntMatch-registerForm">⚡Passwords doesn't match!
                        </small>
                        <div className="input-group mb-2">
                            <div className="input-group-prepend">
                                <div className="input-group-text">🔒</div>
                            </div>
                            <input type="password" className="form-control" id="input-confirmPassword-registerForm"
                                   autoComplete="off"
                                   name="confirmPassword" placeholder="Confirm Password..." onChange={onChange}/>
                        </div>
                    </div>
                    <div className="form-group">
                        <small id="error-emailAlreadyExists-registerForm">⚡Email already
                            exists!
                        </small>
                        <div className="input-group mb-2">
                            <div className="input-group-prepend">
                                <div className="input-group-text">📧</div>
                            </div>
                            <input type="email" className="form-control" id="input-email-registerForm" name="email"
                                   autoComplete="off"
                                   placeholder="Email..." onChange={onChange}/>
                        </div>
                    </div>
                    <button id="button-goToLogin-registerForm" type="button" onClick={this.props.selectLogin}
                            className="btn btn-primary btn-sm">Back
                    </button>
                    <button id="button-proceedRegister-registerForm" type="button"
                            className="btn btn-success btn-sm btn-ladda" data-style="zoom-in" onClick={this.onRegister}>
                        Register
                    </button>
                    <div className="dropdown-divider"/>
                </form>
            </div>
        );
    }

}

export default FormHoc(navbarGuestReduxHoc(GuestRegisterDropdown));