import React, {Component} from 'react';
import navbarGuestReduxHoc from "../../../../redux/hocs/navbar.guest.hoc";
import FormHoc from "../../../../hocs/form.hoc";

class GuestLoginDropdown extends Component {

    constructor(props) {
        super(props);

        this.onLogin = this.onLogin.bind(this);
    }

    onLogin() {
        let {usernameOrEmail, password} = this.props.formData;

        console.log(usernameOrEmail);
        console.log(password);
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

                    <div className="form-group">
                        <div className="input-group mb-2">
                            <div className="input-group-prepend">
                                <div className="input-group-text">🔒</div>
                            </div>
                            <input type="password" className="form-control"
                                   autoComplete="off"
                                   name="password" placeholder="Password..." onChange={onChange}/>
                        </div>
                    </div>

                    <small>⚡Invalid credentials :/</small>

                    <div className="custom-control custom-checkbox">
                        <input type="checkbox" name="rememberMe" className="custom-control-input"/>
                        <label className="custom-control-label" htmlFor="input-rememberMe-loginForm">
                            Remember me
                        </label>
                    </div>

                    <input type="hidden"  name="userLocation"/>

                    <button type="button" data-style="zoom-in" className="btn btn-primary" onClick={this.onLogin}>Login</button>

                    <div className="dropdown-divider"/>
                </form>

                <a className="dropdown-item">
                    <button type="button" onClick={this.props.selectRegister}
                            className="btn btn-success btn-sm">Register
                    </button>
                </a>

                <a  className="dropdown-item">
                    <button className="btn btn-warning btn-sm" onClick={this.props.selectForgottenPassword}>Forgotten
                        Password
                    </button>
                </a>
            </div>
        );
    }

}

export default FormHoc(navbarGuestReduxHoc(GuestLoginDropdown));