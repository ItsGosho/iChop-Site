import React, {Component} from 'react';
import './PlayerProfile.css'
import PlayerProfileCenter from "./profile/grid/PlayerProfileCenter";
import PlayerProfileLeft from "./profile/grid/PlayerProfileLeft";

class PlayerProfile extends Component {


    render() {

        return (

            <div className="container">
                <div className="row">
                    <PlayerProfileLeft/>
                    <PlayerProfileCenter/>
                </div>
            </div>
        );
    }

}

export default PlayerProfile;