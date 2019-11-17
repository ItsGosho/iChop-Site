import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";
import FrontEndResourcesRoutingURLs from "../../../../../constants/front-end.resources.routings";
import RoutingURLs from "../../../../../constants/routing/routing.constants";
import ServerRoutingURLs from "../../../../../constants/routing/server.routing.urls";

class CommentCreatorInformation extends Component {

    constructor(props) {
        super(props);

        this.onUserAvatarError = this.onUserAvatarError.bind(this);
    }

    onUserAvatarError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

    render() {
        let {uuid,username,totalComments,minecraftAccountName} = this.props;

        let userProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);
        let minecraftPorfileUrl = RoutingURLs.PLAYER.PROFILE.VIEW.replace(':uuid', uuid);
        let minecraftAvatarUrl = ServerRoutingURLs.OUTSIDE.MINOTAR.MINECRAFT.HEAD.replace(':accountName', minecraftAccountName);

        return (
            <Fragment>
                <div>
                    <img
                        src={userAvatarUrl}
                        alt=' '
                        onError={this.onUserAvatarError}
                        style={{'width':'50px','height':'50px','maxWidth':'100%'}}
                        className="card-img-top thread-comment-creator_avatar"/>
                </div>
                <div>
                    <small>
                        👤<Link to={userProfileUrl}>{username}</Link>
                    </small>
                </div>
                <div>
                    <small>
                        <small>💬</small>
                        <span>{totalComments} total comments</span>
                    </small>
                </div>

                <div>

                    {
                        (() => {
                            if (minecraftAccountName !== undefined) {
                                return (
                                    <small>
                                        <Link to={minecraftPorfileUrl}>
                                            <img
                                                src={minecraftAvatarUrl}
                                                className="card-img-top"
                                                style={{'width':'15px','height':'15px','maxWidth':'100%'}}
                                                alt=' '/>
                                            {minecraftAccountName}
                                        </Link>
                                    </small>
                                );
                            }
                        })()
                    }

                </div>
            </Fragment>
        );
    }

}

export default CommentCreatorInformation;