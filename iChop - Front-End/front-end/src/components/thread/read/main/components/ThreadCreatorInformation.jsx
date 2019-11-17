import React, {Component} from 'react';
import {Link} from "react-router-dom";
import ServerRoutingURLs from "../../../../../constants/routing/server.routing.urls";
import RoutingURLs from "../../../../../constants/routing/routing.constants";
import FrontEndResourcesRoutingURLs from "../../../../../constants/front-end.resources.routings";
import Image from "../../../../other/Image";


class ThreadCreatorInformation extends Component {


    render() {
        let {uuid, username, totalComments, minecraftAccountName} = this.props;

        let userProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);
        let minecraftAvatarUrl = ServerRoutingURLs.OUTSIDE.MINOTAR.MINECRAFT.HEAD.replace(':accountName', minecraftAccountName);
        let minecraftProfileUrl = RoutingURLs.PLAYER.PROFILE.VIEW.replace(':uuid', uuid);

        return (
            <div className="row">
                <div className="col-md-4">
                    <div align="center">
                        <small>
                            👤 <Link to={userProfileUrl}>{username}</Link>
                        </small>
                    </div>
                    <div align="center">

                        <Image url={userAvatarUrl}
                               defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                               style={{'width': '50px', 'height': '50px'}}/>

                    </div>
                    <div align="center">
                        <small>
                            <small>💬</small>
                            <span>{totalComments} total comments</span>
                        </small>
                    </div>
                </div>
                <div className="col-md-8">
                    <div align="center">
                        <div className="row">

                            {
                                (() => {
                                    if (minecraftAccountName !== undefined) {
                                        return (
                                            <small>
                                                <Link to={minecraftProfileUrl}>
                                                    <img
                                                        src={minecraftAvatarUrl}
                                                        style={{'width': '22px', 'height': '22px'}}
                                                        alt=''/>
                                                    <span
                                                        style={{
                                                            'fontSize': '15px',
                                                            'fontFamily': 'Consolas'
                                                        }}>{minecraftAccountName}
                                                            </span>
                                                </Link>
                                            </small>
                                        );
                                    }
                                })()
                            }

                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default ThreadCreatorInformation;