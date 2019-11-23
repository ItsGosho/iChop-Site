import React from "react";
import CreateReactClass from 'create-react-class';
import RoutingURLs from "../../../constants/routing/routing.constants";
import DropdownIconLink from "../../other/DropdownIconLink";
import {Link} from "react-router-dom";
import withState from "../../../hocs/with.state";
import PrefixURLs from "../../../constants/routing/prefix.routing.constants";

let NavbarAuthenticatedList = CreateReactClass({

    render() {
        let user = this.props.authenticatedUserInfo;
        let username = user.username;

        let profileUrl = RoutingURLs.USER.PROFILE.VIEW(username);
        let informationUrl = PrefixURLs.OPTIONS_PREFIX;
        let logoutUrl = RoutingURLs.AUTHENTICATION.LOGOUT;

        return (
            <div className="dropdown-menu dropdown-menu-right">

                <DropdownIconLink to={profileUrl} icon={'👤'} text={'Profile'}/>
                <DropdownIconLink to={informationUrl} icon={'⚙'} text={'Options'}/>

                {this.props.children}

                <div className="dropdown-divider"/>

                <DropdownIconLink to={logoutUrl} icon={'🚪'} text={'Logout'}/>
            </div>
        );
    }

});

export default withState(NavbarAuthenticatedList);