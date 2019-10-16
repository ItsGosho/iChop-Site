import React, {Component} from 'react';
import './PanePost.css'
import PanePostActions from "./PanePostActions";
import ServerRoutingURLs from "../../../../../constants/server.routing.urls";

class PanePost extends Component {


    render() {
        let {creatorUsername, isAuthenticated, content} = this.props;
        let creatorAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', creatorUsername);

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
    }

}

export default PanePost;