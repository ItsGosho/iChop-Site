import React, {Component, Fragment} from 'react';
import './NavbarGuest.css';
import GuestLoginDropdown from "./GuestLogin";
import GuestRegisterDropdown from "./GuestRegister";
import GuestForgottenPasswordDropdown from "./GuestLostPassword";
import {compose} from "redux";
import {connect} from "react-redux";
import formsDispatchers from "../../../../redux/dispatchers/forms.dispatchers";

class NavbarGuest extends Component {

    constructor(props) {
        super(props);

        this.showDropdown = this.showDropdown.bind(this);
        this.getDropdown = this.getDropdown.bind(this);
    }

    showDropdown() {
        let toShow = !this.props.isDropdownShow;

        this.props.showGuestDropdown(toShow);
    }

    getDropdown() {
        if (this.props.isLoginSelected) {
            return (<GuestLoginDropdown/>);
        }

        if (this.props.isRegisterSelected) {
            return (<GuestRegisterDropdown/>);
        }

        if (this.props.isForgottenPasswordSelected) {
            return (<GuestForgottenPasswordDropdown/>);
        }
    }

    render() {
        return (
            <Fragment>

                <button type="button"
                        className="btn btn-success btn-sm"
                        onClick={this.showGuestDropdown}>
                    Sign In
                </button>

                {this.props.isDropdownShow ? (
                    <div
                        className={`dropdown-menu dropdown-menu-right guest-navbar-form ${this.props.showGuestDropdown ? 'show' : ''}`}>
                        {this.getDropdown()}
                    </div>
                ) : null}

            </Fragment>
        );
    }

}


let mapState = (states) => {
    return {...states.forms}
};

export default compose(connect(mapState, formsDispatchers))(NavbarGuest)