import React, {Component} from 'react';
import Roles from "../../constants/roles.constants";
import UserFooter from "./roles/UserFooter";
import ModeratorFooter from "./roles/ModeratorFooter";
import AdminFooter from "./roles/AdminFooter";
import OwnerFooter from "./roles/OwnerFooter";
import GuestFooter from "./roles/GuestFooter";
import FooterCopyright from "./etc/FooterCopyright";
import FooterHeader from "./etc/FooterHeader";

class CHANGE extends Component {


    render() {

        let role = '';

        return (
            <footer className="page-footer font-small stylish-color-dark pt-4" style={{'margin-top':'125px'}}>

                <div className="container text-center text-md-left">
                    <div className="row">
                        <FooterHeader/>

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

                <FooterCopyright/>
            </footer>
        );
    }

}

export default CHANGE;