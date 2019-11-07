import React from "react";
import CreateReactClass from 'create-react-class';
import RoutingURLs from "../../../constants/routing.constants";
import DropdownIconLink from "../../other/DropdownIconLink";
import {Link} from "react-router-dom";

let NavbarAuthenticatedList = CreateReactClass({

    render() {
        let username = '';

        let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);
        let informationUrl = RoutingURLs.USER.OPTIONS.INFORMATION;
        let logoutUrl = RoutingURLs.AUTHENTICATION.LOGOUT;

        return (
            <div className="dropdown-menu dropdown-menu-right">

                <DropdownIconLink to={profileUrl} icon={'👤'} text={username}/>
                <DropdownIconLink to={informationUrl} icon={'⚙'} text={'Options'}/>

                {this.props.children}

                <div className="dropdown-divider"/>

                <DropdownIconLink to={logoutUrl} icon={'🚪'} text={'Logout'}/>
            </div>
        );
    }

});

export default NavbarAuthenticatedList;