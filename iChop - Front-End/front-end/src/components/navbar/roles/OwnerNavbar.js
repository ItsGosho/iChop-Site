import React, {Component} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";
import './AuthenticatedNavbar.css'
import AuthenticatedNavbarButton from "../other/AuthenticatedNavbarButton";

class OwnerNavbar extends Component {


    render() {
        let username = '';
        let profileUrl = RoutingURLs.USER.PROFILE.replace(':username', username);

        return (
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item dropdown active">
                        <div id="userDiv">

                            <AuthenticatedNavbarButton/>

                            <div className="dropdown-menu dropdown-menu-right">
                                <Link className="dropdown-item" to={profileUrl}>
                                    <small>👤</small>
                                    <span>Profile</span></Link>
                                <Link className="dropdown-item" to={RoutingURLs.USER.PROFILE_OPTIONS_INFORMATION}>
                                    <small>⚙</small>
                                    <span>Options</span></Link>
                                <Link className="dropdown-item" to={RoutingURLs.THREAD.CREATE}>
                                    <small>🚩</small>
                                    Create Thread</Link>
                                <Link className="dropdown-item" to={RoutingURLs.USER.ALL}>
                                    <small>👥</small>
                                    All Users</Link>
                                <Link className="dropdown-item" to={RoutingURLs.COMMENT.REPORTS_ALL}>
                                    <small>⚠</small>
                                    Reports</Link>
                                <div className="dropdown-divider"/>
                                <Link className="dropdown-item" to={RoutingURLs.AUTHENTICATION.LOGOUT}>
                                    <small>🚪</small>
                                    Logout</Link>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        );
    }

}

export default OwnerNavbar;