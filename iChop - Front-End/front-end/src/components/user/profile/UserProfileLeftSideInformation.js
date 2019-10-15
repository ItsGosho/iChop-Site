import React, {Component} from 'react';
import ServerRoutingURLs from "../../../constants/server.routing.urls";
import SideInformationLastOnline from "./side/SideInformationLastOnline";
import SideInformationJoinedOn from "./side/SideInformationJoinedOn";
import SideInformationMessages from "./side/SideInformationMessages";
import SideInformationTotalLikes from "./side/SideInformationTotalLikes";
import SideInformationTotalDislikes from "./side/SideInformationTotalDislikes";
import SideInformationLocation from "./side/SideInformationLocation";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";
import SideInformationFollowings from "./side/SideInformationFollowings";
import SideInformationFollowers from "./side/SideInformationFollowers";
import './UserProfileLeftSideInformation.css'
import Image from "../../other/Image";
import UserFollowingsModal from "./modals/UserFollowingsModal";
import UserFollowersModal from "./modals/UserFollowersModal";
import ModalOpen from "../../modal/ModalOpen";

class UserProfileLeftSideInformation extends Component {


    render() {
        let username = 'ItsGosho';
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);
        let totalFollowings = 7;
        let totalFollowers = 5;

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

                    <div className="card following-card">

                        <div align="center">
                            <span>Following: {totalFollowings}</span>
                        </div>

                        <div>
                            <SideInformationFollowings/>
                        </div>


                        {totalFollowings > 4 ? (
                            <a href="#" className="d-flex justify-content-center align-items-center"
                               data-toggle="modal"
                               data-target=".modal-all-following">See all</a>
                        ) : null}

                        <UserFollowingsModal/>

                    </div>

                    <div className="card followers-card">

                        <div align="center">
                            <span>Followers: {totalFollowers}</span>
                        </div>

                        <div>
                            <SideInformationFollowers/>
                        </div>

                        {totalFollowers > 4 ? (
                            <ModalOpen relationTo="all-followers">
                                <a href='#'>See all</a>
                            </ModalOpen>
                        ) : null}

                        <UserFollowersModal/>

                    </div>

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