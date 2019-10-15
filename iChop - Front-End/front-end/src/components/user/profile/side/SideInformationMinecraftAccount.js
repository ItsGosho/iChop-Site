import React, {Component, Fragment} from 'react';
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";
import './SideInformationMinecraftAccount.css'

class SideInformationMinecraftAccount extends Component {

    render() {
        let uuid = 'fdsfs';
        let accountName = 'ItsGosho';
        let headAvatarUrl = ServerRoutingURLs.OUTSIDE.MINOTAR.MINECRAFT.HEAD.replace(':accountName', accountName);
        let accountProfileUrl = RoutingURLs.PLAYER.PROFILE.VIEW.replace(':uuid', uuid);

        return (
            <Fragment>
                {accountName != null ? (
                    <div className="card card-minecraft-holder">
                        <div>
                            <img
                                src={headAvatarUrl}
                                alt=''
                                className="avatar-minecraft-player"/>
                            <Link to={accountProfileUrl} className="username-minecraf-player">
                                {accountName}
                            </Link>
                        </div>
                    </div>
                ) : null}
            </Fragment>
        )
    }
}

export default SideInformationMinecraftAccount;