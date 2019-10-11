import React, {Component} from 'react';
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";
import '../AuthenticatedNavbar.css'
import NavbarAuthenticatedButton from "../../other/NavbarAuthenticatedButton";
import NavbarAuthenticatedUserListWrapper from "../../wrappers/NavbarAuthenticatedUserListWrapper";

class NavbarAdmin extends Component {


    render() {

        return (
            <div id="userDiv">

                <NavbarAuthenticatedButton/>

                <NavbarAuthenticatedUserListWrapper>

                    <Link className="dropdown-item" to={RoutingURLs.THREAD.CREATE}>
                        <small>🚩</small>
                        Create Thread
                    </Link>

                    <Link className="dropdown-item" to={RoutingURLs.USER.ALL}>
                        <small>👥</small>
                        Users
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

export default NavbarAdmin;