import React, {Component} from 'react';
import './PlayerProfile.css'
import ServerRoutingURLs from "../../constants/server.routing.urls";
import RoutingURLs from "../../constants/routing.constants";
import {Link} from "react-router-dom";
import PlayerStatistics from "./profile/PlayerStatistics";

class PlayerProfile extends Component {


    render() {
        let linkedToUsername = 'ItsGosho';
        let uuid = '8ed20904-3262-401a-901a-1946504d2eea';

        let bodyAvatarUrl = ServerRoutingURLs.OUTSIDE.CRAFATAR.MINECRAFT.SKIN.replace(':uuid', uuid);
        let userProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', linkedToUsername);

        return (
            <div className="container">

                <div className="row">

                    <div className="col-md-auto">
                        <div className="col-md-sm">

                            <div align="center">
                                <img src={bodyAvatarUrl}
                                     alt=''
                                     className="img-player_body-player_profile"/>
                            </div>


                            <div className="card menu">
                                <div className="card-body menu">

                                    <div className="col-md-auto">
                                        <Link to={''} className="btn btn-warning btn-statistics" type="button">Statistics
                                        </Link>
                                    </div>

                                    <div className="col-md-auto">
                                        <div className="row">

                                        </div>
                                    </div>

                                </div>
                            </div>

                            {
                                (() => {
                                    if (linkedToUsername != null) {
                                        return (
                                            <div className="card site-user-username">
                                                <div className="card-body site-user-username">
                                                    <div className="col-md-auto">
                                                        <div className="row site-user-title">

                                                            <div className="col-md-auto site-user-title">
                                                                Site User:
                                                            </div>

                                                            <div className="col-md-auto site-user-username">
                                                                <span className="site-user-username">
                                                                    <Link to={userProfileUrl}>{linkedToUsername}</Link>
                                                                </span>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        );
                                    }
                                })()
                            }

                        </div>
                    </div>

                    <div className="central-content">

                        <PlayerStatistics/>

                    </div>
                </div>
            </div>
        );
    }

}

export default PlayerProfile;