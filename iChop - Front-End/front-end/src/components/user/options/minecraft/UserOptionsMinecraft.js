import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";
import ServerRoutingURLs from "../../../../constants/server.routing.urls";

class UserOptionsMinecraft extends Component {


    render() {
        let isAccountLinked = true;

        let uuid = '8ed20904-3262-401a-901a-1946504d2eea';
        let player = 'ItsGosho';
        let profileUrl = RoutingURLs.PLAYER.PROFILE.VIEW.replace(':uuid', uuid);
        let skinUrl = ServerRoutingURLs.OUTSIDE.CRAFATAR.MINECRAFT.SKIN.replace(':uuid', uuid);

        return (
            <Fragment>
                {!isAccountLinked ? (<Fragment>
                        <div className="dropdown-divider"/>

                        <div className="row" align="center">
                            <div className="col-lg">
                                <span>You have not linked account yet!</span>
                            </div>
                        </div>

                        <div className="dropdown-divider"/>

                        <div className="row">
                            <div className="col-lg">
                                <br></br>
                                <span>I.Type in the server <b>/linkaccount</b>.</span>
                                <br></br>
                                <span>II.Open the generated <b>link</b>.</span>
                                <br></br>
                                <span>III.Then <b>confirm</b> that you want to link your account.</span>
                            </div>
                        </div>

                        <div className="dropdown-divider"/>
                    </Fragment>) :
                    (<Fragment>
                        <div className="row" align="center">
                            <div className="col-lg">
                                <b><Link to={profileUrl}>{player}</Link></b>
                            </div>
                        </div>

                        <div className="dropdown-divider"/>

                        <div align="center">
                            <img src={skinUrl} style={{'width': '95px', 'height': '200px'}} alt=''/>
                        </div>

                        <div className="dropdown-divider"/>

                        <div align="center" style={{'marginTop': '10px'}}>
                            <button type="button" className="btn btn-warning">Unlink</button>
                        </div>
                    </Fragment>)}
            </Fragment>
        );
    }

}

export default UserOptionsMinecraft;