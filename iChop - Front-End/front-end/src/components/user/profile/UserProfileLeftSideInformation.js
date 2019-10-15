import React, {Component} from 'react';
import ServerRoutingURLs from "../../../constants/server.routing.urls";
import SideInformationLastOnline from "./side/SideInformationLastOnline";
import SideInformationJoinedOn from "./side/SideInformationJoinedOn";
import SideInformationMessages from "./side/SideInformationMessages";
import SideInformationTotalLikes from "./side/SideInformationTotalLikes";
import SideInformationTotalDislikes from "./side/SideInformationTotalDislikes";
import SideInformationLocation from "./side/SideInformationLocation";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";
import './UserProfileLeftSideInformation.css'
import Image from "../../other/Image";
import SideInformationFollow from "./side/SideInformationFollow";

class UserProfileLeftSideInformation extends Component {


    render() {
        let username = 'ItsGosho';
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

                            <SideInformationLastOnline/>
                            <SideInformationJoinedOn/>
                            <SideInformationMessages/>

                        </div>
                    </div>


                    <div className="card reactions-given">
                        <div className="card-body reactions-given-body">

                            <SideInformationTotalLikes/>
                            <SideInformationTotalDislikes/>

                        </div>
                    </div>

                    <SideInformationFollow/>

                    <div className="card user-location-card">
                        <div className="card-body user-location-body">
                            <SideInformationLocation/>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default UserProfileLeftSideInformation;