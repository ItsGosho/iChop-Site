import React, {Component} from 'react';
import './Footer.css'
import Roles from "../../constants/enums/roles.constants";
import UserFooter from "./roles/UserFooter";
import ModeratorFooter from "./roles/ModeratorFooter";
import AdminFooter from "./roles/AdminFooter";
import OwnerFooter from "./roles/OwnerFooter";
import GuestFooter from "./roles/GuestFooter";
import FooterCopyright from "./etc/FooterCopyright";
import FooterHeader from "./etc/FooterHeader";
import withState from "../../hocs/with.state";

class Footer extends Component {

    constructor(props) {
        super(props);

        this.getFooter = this.getFooter.bind(this);
    }

    getFooter(role) {
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
    }

    render() {
        let user = this.props.authenticatedUserInfo;

        return (
            <footer className="page-footer font-small stylish-color-dark pt-4">
                <div className="container text-center text-md-left">
                    <div className="row">

                        <div className="col-md-4 mx-auto">
                            <FooterHeader/>
                        </div>

                        {this.getFooter(user.authority)}

                    </div>
                </div>
                <FooterCopyright/>
            </footer>
        );
    }

}

export default withState(Footer);