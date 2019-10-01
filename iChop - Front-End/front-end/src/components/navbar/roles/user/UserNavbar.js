import React, {Component} from 'react';
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";
import '../AuthenticatedNavbar.css'
import AuthenticatedNavbarButton from "../../other/AuthenticatedNavbarButton";
import NavbarAuthenticatedUserListWrapper from "../../wrappers/NavbarAuthenticatedUserListWrapper";

class UserNavbar extends Component {

    render() {

        return (
            <div id="userDiv">
                <AuthenticatedNavbarButton/>
                <NavbarAuthenticatedUserListWrapper/>
            </div>
        );
    }

}

export default UserNavbar;