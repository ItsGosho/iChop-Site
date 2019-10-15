import React, {Component, Fragment} from 'react';
import ModalOpen from "../../../modal/ModalOpen";
import UserFollowingsModal from "../modals/UserFollowingsModal";
import UserFollowersModal from "../modals/UserFollowersModal";
import RoutingURLs from "../../../../constants/routing.constants";
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import {Link} from "react-router-dom";

class SideInformationFollow extends Component {


    render() {
        let totalFollowings = 7;
        let totalFollowers = 5;
        let followings = [].slice(0, 4);
        let followers = [].slice(0, 4);

        return (
            <Fragment>
                <div className="card follow-card">

                    <div align="center">
                        <span>Following: {totalFollowings}</span>
                    </div>

                    <div>
                        <div className="row d-flex justify-content-center align-items-center"
                             style={{'width': '100%', 'marginLeft': '0px'}}>

                            {
                                (() => {
                                    followings.map((following, index) => {
                                        let {username} = following;
                                        let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);
                                        let avatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);

                                        return (
                                            <Link to={profileUrl}>
                                                <img key={index}
                                                     src={avatarUrl}
                                                     className="img-user-avatar"
                                                     title={username}
                                                     onError={this.onUserAvatarError}
                                                     alt=''
                                                     style={{
                                                         'width': '30px',
                                                         'height': '30px',
                                                         'marginLeft': '5px',
                                                         'marginTop': '2px',
                                                         'marginBottom': '2px'
                                                     }}/>
                                            </Link>
                                        );
                                    })
                                })()
                            }
                        </div>
                    </div>


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

                    <div>
                        <div className="row d-flex justify-content-center align-items-center"
                             style={{'width': '100%', 'marginLeft': '0px'}}>

                            {
                                (() => {
                                    followers.map((follower, index) => {
                                        let {username} = follower;
                                        let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);
                                        let avatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);

                                        return (
                                            <Link to={profileUrl}>
                                                <img key={index}
                                                     src={avatarUrl}
                                                     className="img-user-avatar"
                                                     title={username}
                                                     onError={this.onUserAvatarError}
                                                     alt=''
                                                     style={{
                                                         'width': '30px',
                                                         'height': '30px',
                                                         'marginLeft': '5px',
                                                         'marginTop': '2px',
                                                         'marginBottom': '2px'
                                                     }}/>
                                            </Link>
                                        );
                                    })
                                })()
                            }
                        </div>
                    </div>

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