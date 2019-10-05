import React, {Component} from 'react';
import ServerRoutingURLs from "../../../../../constants/server.routing.urls";

class PanePostsCreate extends Component {

    render() {
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', creatorUsername);

        return (
            <form>
                <div className="row">

                    <div className="col-md-1">
                        <object
                            th:data="@{http://localhost:8001/data/user/{username}/avatar(username=${#authentication.principal.username})}"
                            type="image/png"
                            className="img-user-avatar" style={{'width': '30px', 'height': '30px'}}>
                            <img src="/res/img/avatar-user.png" alt="example" className="img-user-avatar"
                                 style={{'width': '30px', 'height': '30px'}}>
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
                          }}></textarea>
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