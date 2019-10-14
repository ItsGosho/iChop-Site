import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";
import '../NavbarAuthenticated.css'
import NavbarAuthenticatedButton from "../../other/NavbarAuthenticatedButton";
import NavbarAuthenticatedList from "../../other/NavbarAuthenticatedList";
import DropdownIconLink from "../../../other/DropdownIconLink";

class NavbarModerator extends Component {


    render() {

        return (
            <Fragment>

                <NavbarAuthenticatedButton/>

                <NavbarAuthenticatedList>

                    <DropdownIconLink to={RoutingURLs.THREAD.CREATE} icon={'🚩'} text={'Create Thread'}/>
                    <DropdownIconLink to={RoutingURLs.COMMENT.REPORT.ALL} icon={'⚠'} text={'Reports'}/>

                </NavbarAuthenticatedList>

            </Fragment>
        );
    }

}

export default NavbarModerator;