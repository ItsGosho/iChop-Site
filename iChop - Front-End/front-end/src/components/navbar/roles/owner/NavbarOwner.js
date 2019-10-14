import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";
import '../NavbarAuthenticated.css'
import NavbarAuthenticatedButton from "../../other/NavbarAuthenticatedButton";
import NavbarAuthenticatedList from "../../other/NavbarAuthenticatedList";
import DropdownIconLink from "../../../other/DropdownIconLink";

class NavbarOwner extends Component {

    render() {

        return (
            <Fragment>
                <NavbarAuthenticatedButton/>

                <NavbarAuthenticatedList>

                    <DropdownIconLink to={RoutingURLs.THREAD.CREATE} icon={'🚩'} text={'Create Thread'}/>
                    <DropdownIconLink to={RoutingURLs.USER.ALL} icon={'👥'} text={'Users'}/>
                    <DropdownIconLink to={RoutingURLs.COMMENT.REPORT.ALL} icon={'⚠'} text={'Reports'}/>

                </NavbarAuthenticatedList>
            </Fragment>
        );
    }

}

export default NavbarOwner;