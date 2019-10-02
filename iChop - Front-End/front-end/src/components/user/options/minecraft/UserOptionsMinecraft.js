import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";
import ServerRoutingURLs from "../../../../constants/server.routing.urls";

class UserOptionsMinecraft extends Component {


    render() {
        let isAccountLinked = true;
        let playerName = 'ItsGosho';
        let uuid = '8ed20904-3262-401a-901a-1946504d2eea';
        let accountProfile = RoutingURLs.PLAYER.PROFILE.VIEW.replace(':uuid', uuid);
        let crafatarSkinUrl = ServerRoutingURLs.OUTSIDE.CRAFATAR.MINECRAFT.SKIN.replace(':uuid', uuid);

        return (
            <Fragment>

                {
                    (() => {
                        if (!isAccountLinked) {
                            return (
                                <Fragment>
                                    <div className="dropdown-divider"/>

                                    <div className="row" align="center">
                                        <div className="col-lg">
                                            <span>You have not linked account yet!</span>
                                        </div>
                                    </div>

                                    <div className="dropdown-divider"/>

                                    <div className="row" align="center">
                                        <div className="col-lg">
                                           <span>To link your account you must be logged in the site with the profile that you want to link your account
                                           ,then in the game type <span><b>/linkaccount</b></span> ,then a <span><b>link</b></span> will be generated ,click
                                               it and a page <span><b>in the site</b></span> will open
                                           with confirmation to link your account.Note that if you link your account you cant link another until <span><b>the
                                           previous is unlinked</b></span>.</span>
                                        </div>
                                    </div>

                                    <div className="dropdown-divider"/>
                                </Fragment>
                            );
                        } else {
                            return (
                                <Fragment>
                                    <div className="row" align="center">
                                        <div className="col-lg">
                                            <span>
                                                <b>
                                                    <Link to={accountProfile}>{playerName}</Link>
                                                </b>
                                            </span>
                                        </div>
                                    </div>

                                    <div className="dropdown-divider"/>

                                    <div align="center">
                                        <img src={crafatarSkinUrl} style={{'width': '95px', 'height': '200px'}} alt=''/>
                                    </div>

                                    <div className="dropdown-divider"/>

                                    <div align="center" style={{'marginTop': '10px'}}>
                                        <button type="button" className="btn btn-warning">Unlink</button>
                                    </div>
                                </Fragment>
                            );
                        }
                    })()
                }
            </Fragment>
        );
    }

}

export default UserOptionsMinecraft;