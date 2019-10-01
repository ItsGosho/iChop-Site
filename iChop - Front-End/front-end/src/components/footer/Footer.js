import React, {Component} from 'react';
import Roles from "../../constants/roles.constants";
import UserFooter from "./roles/UserFooter";
import ModeratorFooter from "./roles/ModeratorFooter";
import AdminFooter from "./roles/AdminFooter";
import OwnerFooter from "./roles/OwnerFooter";
import GuestFooter from "./roles/GuestFooter";

class CHANGE extends Component {


    render() {

        let role = '';

        return (
            <footer className="page-footer font-small stylish-color-dark pt-4" style="margin-top: 125px">

                <div className="container text-center text-md-left">
                    <div className="row">
                        <div className="col-md-4 mx-auto">
                            <h5 className="font-weight-bold text-uppercase mt-3 mb-4">iChop - The Minecraft Server</h5>
                            <p>
                                Welcome to our website ,here you can find the latest news
                                about something new ,to link your account with your in-game account and
                                so many other things!
                            </p>

                        </div>

                        <hr className="clearfix w-100 d-md-none">

                            {
                                (() => {
                                    switch (role) {
                                        case Roles.USER:
                                            return (<UserFooter/>);
                                        case Roles.MODERATOR:
                                            return (<ModeratorFooter/>);
                                        case Roles.ADMIN:
                                            return (<AdminFooter/>);
                                        case Roles.OWNER:
                                            return (<OwnerFooter/>);
                                        default:
                                            return (<GuestFooter/>);
                                    }
                                })()
                            }

                        </hr>
                    </div>

                </div>
            </footer>
        );
    }

}

export default CHANGE;