import React, {Component} from 'react';
import './PlayerProfile.css'
import ServerRoutingURLs from "../../constants/server.routing.urls";
import PlayerStatistics from "./profile/PlayerStatistics";
import PlayerProfileNavigation from "./profile/PlayerProfileNavigation";
import PlayerLinkedTo from "./profile/PlayerLinkedTo";

class PlayerProfile extends Component {


    render() {
        let uuid = '8ed20904-3262-401a-901a-1946504d2eea';

        let bodyAvatarUrl = ServerRoutingURLs.OUTSIDE.CRAFATAR.MINECRAFT.SKIN.replace(':uuid', uuid);

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

                                    <PlayerProfileNavigation/>

                                </div>
                            </div>

                           <PlayerLinkedTo/>

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