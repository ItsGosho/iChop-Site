import React, {Component} from 'react';
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import PlayerProfileNavigation from "../PlayerProfileNavigation";
import PlayerLinkedTo from "../PlayerLinkedTo";

class PlayerProfileLeft extends Component {


    render() {
        let uuid = '8ed20904-3262-401a-901a-1946504d2eea';

        let bodyAvatarUrl = ServerRoutingURLs.OUTSIDE.CRAFATAR.MINECRAFT.SKIN.replace(':uuid', uuid);

        return (
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
        );
    }

}

export default PlayerProfileLeft;