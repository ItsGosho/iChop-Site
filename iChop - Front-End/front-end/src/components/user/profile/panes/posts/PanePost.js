import React, {Component} from 'react';
import './PanePost.css'
import PanePostActions from "./PanePostActions";
import ServerRoutingURLs from "../../../../../constants/server.routing.urls";
import Image from "../../../../other/Image";
import FrontEndResourcesRoutingURLs from "../../../../../constants/front-end.resources.routings";
import {Link} from "react-router-dom";
import RoutingURLs from "../../../../../constants/routing.constants";

class PanePost extends Component {


    render() {
        let {creatorUsername, isAuthenticated, content} = this.props;
        let creatorAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', creatorUsername);
        let creatorProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', creatorUsername);

        return (
            <div className="card top-10px">
                <div className="card-body bottom--15px">

                    <div className="row top--15px">

                        <div className="col-md-1">

                            <Image url={creatorAvatarUrl}
                                   defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                                   className="post-user-avatar"/>

                        </div>

                        <div className="col-lg post-col-holder">
                            <Link to={creatorProfileUrl} className="col-post-creator-holder">
                                <b>{creatorUsername}</b>
                            </Link>
                            <small className="left-5px">{content}</small>
                        </div>
                    </div>

                    {isAuthenticated ? (
                        <div className="row">
                            <PanePostActions/>
                        </div>
                    ) : null}

                </div>
            </div>
        );
    }

}

export default PanePost;