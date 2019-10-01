import React, {Component} from 'react';
import './GuestNavbar.css';
import GuestLoginDropdown from "./GuestLoginDropdown";
import GuestRegisterDropdown from "./GuestRegisterDropdown";
import GuestForgottenPasswordDropdown from "./GuestForgottenPasswordDropdown";

class GuestNavbar extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoginSelected: true,
            isRegisterSelected: false,
            isForgottenPasswordSelected: false,
        }
    }

    /*TODO: Here redux will play BIG role*/

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
                      <GuestLoginDropdown/>
                </div>
            </div>
        );
    }

}

export default GuestNavbar;