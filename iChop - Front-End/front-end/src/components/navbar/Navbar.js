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
import {Link} from "react-router-dom";
import RoutingURLs from "../../constants/routing.constants";

class Navbar extends Component {


    render() {
        let role = Roles.MODERATOR;


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