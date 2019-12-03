import React, {Fragment} from 'react';
import {Link} from "react-router-dom";
import FrontEndResourcesRoutingURLs from "../../../../../constants/front-end.resources.routings";
import RoutingURLs from "../../../../../constants/routing/routing.constants";
import ServerRoutingURLs from "../../../../../constants/routing/server.routing.urls";
import Image from "../../../../other/Image";
import PropTypes from 'prop-types';


const CommentCreatorInformation = ({uuid, username, totalComments}) => (
    <Fragment>

        <div>
            <Image style={{'width': '50px', 'height': '50px', 'maxWidth': '100%'}}
                   url={ServerRoutingURLs.CORE.DATA_STORAGE.GET_USER_AVATAR(username)}
                   defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                   className="card-img-top thread-comment-creator_avatar"/>
        </div>

        <div>
            <small>
                👤<Link to={RoutingURLs.USER.PROFILE.VIEW(username)}>{username}</Link>
            </small>
        </div>

        <div>
            <small>
                <small>💬</small>
                <span>{totalComments} total comments</span>
            </small>
        </div>
    </Fragment>
);


export default CommentCreatorInformation;

CommentCreatorInformation.propTypes = {
    uuid: PropTypes.string,
    username: PropTypes.string,
    totalComments: PropTypes.number,
};