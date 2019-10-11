import React, {Component} from 'react';
import './NavbarGuest.css';
import GuestLoginDropdown from "./GuestLoginDropdown";
import GuestRegisterDropdown from "./GuestRegisterDropdown";
import GuestForgottenPasswordDropdown from "./GuestForgottenPasswordDropdown";
import navbarGuestReduxHoc from "../../../../redux/hocs/navbar.guest.hoc";

class NavbarGuest extends Component {

    constructor(props) {
        super(props);

        this.showDropdown = this.showDropdown.bind(this);
    }

    showDropdown() {
        let toShow = !this.props.redux.showDropdown;

        this.props.showDropdown(toShow);
    }

    render() {

        return (
            <div id="div-signIn-signInForms">

                <button id="button-signIn-navBar" className="btn btn-success btn-sm" onClick={this.showDropdown}>Sign in</button>

                {
                    (() => {
                        if (this.props.redux.showDropdown) {
                            return (
                                <div id="div-authForms-signInForm"
                                     className={"dropdown-menu dropdown-menu-right " + (this.props.showDropdown ? 'show' : '')}>
                                    {
                                        (() => {
                                            if (this.props.redux.isLoginSelected) {
                                                return (<GuestLoginDropdown/>);
                                            }

                                            if (this.props.redux.isRegisterSelected) {
                                                return (<GuestRegisterDropdown/>);
                                            }

                                            if (this.props.redux.isForgottenPasswordSelected) {
                                                return (<GuestForgottenPasswordDropdown/>);
                                            }
                                        })()
                                    }
                                </div>
                            );
                        }
                    })()
                }
            </div>
        );
    }

}

export default navbarGuestReduxHoc(NavbarGuest);