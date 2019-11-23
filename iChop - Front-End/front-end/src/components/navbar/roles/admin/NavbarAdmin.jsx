import React, {Fragment} from 'react';
import RoutingURLs from "../../../../constants/routing/routing.constants";
import '../NavbarAuthenticated.css'
import NavbarAuthenticatedButton from "../../components/NavbarAuthenticatedButton";
import NavbarAuthenticatedList from "../../components/NavbarAuthenticatedList";
import DropdownIconLink from "../../../other/DropdownIconLink";


const NavbarAdmin = () => (
    <Fragment>
        <NavbarAuthenticatedButton/>

        <NavbarAuthenticatedList>

            <DropdownIconLink to={RoutingURLs.THREAD.CREATE} icon={'🚩'} text={'Create Thread'}/>
            <DropdownIconLink to={RoutingURLs.USER.ALL} icon={'👥'} text={'Users'}/>
            <DropdownIconLink to={RoutingURLs.COMMENT.REPORT.ALL} icon={'⚠'} text={'Reports'}/>

        </NavbarAuthenticatedList>
    </Fragment>
);

export default NavbarAdmin;