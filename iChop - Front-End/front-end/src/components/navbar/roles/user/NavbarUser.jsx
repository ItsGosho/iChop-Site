import React, {Component, Fragment} from 'react';
import '../NavbarAuthenticated.css'
import NavbarAuthenticatedButton from "../../components/NavbarAuthenticatedButton";
import NavbarAuthenticatedList from "../../components/NavbarAuthenticatedList";

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