import React, {Component} from 'react';
import './PlayerLinkAccount.css'
import {Link} from "react-router-dom";
import RoutingURLs from "../../constants/routing.constants";
import ServerRoutingURLs from "../../constants/server.routing.urls";

class PlayerLinkAccount extends Component {

    constructor(props) {
        super(props);

        this.linkAccount = this.linkAccount.bind(this);
    }

    linkAccount() {
        /*TODO: Link the account to the current logged in user!*/
    }

    render() {
        let name = 'ItsGosho';
        let avatarUrl = ServerRoutingURLs.OUTSIDE.MINOTAR.MINECRAFT.HEAD.replace(':accountName', name);

        return (
            <div className="container d-flex justify-content-center align-items-center"
                 id="div-container-player_link_account">
                <div className="card" id="div-card-player_link_account">

                    <div className="card-header">
                        <span>Link your account:</span>
                    </div>

                    <div className="card-body">

                        <p className="card-text">Are you sure that you want to link
                            <img id="img-player_head-player_link_account" src={avatarUrl} alt=''/>
                            <b id="b-minecraft_name-player_link_account">{' ' + name}</b>
                            <span> to your account?</span>
                        </p>

                        <div className="float-right">
                            <form id="form-confirm_player_link_connection-player_link_account">
                                <button className="btn btn-warning" onClick={this.linkAccount}>Yes</button>
                            </form>
                        </div>

                        <div className="float-left">
                            <Link to={RoutingURLs.HOME}>
                                <button className="btn btn-danger">No</button>
                            </Link>
                        </div>

                    </div>

                </div>
            </div>
        );
    }

}

export default PlayerLinkAccount;