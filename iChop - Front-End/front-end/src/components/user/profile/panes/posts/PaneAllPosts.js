import React, {Component, Fragment} from 'react';
import PanePostActions from "./PanePostActions";
import ServerRoutingURLs from "../../../../../constants/server.routing.urls";
import FrontEndResourcesRoutingURLs from "../../../../../constants/front-end.resources.routings";

class PaneAllPosts extends Component {

    constructor(props) {
        super(props);

        this.onUserAvatarError = this.onUserAvatarError.bind(this);
    }


    onUserAvatarError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

    render() {
        let isAuthenticated = true;
        let posts = [];

        return (
            <Fragment>
                {
                    (() => {
                        posts.map((post, index) => {
                            let creatorUsername = 'ItsGosho';
                            let creatorAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', creatorUsername);
                            let content = '';

                            return (
                                <div className="card post" style={{'marginTop': '10px'}}>
                                    <div className="card-body" style={{'marginBottom': '-15px'}}>

                                        <div className="row" style={{'marginTop': '-15px'}}>
                                            <div className="col-md-1" style={{'marginTop': '9px'}}>
                                                <img
                                                    src={creatorAvatarUrl}
                                                    alt=''
                                                    onError={this.onUserAvatarError}
                                                    style={{'width': '30px', 'height': '30px'}}/>
                                            </div>
                                            <div className="col-lg" style={{'width': '150px'}}>
                                                <a href=' ' style={{'color': '#775322', 'fontSize': '13px'}}>
                                                    <b>{creatorUsername}</b> </a>
                                                <small>{content}</small>
                                            </div>
                                        </div>

                                        {
                                            (() => {
                                                if (isAuthenticated) {
                                                    return (
                                                        <div className="row">
                                                            <PanePostActions/>
                                                        </div>
                                                    );
                                                }
                                            })()
                                        }

                                    </div>
                                </div>
                            );
                        })
                    })()
                }
            </Fragment>
        );
    }
}

export default PaneAllPosts;