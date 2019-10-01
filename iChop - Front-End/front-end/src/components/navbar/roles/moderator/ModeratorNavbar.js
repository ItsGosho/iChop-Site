import React, {Component} from 'react';
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";
import '../AuthenticatedNavbar.css'
import AuthenticatedNavbarButton from "../../other/AuthenticatedNavbarButton";
import NavbarAuthenticatedUserListWrapper from "../../wrappers/NavbarAuthenticatedUserListWrapper";

class ModeratorNavbar extends Component {


    render() {

        return (
            <div id="userDiv">

                <AuthenticatedNavbarButton/>

                <NavbarAuthenticatedUserListWrapper>

                    <Link className="dropdown-item" to={RoutingURLs.THREAD.CREATE}>
                        <small>🚩</small>
                        Create Thread
                    </Link>

                    <Link className="dropdown-item" to={RoutingURLs.COMMENT.REPORT.ALL}>
                        <small>⚠</small>
                        Reports
                    </Link>

                </NavbarAuthenticatedUserListWrapper>

            </div>
        );
    }

}

export default ModeratorNavbar;