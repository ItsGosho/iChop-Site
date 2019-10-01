import React, {Component} from 'react';
import Roles from "../../constants/roles.constants";
import UserFooter from "./roles/UserFooter";
import ModeratorFooter from "./roles/ModeratorFooter";
import AdminFooter from "./roles/AdminFooter";
import OwnerFooter from "./roles/OwnerFooter";
import GuestFooter from "./roles/GuestFooter";
import FooterCopyright from "./etc/FooterCopyright";
import FooterHeader from "./etc/FooterHeader";

class Footer extends Component {


    render() {
        let role = Roles.USER;

        return (
            <footer className="page-footer font-small stylish-color-dark pt-4" style={{'marginTop': '125px'}}>

                <div className="container text-center text-md-left">
                    <div className="row">
                        <FooterHeader/>

                        <div className="col-md-2 mx-auto">
                            <h5 className="font-weight-bold text-uppercase mt-3 mb-4">VISIT</h5>
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
                        </div>

                    </div>

                </div>

                <FooterCopyright/>
            </footer>
        );
    }

}

export default Footer;