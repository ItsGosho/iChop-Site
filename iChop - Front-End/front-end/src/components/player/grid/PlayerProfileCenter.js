import React,{Component} from 'react';
import PlayerStatistics from "../profile/PlayerStatistics";

class PlayerProfileCenter extends Component {


    render() {

        return (
            <div className="central-content">

                <PlayerStatistics/>

            </div>
        );
    }

}

export default PlayerProfileCenter;