import React, {Component, Fragment} from 'react';
import FooterSideListWrapper from "../wrappers/FooterSideListWrapper";
import navbarGuestReduxHoc from "../../../redux/hocs/navbar.guest.hoc";
import LinkIconLi from "../../other/LinkIconLi";

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

                    <LinkIconLi icon={'🔐'} text={'Login'} onClick={this.onLoginRequired}/>
                    <LinkIconLi icon={'🔐'} text={'Login'} onClick={this.onLoginRequired}/>
                    <LinkIconLi icon={'🔐'} text={'Login'} onClick={this.onLoginRequired}/>

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