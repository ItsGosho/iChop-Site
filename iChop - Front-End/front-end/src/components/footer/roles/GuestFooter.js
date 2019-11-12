import React, {Component, Fragment} from 'react';
import FooterLinksWrapper from "../etc/FooterLinksWrapper";
import LinkIconLi from "../../other/LinkIconLi";
import navbarGuestDispatchers from "../../../redux/dispatchers/navbar.guest.dispatchers";
import {connect} from "react-redux";

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

let mapState = (state) => {
    return {...state};
};

export default connect(mapState,navbarGuestDispatchers)(GuestFooter);