import React, {Component, Fragment} from 'react';
import FooterLinksWrapper from "../etc/FooterLinksWrapper";
import LinkIconLi from "../../other/LinkIconLi";
import formsDispatchers from "../../../redux/dispatchers/forms.dispatchers";
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

        this.props.showGuestDropdown(true);
        this.props.selectGuestLogin();
    }

    onRegisterRequired(event) {
        event.preventDefault();

        this.props.showGuestDropdown(true);
        this.props.selectGuestRegister();
    }

    onForgottenPasswordRequired(event) {
        event.preventDefault();

        this.props.showGuestDropdown(true);
        this.props.selectGuestForgottenPassword();
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

export default connect(mapState,formsDispatchers)(GuestFooter);