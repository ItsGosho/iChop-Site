import React from 'react';
import {Link} from "react-router-dom";
import ServerRoutingURLs from "../../../../../constants/routing/server.routing.urls";
import RoutingURLs from "../../../../../constants/routing/routing.constants";
import FrontEndResourcesRoutingURLs from "../../../../../constants/front-end.resources.routings";
import Image from "../../../../other/Image";
import withState from "../../../../../hocs/with.state";


const ThreadCreatorInformation = (props) => {
    let uuid = '8ed20904-3262-401a-901a-1946504d2eea';
    let minecraftAccountName = 'ItsGosho';

    let {creatorUsername, creatorTotalComments} = props.threadRead;

    return (
        <div className="row">
            <div className="col-md-4">
                <div align="center">
                    <small>
                        👤 <Link to={RoutingURLs.USER.PROFILE.VIEW(creatorUsername)}>{creatorUsername}</Link>
                    </small>
                </div>
                <div align="center">

                    <Image url={ServerRoutingURLs.DATA.USER.AVATAR.GET(creatorUsername)}
                           defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                           style={{'width': '50px', 'height': '50px'}}/>

                </div>
                <div align="center">
                    <small>
                        <small>💬</small>
                        <span>{creatorTotalComments} total comments</span>
                    </small>
                </div>
            </div>
            <div className="col-md-8">
                <div align="center">
                    <div className="row">

                        {minecraftAccountName !== undefined ? (
                            <small>
                                <Link to={RoutingURLs.PLAYER.PROFILE.VIEW(uuid)}>
                                    <img
                                        src={ServerRoutingURLs.OUTSIDE.MINOTAR.MINECRAFT.HEAD(minecraftAccountName)}
                                        style={{'width': '22px', 'height': '22px'}}
                                        alt=''/>
                                    <span style={{
                                        'fontSize': '15px',
                                        'fontFamily': 'Consolas'
                                    }}>{minecraftAccountName}</span>
                                </Link>
                            </small>
                        ) : null}

                    </div>
                </div>
            </div>
        </div>
    );
};

export default withState(ThreadCreatorInformation);