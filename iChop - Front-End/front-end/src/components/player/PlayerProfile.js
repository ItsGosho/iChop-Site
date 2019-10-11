import React, {Component} from 'react';
import './PlayerProfile.css'
import PlayerProfileCenter from "./grid/PlayerProfileCenter";
import PlayerProfileLeft from "./grid/PlayerProfileLeft";

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