import React, {Component} from 'react';
import {Link} from "react-router-dom";
import RoutingURLs from "../../../../constants/routing.constants";
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";

class SideInformationFollowers extends Component {

    constructor(props) {
        super(props);

        this.onUserAvatarError = this.onUserAvatarError.bind(this);
    }


    onUserAvatarError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

    render() {
        let followers = [].slice(0, 4);

        return (
            <div className="row d-flex justify-content-center align-items-center"
                 style={{'width': '100%', 'margin-left': '0px'}}>

                {
                    (() => {
                        followers.map((follower, index) => {
                            return (
                                <Link to={RoutingURLs.USER.PROFILE.VIEW.replace(':username', follower.username)}>
                                    <img
                                        src={ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', follower.username)}
                                        className="img-user-avatar"
                                        title={follower.username}
                                        onError={this.onUserAvatarError}
                                        alt=''
                                        style={{
                                            'width': '30px',
                                            'height': '30px',
                                            'marginLeft': '5px',
                                            'marginTop': '2px',
                                            'marginBottom': '2px'
                                        }}/>
                                </Link>
                            );
                        })
                    })()
                }
            </div>
        );
    }
}

export default SideInformationFollowers;