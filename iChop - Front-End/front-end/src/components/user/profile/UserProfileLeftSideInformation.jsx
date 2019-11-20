import React, {Component} from 'react';
import PropTypes from 'prop-types';
import ServerRoutingURLs from "../../../constants/routing/server.routing.urls";
import SideInformationLocation from "./side/SideInformationLocation";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";
import './UserProfileLeftSideInformation.css'
import Image from "../../other/Image";
import SideInformationFollow from "./side/SideInformationFollow";
import SideInformationReactions from "./side/SideInformationReactions";
import SideInformationMinecraftAccount from "./side/SideInformationMinecraftAccount";
import SideInformationUser from "./side/SideInformationUser";
import withState from "../../../hocs/with.state";

class UserProfileLeftSideInformation extends Component {


    render() {
        let {username} = this.props.userProfileInfo;

        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);

        return (
            <div className="col-md-auto">
                <div className="col-md-sm user-info">

                    <div align="center">
                        <Image url={userAvatarUrl}
                               defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                               className="img-user-avatar"/>
                    </div>

                    <div className="card user-base-info">
                        <div className="card-body user-base-info-body">

                            <SideInformationUser/>

                        </div>
                    </div>

                    <SideInformationMinecraftAccount/>

                    <div className="card reactions-given">
                        <div className="card-body reactions-given-body">

                            <SideInformationReactions/>

                        </div>
                    </div>

                    <SideInformationFollow/>
                    <SideInformationLocation/>
                </div>
            </div>
        );
    }
}

export default withState(UserProfileLeftSideInformation);