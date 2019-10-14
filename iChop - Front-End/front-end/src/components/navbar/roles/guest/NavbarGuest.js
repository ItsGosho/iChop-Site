import React, {Component, Fragment} from 'react';
import './NavbarGuest.css';
import GuestLoginDropdown from "./GuestLogin";
import GuestRegisterDropdown from "./GuestRegister";
import GuestForgottenPasswordDropdown from "./GuestLostPassword";
import navbarGuestReduxHoc from "../../../../redux/hocs/navbar.guest.hoc";

class NavbarGuest extends Component {

    constructor(props) {
        super(props);

        this.showDropdown = this.showDropdown.bind(this);
        this.getDropdown = this.getDropdown.bind(this);
    }

    showDropdown() {
        let toShow = !this.props.redux.showDropdown;

        this.props.showDropdown(toShow);
    }

    getDropdown() {
        if (this.props.redux.isLoginSelected) {
            return (<GuestLoginDropdown/>);
        }

        if (this.props.redux.isRegisterSelected) {
            return (<GuestRegisterDropdown/>);
        }

        if (this.props.redux.isForgottenPasswordSelected) {
            return (<GuestForgottenPasswordDropdown/>);
        }
    }

    render() {

        return (
            <Fragment>

                <button type="button"
                        className="btn btn-success btn-sm"
                        onClick={this.showDropdown}>
                    Sign In
                </button>

                {this.props.redux.showDropdown ? (
                    <div
                        className={`dropdown-menu dropdown-menu-right guest-navbar-form ${this.props.showDropdown ? 'show' : ''}`}>
                        {this.getDropdown()}
                    </div>
                ) : null}

            </Fragment>
        );
    }

}

export default navbarGuestReduxHoc(NavbarGuest);