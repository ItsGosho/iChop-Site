import React, {Fragment} from 'react';
import RoutingURLs from "../../../constants/routing/routing.constants";
import LinkIconLi from "../../other/LinkIconLi";
import {connect} from "react-redux";
import formsDispatchers from "../../../redux/dispatchers/forms.dispatchers";
import PrefixURLs from "../../../constants/routing/prefix.routing.constants";

const FooterAuthenticatedLinks = (props) => {
    let {username} = props.authenticatedUserInfo;
    let profileUrl = RoutingURLs.USER.PROFILE.VIEW(username);

    return (
        <Fragment>

            <LinkIconLi url={profileUrl} icon={'👤'} text={'Profile'}/>
            <LinkIconLi url={PrefixURLs.OPTIONS_PREFIX} icon={'⚙'} text={'Options'}/>
            <LinkIconLi url={RoutingURLs.AUTHENTICATION.LOGOUT} icon={'🚪'} text={'Logout'}/>

        </Fragment>
    )
};

export default connect((state) => ({...state}), formsDispatchers)(FooterAuthenticatedLinks);