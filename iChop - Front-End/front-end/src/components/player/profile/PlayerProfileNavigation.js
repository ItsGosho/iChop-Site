import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";

class PlayerProfileNavigation extends Component {


    render() {
        let uuid = '8ed20904-3262-401a-901a-1946504d2eea';
        let statisticsUrl = RoutingURLs.PLAYER.PROFILE.STATISTICS.replace(':uuid', uuid);

        return (
            <Fragment>
                <div className="col-md-auto">

                    <Link type="button"
                          to={statisticsUrl}
                          className="btn btn-warning btn-statistics">
                        Statistics
                    </Link>

                </div>

                <div className="col-md-auto">
                    <div className="row">

                    </div>
                </div>
            </Fragment>
        );
    }

}

export default PlayerProfileNavigation;