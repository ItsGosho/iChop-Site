import React from 'react';
import './Footer.css'
import Roles from "../../constants/enums/roles.constants";
import UserFooter from "./roles/UserFooter";
import ModeratorFooter from "./roles/ModeratorFooter";
import AdminFooter from "./roles/AdminFooter";
import OwnerFooter from "./roles/OwnerFooter";
import GuestFooter from "./roles/GuestFooter";
import FooterCopyright from "./components/FooterCopyright";
import FooterHeader from "./components/FooterHeader";
import withDispatchers from "../../hocs/with.dispatchers";

const Footer = (props) => {
    let {authority} = props.authenticatedUserInfo;

    return (
        <footer className="page-footer font-small stylish-color-dark pt-4">
            <div className="container text-center text-md-left">
                <div className="row">

                    <div className="col-md-4 mx-auto">
                        <FooterHeader/>
                    </div>

                    {getFooter(authority)}

                </div>
            </div>
            <FooterCopyright/>
        </footer>
    );
};

export default withDispatchers(Footer);

const getFooter = (authority) => {
    switch (authority) {
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
};