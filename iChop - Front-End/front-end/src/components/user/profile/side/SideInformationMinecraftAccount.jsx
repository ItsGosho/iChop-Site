import React, {Fragment} from 'react';
import ServerRoutingURLs from "../../../../constants/routing/server.routing.urls";
import RoutingURLs from "../../../../constants/routing/routing.constants";
import {Link} from "react-router-dom";
import './SideInformationMinecraftAccount.css'


const SideInformationMinecraftAccount = () => {
    let uuid = 'fdsfs';
    let accountName = 'ItsGosho';

    return (
        <Fragment>
            {accountName != null ? (
                <div className="card card-minecraft-holder">
                    <div>
                        <img src={ServerRoutingURLs.OUTSIDE.MINOTAR.MINECRAFT.HEAD(accountName)} alt=''
                             className="avatar-minecraft-player"/>
                        <Link to={RoutingURLs.PLAYER.PROFILE.VIEW(uuid)}
                              className="username-minecraf-player">{accountName}</Link>
                    </div>
                </div>
            ) : null}
        </Fragment>
    )
};

export default SideInformationMinecraftAccount;