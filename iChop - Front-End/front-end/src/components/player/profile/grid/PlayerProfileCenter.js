import React, {Component} from 'react';
import PlayerStatistics from "../PlayerStatistics";
import {Route, Switch} from "react-router-dom";
import RoutingURLs from "../../../../constants/routing/routing.constants";

class PlayerProfileCenter extends Component {


    render() {
        return (
            <div className="central-content">

                <Switch>
                    <Route exact path={RoutingURLs.PLAYER.PROFILE.VIEW} component={() => (<PlayerStatistics/>)}/>
                    <Route exact path={RoutingURLs.PLAYER.PROFILE.STATISTICS} component={() => (<PlayerStatistics/>)}/>
                </Switch>

            </div>
        );
    }

}

export default PlayerProfileCenter;