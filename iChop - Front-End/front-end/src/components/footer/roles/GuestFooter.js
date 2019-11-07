import React, {Component, Fragment} from 'react';
import FooterLinksWrapper from "../etc/FooterLinksWrapper";
import navbarGuestReduxHoc from "../../../redux/dispatchers/navbar.guest.dispatchers";
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

                <FooterLinksWrapper text={'VISIT'}>

                    <LinkIconLi icon={'🔐'} text={'Login'} onClick={this.onLoginRequired}/>
                    <LinkIconLi icon={'🗝️'} text={'Register'} onClick={this.onRegisterRequired}/>
                    <LinkIconLi icon={'🏷️'} text={'Forgotten Password'} onClick={this.onForgottenPasswordRequired}/>

                </FooterLinksWrapper>
            </Fragment>
        );
    }

}

export default navbarGuestReduxHoc(GuestFooter);