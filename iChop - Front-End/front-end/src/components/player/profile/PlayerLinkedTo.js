import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../constants/routing.constants";

class PlayerLinkedTo extends Component {


    render() {
        let linkedToUsername = 'ItsGosho';
        let userProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', linkedToUsername);

        return (
            <Fragment>
                {linkedToUsername !== null ? (
                    <div className="card site-user-username">
                        <div className="card-body site-user-username">
                            <div className="col-md-auto">
                                <div className="row site-user-title">

                                    <div className="col-md-auto site-user-title">Site User:</div>

                                    <div className="col-md-auto site-user-username">
                                        <span className="site-user-username">
                                            <Link to={userProfileUrl}>{linkedToUsername}</Link>
                                        </span>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                ) : null}
            </Fragment>
        );
    }

}

export default PlayerLinkedTo;