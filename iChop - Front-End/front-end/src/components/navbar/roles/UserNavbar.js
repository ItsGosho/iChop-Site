import React, {Component} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";
import './AuthenticatedNavbar.css'
import AuthenticatedNavbarButton from "../other/AuthenticatedNavbarButton";
import NavbarAuthenticatedUserListWrapper from "../wrappers/NavbarAuthenticatedUserListWrapper";

class UserNavbar extends Component {

    render() {
        let username = '';
        let profileUrl = RoutingURLs.USER.PROFILE.replace(':username', username);

        return (
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav ml-auto">
                    <li className="nav-item dropdown active">
                        <div id="userDiv">

                            <AuthenticatedNavbarButton/>

                            <NavbarAuthenticatedUserListWrapper/>
                        </div>
                    </li>
                </ul>
            </div>
        );
    }

}

export default UserNavbar;