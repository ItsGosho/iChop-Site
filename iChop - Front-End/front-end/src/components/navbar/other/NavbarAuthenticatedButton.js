import React, {Component} from "react";
import Image from "../../other/Image";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";

class NavbarAuthenticatedButton extends Component {


    /*TODO:Да споделя решението си тук:*/

    /*https://stackoverflow.com/questions/980855/inputting-a-default-image-in-case-the-src-attribute-of-an-html-img-is-not-vali*/

    render() {
        let userProfilePictureUrl = '';

        return (
            <button type="button" className="btn btn-success dropdown-toggle btn-sm"
                    data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">

                <Image url={userProfilePictureUrl} defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                       className={'img-user_avatar-top_nav_bar'}/>

                <span>⚙</span>
            </button>
        );
    }
};


export default NavbarAuthenticatedButton;