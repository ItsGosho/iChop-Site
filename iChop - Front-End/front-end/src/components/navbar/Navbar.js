import React, {Component} from 'react';
import Roles from "../../constants/roles.constants";
import UserFooter from "../footer/roles/UserFooter";
import ModeratorFooter from "../footer/roles/ModeratorFooter";
import AdminFooter from "../footer/roles/AdminFooter";
import OwnerFooter from "../footer/roles/OwnerFooter";
import GuestFooter from "../footer/roles/GuestFooter";
import UserNavbar from "./roles/UserNavbar";
import ModeratorNavbar from "./roles/ModeratorNavbar";
import AdminNavbar from "./roles/AdminNavbar";
import OwnerNavbar from "./roles/OwnerNavbar";
import GuestNavbar from "./roles/GuestNavbar";

class Navbar extends Component {


    render() {
        let role = Roles.OWNER;

        return (
            <div className="container" id="div-containter-top_nav_bar">

                <nav className="navbar navbar-expand-lg navbar-light bg-light rounded">

                    <img src="/res/img/navbar-icon.png" width="30" height="30" className="d-inline-block align-top"
                         alt=""/>
                    <a className="navbar-brand" href="/">iChop</a>
                    <button id="button-minimized-navbar" className="navbar-toggler" type="button"
                            data-toggle="collapse"
                            data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"/>
                    </button>

                    {
                        (() => {
                            switch (role) {
                                case Roles.USER:
                                    return (<UserNavbar/>);
                                case Roles.MODERATOR:
                                    return (<ModeratorNavbar/>);
                                case Roles.ADMIN:
                                    return (<AdminNavbar/>);
                                case Roles.OWNER:
                                    return (<OwnerNavbar/>);
                                default:
                                    return (<GuestNavbar/>);
                            }
                        })()
                    }

                </nav>
            </div>
        );
    }

}

export default Navbar;