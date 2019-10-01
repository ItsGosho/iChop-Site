import React, {Component} from 'react';
import Roles from "../../constants/roles.constants";
import UserNavbar from "./roles/user/UserNavbar";
import ModeratorNavbar from "./roles/moderator/ModeratorNavbar";
import AdminNavbar from "./roles/admin/AdminNavbar";
import OwnerNavbar from "./roles/owner/OwnerNavbar";
import GuestNavbar from "./roles/guest/GuestNavbar";
import {Link} from "react-router-dom";
import RoutingURLs from "../../constants/routing.constants";

class Navbar extends Component {


    render() {
        let role = '';


        return (
            <div className="container" id="div-containter-top_nav_bar">

                <nav className="navbar navbar-expand-lg navbar-light bg-light rounded">

                    <img src="/img/navbar-icon.png" width="30" height="30" className="d-inline-block align-top"
                         alt=""/>

                    <Link className="navbar-brand" to={RoutingURLs.HOME}>iChop</Link>

                    <button id="button-minimized-navbar" className="navbar-toggler" type="button"
                            data-toggle="collapse"
                            data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"/>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav ml-auto">
                            <li className="nav-item dropdown active">
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
                            </li>
                        </ul>
                    </div>

                </nav>
            </div>
        );
    }

}

export default Navbar;