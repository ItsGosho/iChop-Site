import React, {Fragment} from 'react';
import ServerRoutingURLs from "../../../../constants/routing/server.routing.urls";
import RoutingURLs from "../../../../constants/routing/routing.constants";
import {Link} from "react-router-dom";
import './SideInformationMinecraftAccount.css'
import withState from "../../../../hocs/with.state";


const SideInformationMinecraftAccount = (props) => {
    let {playerName,playerUUID} = props.userProfileInfo;

    return (
        <Fragment>
            {playerName ? (
                <div className="card card-minecraft-holder">
                    <div>
                        <img src={ServerRoutingURLs.OUTSIDE.MINOTAR.MINECRAFT.HEAD(playerName)} alt=''
                             className="avatar-minecraft-player"/>
                        <Link to={RoutingURLs.PLAYER.PROFILE.VIEW(playerUUID)}
                              className="username-minecraf-player">{playerName}</Link>
                    </div>
                </div>
            ) : null}
        </Fragment>
    )
};

export default withState(SideInformationMinecraftAccount);