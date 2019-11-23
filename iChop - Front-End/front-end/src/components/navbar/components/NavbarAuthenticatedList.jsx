import React from "react";
import RoutingURLs from "../../../constants/routing/routing.constants";
import DropdownIconLink from "../../other/DropdownIconLink";
import withState from "../../../hocs/with.state";
import PrefixURLs from "../../../constants/routing/prefix.routing.constants";

const NavbarAuthenticatedList = (props) => {
    let {username} = props.authenticatedUserInfo;
    let {children} = props;

    return (
        <div className="dropdown-menu dropdown-menu-right">

            <DropdownIconLink to={RoutingURLs.USER.PROFILE.VIEW(username)} icon={'👤'} text={'Profile'}/>
            <DropdownIconLink to={PrefixURLs.OPTIONS_PREFIX} icon={'⚙'} text={'Options'}/>

            {children}

            <div className="dropdown-divider"/>

            <DropdownIconLink to={RoutingURLs.AUTHENTICATION.LOGOUT} icon={'🚪'} text={'Logout'}/>
        </div>
    );
};

export default withState(NavbarAuthenticatedList);