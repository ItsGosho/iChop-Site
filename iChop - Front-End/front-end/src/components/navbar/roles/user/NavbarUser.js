import React, {Component} from 'react';
import '../NavbarAuthenticated.css'
import NavbarAuthenticatedButton from "../../other/NavbarAuthenticatedButton";
import NavbarAuthenticatedList from "../../other/NavbarAuthenticatedList";

class NavbarUser extends Component {

    render() {

        return (
            <div>
                <NavbarAuthenticatedButton/>
                <NavbarAuthenticatedList/>
            </div>
        );
    }

}

export default NavbarUser;