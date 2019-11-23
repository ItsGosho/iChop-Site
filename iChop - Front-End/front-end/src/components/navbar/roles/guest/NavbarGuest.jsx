import React, {Component, Fragment} from 'react';
import './NavbarGuest.css';
import GuestLoginDropdown from "./GuestLogin";
import GuestRegisterDropdown from "./GuestRegister";
import GuestForgottenPasswordDropdown from "./GuestLostPassword";
import formsDispatchers from "../../../../redux/dispatchers/forms.dispatchers";
import withDispatcher from "../../../../hocs/with.dispatcher";

class NavbarGuest extends Component {

    constructor(props) {
        super(props);

        this.showDropdown = this.showDropdown.bind(this);
        this.getDropdown = this.getDropdown.bind(this);
    }

    showDropdown() {
        let {isDropdownShow} = this.props.forms;

        this.props.showGuestDropdown(!isDropdownShow);
    }

    getDropdown() {
        let {isLoginSelected, isRegisterSelected, isForgottenPasswordSelected} = this.props.forms;

        if (isLoginSelected) {
            return (<GuestLoginDropdown/>);
        }

        if (isRegisterSelected) {
            return (<GuestRegisterDropdown/>);
        }

        if (isForgottenPasswordSelected) {
            return (<GuestForgottenPasswordDropdown/>);
        }
    }

    render() {
        let {isDropdownShow} = this.props.forms;

        return (
            <Fragment>
                <button type="button" className="btn btn-success btn-sm" onClick={this.showDropdown}>Sign In</button>

                {isDropdownShow ? (
                    <div
                        className={`dropdown-menu dropdown-menu-right guest-navbar-form ${isDropdownShow ? 'show' : ''}`}>
                        {this.getDropdown()}
                    </div>
                ) : null}

            </Fragment>
        );
    }

}

export default withDispatcher(formsDispatchers)(NavbarGuest);