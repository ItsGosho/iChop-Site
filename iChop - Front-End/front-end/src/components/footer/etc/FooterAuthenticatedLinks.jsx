import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../constants/routing/routing.constants";
import LinkIconLi from "../../other/LinkIconLi";
import {connect} from "react-redux";
import formsDispatchers from "../../../redux/dispatchers/forms.dispatchers";
import PrefixURLs from "../../../constants/routing/prefix.routing.constants";

class FooterAuthenticatedLinks extends Component {


    render() {
        let {username} = this.props.authenticatedUserInfo;
        let myProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);

        return (
            <Fragment>

                <LinkIconLi url={myProfileUrl} icon={'👤'} text={'Profile'}/>
                <LinkIconLi url={PrefixURLs.OPTIONS_PREFIX} icon={'⚙'} text={'Options'}/>
                <LinkIconLi url={RoutingURLs.AUTHENTICATION.LOGOUT} icon={'🚪'} text={'Logout'}/>

            </Fragment>
        );
    }

}

let mapState = (state) => {
    return {...state};
};

export default connect(mapState,formsDispatchers)(FooterAuthenticatedLinks);