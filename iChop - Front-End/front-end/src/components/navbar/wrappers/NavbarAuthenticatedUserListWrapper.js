import React, {Fragment} from "react";
import CreateReactClass from 'create-react-class';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";

var NavbarAuthenticatedUserListWrapper = CreateReactClass({
    render() {
        let username = '';
        let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);

        return (
            <div className="dropdown-menu dropdown-menu-right">

                <Link className="dropdown-item" to={profileUrl}>
                    <small>👤</small>
                    <span>Profile</span>
                </Link>

                <Link className="dropdown-item" to={RoutingURLs.USER.OPTIONS.INFORMATION}>
                    <small>⚙</small>
                    <span>Options</span>
                </Link>

                {this.props.children}

                <div className="dropdown-divider"/>
                <Link className="dropdown-item" to={RoutingURLs.AUTHENTICATION.LOGOUT}>
                    <small>🚪</small>
                    Logout
                </Link>
            </div>
        );
    }
});


export default NavbarAuthenticatedUserListWrapper;