import React, {Component, Fragment} from 'react';
import ModalOpen from "../../../modal/ModalOpen";
import UserFollowingsModal from "../modals/UserFollowingsModal";
import UserFollowersModal from "../modals/UserFollowersModal";
import RoutingURLs from "../../../../constants/routing.constants";
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import {Link} from "react-router-dom";
import Image from "../../../other/Image";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import './SideInformationFollow.css'

class SideInformationFollow extends Component {


    render() {
        let totalFollowings = 7;
        let totalFollowers = 5;

        let maxFollowShow = 4;
        let followings = [{username: 'ItsGosho'}, {username: 'Roshko'}].slice(0, maxFollowShow);
        let followers = [{username: 'Qncho'}].slice(0, maxFollowShow);

        return (
            <Fragment>
                <div className="card follow-card">

                    <div align="center">
                        <span>Following: {totalFollowings}</span>
                    </div>

                    <FollowInformation users={followings}/>

                    {totalFollowings > maxFollowShow ? (
                        <ModalOpen relationTo="all-followings">
                            <a href='#'>See all</a>
                        </ModalOpen>
                    ) : null}

                    <UserFollowingsModal/>

                </div>

                <div className="card follow-card">

                    <div align="center">
                        <span>Followers: {totalFollowers}</span>
                    </div>

                    <FollowInformation users={followers}/>

                    {totalFollowers > maxFollowShow ? (
                        <ModalOpen relationTo="all-followers">
                            <a href='#'>See all</a>
                        </ModalOpen>
                    ) : null}

                    <UserFollowersModal/>

                </div>
            </Fragment>
        );
    }

}

export default SideInformationFollow;

const FollowInformation = (props) => {
    let {users} = props;

    let usersSliced = users.slice(0, 4);

    return (
        <div className="row d-flex justify-content-center align-items-center">
            {usersSliced.map((user, index) => {
                let {username} = user;
                let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);
                let avatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);

                return (
                    <Link to={profileUrl}>

                        <Image url={avatarUrl}
                               defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                               title={username}
                               className="follow-image"/>
                    </Link>
                );
            })}
        </div>
    )
};