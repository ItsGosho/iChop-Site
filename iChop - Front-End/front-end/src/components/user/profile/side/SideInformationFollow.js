import React, {Component, Fragment} from 'react';
import ModalOpen from "../../../modal/ModalOpen";
import UserFollowingsModal from "../modals/UserFollowingsModal";
import UserFollowersModal from "../modals/UserFollowersModal";
import RoutingURLs from "../../../../constants/routing.constants";
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import {Link} from "react-router-dom";
import Image from "../../../other/Image";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";

class SideInformationFollow extends Component {


    render() {
        let totalFollowings = 7;
        let totalFollowers = 5;
        let followings = [{username: 'ItsGosho'}, {username: 'Roshko'}].slice(0, 4);
        let followers = [].slice(0, 4);

        return (
            <Fragment>
                <div className="card follow-card">

                    <div align="center">
                        <span>Following: {totalFollowings}</span>
                    </div>

                    <FollowInformation users={followings}/>

                    {totalFollowings > 4 ? (
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

                    {totalFollowers > 4 ? (
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
        <div className="row d-flex justify-content-center align-items-center"
             style={{'width': '100%', 'marginLeft': '0px'}}>
            {usersSliced.map((user, index) => {
                let {username} = user;
                let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);
                let avatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);

                return (
                    <Link to={profileUrl}>

                        <Image url={avatarUrl}
                               defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                               title={username}
                               style={{
                                   'width': '30px',
                                   'height': '30px',
                                   'marginLeft': '5px',
                                   'marginTop': '2px',
                                   'marginBottom': '2px'
                               }}/>
                    </Link>
                );
            })}
        </div>
    )
};