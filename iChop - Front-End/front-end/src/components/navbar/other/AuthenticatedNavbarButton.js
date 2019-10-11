import React, {Fragment, Component} from "react";
import Image from "../../other/Image";
import ServerRoutingURLs from "../../../constants/server.routing.urls";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";

class AuthenticatedNavbarButton extends Component {


    /*TODO:Да споделя решението си тук:*/

    /*https://stackoverflow.com/questions/980855/inputting-a-default-image-in-case-the-src-attribute-of-an-html-img-is-not-vali*/

    render() {
        let userProfilePictureUrl = '';

        return (
            <Fragment>
                <button type="button" className="btn btn-success dropdown-toggle btn-sm"
                        data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">

                    <Image url={userProfilePictureUrl} defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR} className={'img-user_avatar-top_nav_bar'}/>

                    <span>⚙</span>
                </button>
            </Fragment>
        );
    }
};


export default AuthenticatedNavbarButton;