import React, {Component} from 'react';
import RoutingURLs from "../../../constants/routing.constants";
import {Link} from "react-router-dom";
import './AuthenticatedNavbar.css'
import AuthenticatedNavbarButton from "../other/AuthenticatedNavbarButton";
import NavbarAuthenticatedUserListWrapper from "../wrappers/NavbarAuthenticatedUserListWrapper";

class OwnerNavbar extends Component {


    render() {

        return (
            <div id="userDiv">

                <AuthenticatedNavbarButton/>

                <NavbarAuthenticatedUserListWrapper>

                    <Link className="dropdown-item" to={RoutingURLs.THREAD.CREATE}>
                        <small>🚩</small>
                        Create Thread
                    </Link>

                    <Link className="dropdown-item" to={RoutingURLs.USER.ALL}>
                        <small>👥</small>
                        All Users
                    </Link>

                    <Link className="dropdown-item" to={RoutingURLs.COMMENT.REPORTS_ALL}>
                        <small>⚠</small>
                        Reports
                    </Link>

                </NavbarAuthenticatedUserListWrapper>

            </div>
        );
    }

}

export default OwnerNavbar;