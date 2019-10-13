import React, {Component, Fragment} from 'react';
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import './UserOptionsMinecraft.css'

class UserOptionsMinecraft extends Component {

    constructor(props) {
        super(props);

        this.onUnlink = this.onUnlink.bind(this);
    }

    onUnlink() {
        console.log("Unlink Cliked!");
    }


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
                            <img src={skinUrl} className="skin" alt=''/>
                        </div>

                        <div className="dropdown-divider"/>

                        <div align="center" className="unlink-button-holder">

                            <button type="button"
                                    className="btn btn-warning"
                                    onClick={this.onUnlink}>
                                Unlink
                            </button>

                        </div>
                    </Fragment>)
                }
            </Fragment>
        );
    }

}

export default UserOptionsMinecraft;