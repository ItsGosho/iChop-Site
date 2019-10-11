import React, {Component} from 'react';
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";
import '../NavbarAuthenticated.css'
import NavbarAuthenticatedButton from "../../other/NavbarAuthenticatedButton";
import NavbarAuthenticatedUserListWrapper from "../../wrappers/NavbarAuthenticatedUserListWrapper";

class NavbarOwner extends Component {


    render() {

        return (
            <div id="userDiv">

                <NavbarAuthenticatedButton/>

                <NavbarAuthenticatedUserListWrapper>

                    <Link className="dropdown-item" to={RoutingURLs.THREAD.CREATE}>
                        <small>🚩</small>
                        <span>Create Thread</span>
                    </Link>

                    <Link className="dropdown-item" to={RoutingURLs.USER.ALL}>
                        <small>👥</small>
                        <span>All Users</span>
                    </Link>

                    <Link className="dropdown-item" to={RoutingURLs.COMMENT.REPORT.ALL}>
                        <small>⚠</small>
                        <span>Reports</span>
                    </Link>

                </NavbarAuthenticatedUserListWrapper>

            </div>
        );
    }

}

export default NavbarOwner;