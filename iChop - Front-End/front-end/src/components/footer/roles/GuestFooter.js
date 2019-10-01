import React, {Component, Fragment} from 'react';
import FooterSideListWrapper from "../wrappers/FooterSideListWrapper";
import navbarGuestReduxHoc from "../../../redux/hocs/navbar.guest.hoc";

class GuestFooter extends Component {

    constructor(props) {
        super(props);

        this.onLoginRequired = this.onLoginRequired.bind(this);
        this.onRegisterRequired = this.onRegisterRequired.bind(this);
        this.onForgottenPasswordRequired = this.onForgottenPasswordRequired.bind(this);
    }


    onLoginRequired(event) {
       event.preventDefault();

       this.props.showDropdown(true);
       this.props.selectLogin();
    }

    onRegisterRequired(event) {
        event.preventDefault();

        this.props.showDropdown(true);
        this.props.selectRegister();
    }

    onForgottenPasswordRequired(event) {
        event.preventDefault();

        this.props.showDropdown(true);
        this.props.selectForgottenPassword();
    }

    render() {

        return (
            <Fragment>

                <FooterSideListWrapper text={'VISIT'}>
                    <li>
                        <a onClick={this.onLoginRequired} href=' '>
                            <small>🔐</small>
                            Login</a>
                    </li>
                    <li>
                        <a onClick={this.onRegisterRequired} href=' '>
                            <small>🗝️</small>
                            Register</a>
                    </li>
                    <li>
                        <a onClick={this.onForgottenPasswordRequired} href=' '>
                            <small>🏷️</small>
                            Forgotten Password</a>
                    </li>

                </FooterSideListWrapper>
            </Fragment>
        );
    }

}

export default navbarGuestReduxHoc(GuestFooter);