import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";
import '../NavbarAuthenticated.css'
import NavbarAuthenticatedButton from "../../other/NavbarAuthenticatedButton";
import NavbarAuthenticatedList from "../../other/NavbarAuthenticatedList";

class NavbarModerator extends Component {


    render() {

        return (
            <Fragment>

                <NavbarAuthenticatedButton/>

                <NavbarAuthenticatedList>

                    <Link className="dropdown-item" to={RoutingURLs.THREAD.CREATE}>
                        <small>🚩</small>
                        <span>Create Thread</span>
                    </Link>

                    <Link className="dropdown-item" to={RoutingURLs.COMMENT.REPORT.ALL}>
                        <small>⚠</small>
                        <span>Reports</span>
                    </Link>

                </NavbarAuthenticatedList>

            </Fragment>
        );
    }

}

export default NavbarModerator;