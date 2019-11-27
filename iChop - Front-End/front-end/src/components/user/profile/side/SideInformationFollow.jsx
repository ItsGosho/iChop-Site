import React, {Fragment} from 'react';
import ModalOpen from "../../../modal/ModalOpen";
import UserFollowingsModal from "../modals/UserFollowingsModal";
import UserFollowersModal from "../modals/UserFollowersModal";
import RoutingURLs from "../../../../constants/routing/routing.constants";
import ServerRoutingURLs from "../../../../constants/routing/server.routing.urls";
import {Link} from "react-router-dom";
import Image from "../../../other/Image";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import './SideInformationFollow.css'
import withState from "../../../../hocs/with.state";


const MAX_FOLLOW_SHOW = 4;

const SideInformationFollow = (props) => {
    let {followings, followers} = props.userProfileInfo;

    let totalFollowings = followings.length;
    let totalFollowers = followers.length;

    return (
        <Fragment>
            <div className="card follow-card">

                <div align="center">
                    <span>Following: {totalFollowings}</span>
                </div>

                <FollowInformation users={followings.slice(0, MAX_FOLLOW_SHOW)}/>

                {totalFollowings > MAX_FOLLOW_SHOW ? (
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

                <FollowInformation users={followers.slice(0, MAX_FOLLOW_SHOW)}/>

                {totalFollowers > MAX_FOLLOW_SHOW ? (
                    <ModalOpen relationTo="all-followers">
                        <a href='#'>See all</a>
                    </ModalOpen>
                ) : null}

                <UserFollowersModal/>

            </div>
        </Fragment>
    );
};


export default withState(SideInformationFollow);

const FollowInformation = ({users}) => {
    let usersSliced = users.slice(0, MAX_FOLLOW_SHOW);

    return (
        <div className="row d-flex justify-content-center align-items-center">
            {usersSliced.map(({id,username}, index) => {
                let profileUrl = RoutingURLs.USER.PROFILE.VIEW(username);
                let avatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET(username);

                return (
                    <div key={id}>
                        <Link to={profileUrl}>

                            <Image url={avatarUrl}
                                   defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                                   title={username}
                                   className="follow-image"/>
                        </Link>
                    </div>
                );
            })}
        </div>
    )
};