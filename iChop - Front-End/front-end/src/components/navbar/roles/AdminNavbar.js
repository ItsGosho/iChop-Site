import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";
import './AuthenticatedNavbar.css'
import AuthenticatedNavbarButton from "../other/AuthenticatedNavbarButton";
import NavbarAuthenticatedUserListWrapper from "../wrappers/NavbarAuthenticatedUserListWrapper";

class AdminNavbar extends Component {


    render() {
        let username = '';
        let profileUrl = RoutingURLs.USER.PROFILE.replace(':username', username);

        return (
            <Fragment>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav ml-auto">
                        <li className="nav-item dropdown active">
                            <div id="userDiv">

                                <AuthenticatedNavbarButton/>

                                <NavbarAuthenticatedUserListWrapper>
                                    <Link className="dropdown-item" to={RoutingURLs.THREAD.CREATE}>
                                        <small>🚩</small>
                                        Create Thread</Link>
                                    <Link className="dropdown-item" to={RoutingURLs.USER.ALL}>
                                        <small>👥</small>
                                        Users</Link>
                                    <Link className="dropdown-item" to={RoutingURLs.COMMENT.REPORTS_ALL}>
                                        <small>⚠</small>
                                        Reports</Link>
                                </NavbarAuthenticatedUserListWrapper>

                            </div>
                        </li>
                    </ul>
                </div>
            </Fragment>
        );
    }

}

export default AdminNavbar;