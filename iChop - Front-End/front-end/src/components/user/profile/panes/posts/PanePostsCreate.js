import React, {Component} from 'react';
import ServerRoutingURLs from "../../../../../constants/server.routing.urls";
import FrontEndResourcesRoutingURLs from "../../../../../constants/front-end.resources.routings";

class PanePostsCreate extends Component {

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
                <textarea name="content" id="textarea-createPost-userProfile"
                          style={{
                              'border': '1px solid #ccc',
                              'borderRadius': '3px',
                              'height': '60px',
                              'overflow': 'auto',
                              'width': '100%',
                              'resize': 'none'
                          }}/>
                    </div>

                </div>

                <div className="row" style={{'marginTop': '3px'}}>
                    <div className="col-lg">
                        <button type="button" id="button-createPost-userProfile" className="btn btn-info btn-sm"
                                style={{'float': 'right', 'display': 'inlineBlock'}}>Post
                        </button>
                        <small id="small-leftPostCreationCharacters-userProfile"
                               style={{
                                   'float': 'right',
                                   'display': 'inlineBlock',
                                   'marginRight': '3px',
                                   'color': 'darkgreen',
                                   'fontSize': '13px'
                               }}>150
                        </small>
                    </div>
                </div>
            </form>
        );
    }
}

export default PanePostsCreate;