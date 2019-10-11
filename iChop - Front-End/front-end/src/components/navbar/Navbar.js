import React, {Component} from 'react';
import Roles from "../../constants/roles.constants";
import UserNavbar from "./roles/user/UserNavbar";
import ModeratorNavbar from "./roles/moderator/ModeratorNavbar";
import AdminNavbar from "./roles/admin/AdminNavbar";
import OwnerNavbar from "./roles/owner/OwnerNavbar";
import GuestNavbar from "./roles/guest/GuestNavbar";
import {Link} from "react-router-dom";
import RoutingURLs from "../../constants/routing.constants";
import FrontEndResourcesRoutingURLs from "../../constants/front-end.resources.routings";

class Navbar extends Component {


    render() {
        let role = Roles.ADMIN;


        return (
            <div className="container" id="div-containter-top_nav_bar">

                <nav className="navbar navbar-expand-lg navbar-light bg-light rounded">

                    <img src={FrontEndResourcesRoutingURLs.NAVBAR.ICON} width="30" height="30"
                         className="d-inline-block align-top"
                         alt=""/>

                    <Link className="navbar-brand" to={RoutingURLs.HOME}>iChop</Link>

                    <button id="button-minimized-navbar" className="navbar-toggler" type="button"
                            data-toggle="collapse"
                            data-target="#navbar"
                            aria-controls="navbar" aria-expanded="false"
                            aria-label="Toggle Navigation Bar">
                        <span className="navbar-toggler-icon"/>
                    </button>

                    <div className="collapse navbar-collapse" id="navbar">
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