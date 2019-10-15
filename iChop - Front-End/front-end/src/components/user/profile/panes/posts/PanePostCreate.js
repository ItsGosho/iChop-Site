import React, {Component} from 'react';
import ServerRoutingURLs from "../../../../../constants/server.routing.urls";
import FrontEndResourcesRoutingURLs from "../../../../../constants/front-end.resources.routings";
import './PanePostCreate.css';
import Image from "../../../../other/Image";

class PanePostCreate extends Component {

    render() {
        let username = 'ItsGosho';
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);

        /*TODO: count left chars like in the profile edit and add method for the post button*/
        return (
            <form>
                <div className="row">

                    <div className="col-md-1">
                        <Image url={userAvatarUrl}
                               defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                               className="pane-post-create-avatar"/>
                    </div>

                    <div className="col-lg">
                        <textarea name="content" className="pane-post-create-post"/>
                    </div>

                </div>

                <div className="row">
                    <div className="col-lg">
                        <button type="button" className="btn btn-info btn-sm pane-post-button">Post</button>
                        <small className="pane-post-left-chars">150</small>
                    </div>
                </div>
            </form>
        );
    }
}

export default PanePostCreate;