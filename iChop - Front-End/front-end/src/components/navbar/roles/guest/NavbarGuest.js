import React, {Component, Fragment} from 'react';
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
            <Fragment>

                <button className="btn btn-success btn-sm" onClick={this.showDropdown}>Sign in</button>

                {
                    (() => {
                        if (this.props.redux.showDropdown) {
                            return (
                                <div
                                    className={"dropdown-menu dropdown-menu-right guest-navbar-form " + (this.props.showDropdown ? 'show' : '')}>
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
            </Fragment>
        );
    }

}

export default navbarGuestReduxHoc(NavbarGuest);