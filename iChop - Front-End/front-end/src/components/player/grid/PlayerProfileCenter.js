import React, {Component} from 'react';
import PlayerStatistics from "../profile/PlayerStatistics";
import {Route, Switch} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";

class PlayerProfileCenter extends Component {


    render() {
        let uuid = '8ed20904-3262-401a-901a-1946504d2eea';

        return (
            <div className="central-content">

                <Switch>
                    <Route exact path={RoutingURLs.PLAYER.PROFILE.VIEW.replace(':uuid',uuid)} component={() => (<PlayerStatistics/>)}/>
                    <Route exact path={RoutingURLs.PLAYER.PROFILE.STATISTICS} component={() => (<PlayerStatistics/>)}/>
                </Switch>

            </div>
        );
    }

}

export default PlayerProfileCenter;