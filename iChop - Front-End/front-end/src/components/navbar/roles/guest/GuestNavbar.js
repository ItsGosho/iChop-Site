import React, {Component} from 'react';
import './GuestNavbar.css';

class GuestNavbar extends Component {


    render() {

        return (
            <div id="div-signIn-signInForms">
                <button id="button-signIn-navBar" type="button"
                        className="btn btn-success dropdown-toggle btn-sm"
                        data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    Sign in
                </button>
                <div id="div-authForms-signInForm" className="dropdown-menu dropdown-menu-right">
                    <th:block th:insert="auth/login-dropdown.html"></th:block>


                    <th:block th:insert="auth/register-dropdown.html"></th:block>


                    <th:block th:insert="auth/forgotten_password-dropdown.html"></th:block>
                </div>
            </div>
        );
    }

}

export default GuestNavbar;