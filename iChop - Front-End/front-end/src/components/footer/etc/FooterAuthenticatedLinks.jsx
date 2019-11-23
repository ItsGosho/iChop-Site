import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../constants/routing/routing.constants";
import LinkIconLi from "../../other/LinkIconLi";
import {connect} from "react-redux";
import formsDispatchers from "../../../redux/dispatchers/forms.dispatchers";
import PrefixURLs from "../../../constants/routing/prefix.routing.constants";

class FooterAuthenticatedLinks extends Component {

    render() {
        let {username: authenticatedUsername} = this.props.authenticatedUserInfo;
        let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', authenticatedUsername);

        return (
            <Fragment>

                <LinkIconLi url={profileUrl} icon={'👤'} text={'Profile'}/>
                <LinkIconLi url={PrefixURLs.OPTIONS_PREFIX} icon={'⚙'} text={'Options'}/>
                <LinkIconLi url={RoutingURLs.AUTHENTICATION.LOGOUT} icon={'🚪'} text={'Logout'}/>

            </Fragment>
        );
    }

}

export default connect((state) => ({...state}), formsDispatchers)(FooterAuthenticatedLinks);