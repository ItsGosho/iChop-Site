import React, {Component} from 'react';
import ServerRoutingURLs from "../../../../../constants/server.routing.urls";
import FrontEndResourcesRoutingURLs from "../../../../../constants/front-end.resources.routings";
import './PanePostCreate.css';

class PanePostCreate extends Component {

    constructor(props) {
        super(props);

        this.onUserAvatarError = this.onUserAvatarError.bind(this);
    }


    onUserAvatarError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

    render() {
        let username = 'ItsGosho';
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);

        return (
            <form>
                <div className="row">

                    <div className="col-md-1">
                        <object
                            data={userAvatarUrl}
                            type="image/png"
                            className="img-user-avatar" style={{'width': '30px', 'height': '30px'}}>
                            <img onError={this.onUserAvatarError} alt=' ' className="img-user-avatar"
                                 style={{'width': '30px', 'height': '30px'}}/>
                        </object>
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