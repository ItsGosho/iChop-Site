import React, {Fragment} from 'react';
import '../NavbarAuthenticated.css'
import NavbarAuthenticatedButton from "../../components/NavbarAuthenticatedButton";
import NavbarAuthenticatedList from "../../components/NavbarAuthenticatedList";


const NavbarUser = () => (
    <Fragment>
        <NavbarAuthenticatedButton/>
        <NavbarAuthenticatedList/>
    </Fragment>
);

export default NavbarUser;