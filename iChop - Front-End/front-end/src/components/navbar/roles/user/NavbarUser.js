import React, {Component} from 'react';
import '../AuthenticatedNavbar.css'
import NavbarAuthenticatedButton from "../../other/NavbarAuthenticatedButton";
import NavbarAuthenticatedUserListWrapper from "../../wrappers/NavbarAuthenticatedUserListWrapper";

class NavbarUser extends Component {

    render() {

        return (
            <div id="userDiv">
                <NavbarAuthenticatedButton/>
                <NavbarAuthenticatedUserListWrapper/>
            </div>
        );
    }

}

export default NavbarUser;