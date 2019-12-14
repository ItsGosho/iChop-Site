import React, {Component, Fragment} from 'react';
import FooterLinksWrapper from "../components/FooterLinksWrapper";
import LinkIconLi from "../../other/LinkIconLi";
import formsDispatchers from "../../../redux/dispatchers/forms.dispatchers";
import withDispatcher from "../../../hocs/with.dispatcher";

class GuestFooter extends Component {

    onDropdownEvent = (event) => {
        event.preventDefault();
        this.props.showGuestDropdown(true);
    };

    onLoginRequired = (event) => {
        this.onRequire(event, this.props.selectGuestLogin)
    };

    onRegisterRequired = (event) => {
        this.onRequire(event, this.props.selectGuestRegister)
    };

    onForgottenPasswordRequired = (event) => {
        this.onRequire(event, this.props.selectGuestForgottenPassword)
    };

    onRequire(event, action) {
        this.onDropdownEvent(event);
        action();
        window.scrollTo(0, 0);
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

export default withDispatcher(formsDispatchers)(GuestFooter);