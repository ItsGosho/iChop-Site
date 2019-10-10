import React, {Component} from 'react';
import {Link} from "react-router-dom";
import ServerRoutingURLs from "../../../constants/server.routing.urls";
import RoutingURLs from "../../../constants/routing.constants";

class CommentCreatorInformation extends Component {


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
                        <img
                            src={userAvatarUrl}
                            onError={this.onUserAvatarError}
                            alt=''
                            style={{'width': '50px', 'height': '50px'}}
                            className="card-img-top thread-creator-avatar"/>
                    </div>
                    <div align="center">
                        <small>
                            <small>💬</small>
                            <span className="totalComments">{totalComments} total comments</span>
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

export default CommentCreatorInformation;