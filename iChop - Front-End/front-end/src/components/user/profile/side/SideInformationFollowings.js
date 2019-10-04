import React,{Component} from 'react';
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import RoutingURLs from "../../../../constants/routing.constants";
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import {Link} from "react-router-dom";

class SideInformationFollowings extends Component {

    constructor(props) {
        super(props);

        this.onUserAvatarError = this.onUserAvatarError.bind(this);
    }


    onUserAvatarError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

    render() {
        let followings = [].slice(0, 4);

        return (
            <div className="row d-flex justify-content-center align-items-center"
                 style={{'width': '100%', 'marginLeft': '0px'}}>

                {
                    (() => {
                        followings.map((following, index) => {
                            let {username} = following;
                            let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);
                            let avatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);

                            return (
                                <Link to={profileUrl}>
                                    <img key={index}
                                         src={avatarUrl}
                                         className="img-user-avatar"
                                         title={username}
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

export default SideInformationFollowings;