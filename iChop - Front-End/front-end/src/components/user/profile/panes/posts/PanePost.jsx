import React, {Component} from 'react';
import './PanePost.css'
import PanePostActions from "./PanePostActions";
import ServerRoutingURLs from "../../../../../constants/routing/server.routing.urls";
import Image from "../../../../other/Image";
import FrontEndResourcesRoutingURLs from "../../../../../constants/front-end.resources.routings";
import {Link} from "react-router-dom";
import RoutingURLs from "../../../../../constants/routing/routing.constants";
import PropTypes from "prop-types";
import dateFormat from "dateformat";

class PanePost extends Component {


    render() {
        let {id, creatorUsername, content, createdOn, userProfileUsername, isAuthenticated,key} = this.props;
        let datePattern = "dd mmm, yyyy";

        let creatorAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', creatorUsername);
        let creatorProfileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', creatorUsername);

        return (
            <div className="card top-10px" key={key}>
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
                            <div className="col-md-12">
                                <span className="post-createdOn">{dateFormat(createdOn, datePattern)}</span>

                                <PanePostActions id={id}
                                                 content={content}
                                                 creatorUsername={creatorUsername}
                                                 userProfileUsername={userProfileUsername}/>
                            </div>
                        </div>
                    ) : null}

                </div>
            </div>
        );
    }

}

PanePost.propTypes = {
    isAuthenticated: PropTypes.bool,
    id: PropTypes.string,
    key: PropTypes.string,
    creatorUsername: PropTypes.string,
    content: PropTypes.string,
    createdOn: PropTypes.object,
    userProfileUsername: PropTypes.string,
};

export default PanePost;