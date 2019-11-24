import React, {Fragment} from 'react';
import {Link} from "react-router-dom";
import FrontEndResourcesRoutingURLs from "../../../../../constants/front-end.resources.routings";
import RoutingURLs from "../../../../../constants/routing/routing.constants";
import ServerRoutingURLs from "../../../../../constants/routing/server.routing.urls";
import Image from "../../../../other/Image";
import PropTypes from 'prop-types';


const CommentCreatorInformation = ({uuid, username, totalComments, minecraftAccountName}) => (
    <Fragment>

        <div>
            <Image style={{'width': '50px', 'height': '50px', 'maxWidth': '100%'}}
                   url={ServerRoutingURLs.DATA.USER.AVATAR.GET(username)}
                   defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                   className="card-img-top thread-comment-creator_avatar"
                   title={minecraftAccountName}/>
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

        <div>

            {minecraftAccountName !== undefined ? (
                <small>
                    <Link to={RoutingURLs.PLAYER.PROFILE.VIEW(uuid)}>
                        <img
                            src={ServerRoutingURLs.OUTSIDE.MINOTAR.MINECRAFT.HEAD(minecraftAccountName)}
                            className="card-img-top"
                            style={{'width': '15px', 'height': '15px', 'maxWidth': '100%'}}
                            alt=' '/>
                        {minecraftAccountName}
                    </Link>
                </small>
            ) : null}

        </div>
    </Fragment>
);


export default CommentCreatorInformation;

CommentCreatorInformation.propTypes = {
    uuid: PropTypes.string,
    username: PropTypes.string,
    totalComments: PropTypes.number,
    minecraftAccountName: PropTypes.string,
};