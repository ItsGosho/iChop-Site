import React, {Component} from 'react';
import Roles from "../../constants/roles.constants";
import NavbarUser from "./roles/user/NavbarUser";
import NavbarModerator from "./roles/moderator/NavbarModerator";
import NavbarAdmin from "./roles/admin/NavbarAdmin";
import NavbarOwner from "./roles/owner/NavbarOwner";
import GuestNavbar from "./roles/guest/NavbarGuest";
import {Link} from "react-router-dom";
import RoutingURLs from "../../constants/routing.constants";
import FrontEndResourcesRoutingURLs from "../../constants/front-end.resources.routings";

class Navbar extends Component {
    
    render() {
        let role = Roles.ADMIN;


        return (
            <div className="container">
                <nav className="navbar navbar-expand-lg navbar-light bg-light rounded">

                    <img src={FrontEndResourcesRoutingURLs.NAVBAR.ICON}
                         width="30"
                         height="30"
                         className="d-inline-block align-top"
                         alt=""/>

                    <Link className="navbar-brand" to={RoutingURLs.HOME}>iChop</Link>

                    <button type="button"
                            className="navbar-toggler"
                            data-toggle="collapse"
                            data-target="#core-navigation-bar"
                            aria-controls="navbar" aria-expanded="false"
                            aria-label="Toggle Navigation Bar">
                        <span className="navbar-toggler-icon"/>
                    </button>

                    <div className="collapse navbar-collapse" id="core-navigation-bar">
                        <ul className="navbar-nav ml-auto">
                            <li className="nav-item dropdown active">
                                {
                                    (() => {
                                        switch (role) {
                                            case Roles.USER:
                                                return (<NavbarUser/>);
                                            case Roles.MODERATOR:
                                                return (<NavbarModerator/>);
                                            case Roles.ADMIN:
                                                return (<NavbarAdmin/>);
                                            case Roles.OWNER:
                                                return (<NavbarOwner/>);
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