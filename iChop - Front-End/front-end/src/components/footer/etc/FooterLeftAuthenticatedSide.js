import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";

class FooterLeftAuthenticatedSide extends Component {


    render() {
        let username = '';
        let myProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);

        return (
            <Fragment>
                <li>
                    <Link to={myProfileUrl}>
                        <small>👤</small>
                        <span>Profile</span></Link>
                </li>
                <li>
                    <Link to={RoutingURLs.USER.OPTIONS.INFORMATION}>
                        <small>⚙</small>
                        <span>Options</span></Link>
                </li>
                <li>
                    <Link to={RoutingURLs.AUTHENTICATION.LOGOUT}>
                        <small>🚪</small>
                        Logout</Link>
                </li>
            </Fragment>
        );
    }

}

export default FooterLeftAuthenticatedSide;