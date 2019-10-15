import React, {Component} from 'react';
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";

class SideInformationMinecraftAccount extends Component {

    render() {
        let uuid = 'fdsfs';
        let accountName = 'ItsGosho';
        let headAvatarUrl = ServerRoutingURLs.OUTSIDE.MINOTAR.MINECRAFT.HEAD.replace(':accountName', accountName);
        let accountProfileUrl = RoutingURLs.PLAYER.PROFILE.VIEW.replace(':uuid', uuid);

        return (
            (() => {
                if (accountName != null) {
                    return (
                        <div className="card minecraft-user-link" style={{'marginTop': '10px'}}>
                            <div>
                                <img
                                    src={headAvatarUrl}
                                    alt=''
                                    style={{
                                        'width': '25px',
                                        'height': '25px',
                                        'marginLeft': '5px',
                                        'marginTop': '2px',
                                        'marginBottom': '2px'
                                    }}/>
                                <Link to={accountProfileUrl} style={{'marginLeft':'5px'}}>
                                    {accountName}
                                </Link>
                            </div>
                        </div>
                    )
                }
            })()
        )
    }
}

export default SideInformationMinecraftAccount;