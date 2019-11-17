import React, {Component, Fragment} from 'react';
import '../NavbarAuthenticated.css'
import NavbarAuthenticatedButton from "../../other/NavbarAuthenticatedButton";
import NavbarAuthenticatedList from "../../other/NavbarAuthenticatedList";

class NavbarUser extends Component {

    render() {

        return (
            <Fragment>
                <NavbarAuthenticatedButton/>
                <NavbarAuthenticatedList/>
            </Fragment>
        );
    }

}

export default NavbarUser;