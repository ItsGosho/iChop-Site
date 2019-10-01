import React,{Component} from 'react';

class GuestLoginDropdown extends Component {


    render() {

        return (
            <div id="div-login-dropdown">
                <form className="px-4 py-3" method="post" action="/login">
                    <div className="form-group" id="formGroup-usernameOrEmail-loginForm">
                        <div className="input-group mb-2">
                            <div className="input-group-prepend">
                                <div className="input-group-text">👤/📧</div>
                            </div>
                            <input type="text" className="form-control" id="input-usernameOrEmail-loginForm"
                                   autoComplete="off"
                                   name="usernameOrEmail" placeholder="Username or Email..."/>
                        </div>
                    </div>
                    <div className="form-group">
                        <div className="input-group mb-2">
                            <div className="input-group-prepend">
                                <div className="input-group-text">🔒</div>
                            </div>
                            <input type="password" className="form-control" id="input-password-loginForm"
                                   autoComplete="off"
                                   name="password" placeholder="Password..."/>
                        </div>
                    </div>
                    <small id="error-invalidCredentials-loginForm">⚡Invalid credentials :/
                    </small>
                    <div className="custom-control custom-checkbox">
                        <input type="checkbox" name="rememberMe" className="custom-control-input"
                               id="input-rememberMe-loginForm"/>
                            <label className="custom-control-label" htmlFor="input-rememberMe-loginForm">
                                Remember me
                            </label>
                    </div>

                    <input type="hidden" id="input-user_location-loginDropdown" name="userLocation"/>

                        <button type="submit" data-style="zoom-in" className="btn btn-primary">Login</button>
                        <div className="dropdown-divider"></div>
                </form>
                <a id="button-goToRegister-loginForm" className="dropdown-item">
                    <button type="button" className="btn btn-success btn-sm">Register</button>
                </a>
                <a id="button-goToForgottenPassword-loginForm" className="dropdown-item">
                    <button className="btn btn-warning btn-sm">Forgotten Password</button>
                </a>
            </div>
        );
    }

}

export default GuestLoginDropdown;