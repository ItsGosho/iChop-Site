import React, {Fragment} from 'react';
import FooterLinksWrapper from "../components/FooterLinksWrapper";
import LinkIconLi from "../../other/LinkIconLi";
import formsDispatchers from "../../../redux/dispatchers/forms.dispatchers";
import {connect} from "react-redux";

const GuestFooter = () => (
    <Fragment>
        <FooterLinksWrapper text={'VISIT'}>

            <LinkIconLi icon={'🔐'} text={'Login'} onClick={onLoginRequired}/>
            <LinkIconLi icon={'🗝️'} text={'Register'} onClick={onRegisterRequired}/>
            <LinkIconLi icon={'🏷️'} text={'Forgotten Password'} onClick={onForgottenPasswordRequired}/>

        </FooterLinksWrapper>
    </Fragment>
);

export default connect((state) => ({...state}), formsDispatchers)(GuestFooter);

const onDropdownEvent = (event) => {
    event.preventDefault();

    this.props.showGuestDropdown(true);
};

const onLoginRequired = (event) => {
    this.onDropdownEvent(event);
    this.props.selectGuestLogin();
};

const onRegisterRequired = (event) => {
    this.onDropdownEvent(event);
    this.props.selectGuestRegister();
};

const onForgottenPasswordRequired = (event) => {
    this.onDropdownEvent(event);
    this.props.selectGuestForgottenPassword();
};