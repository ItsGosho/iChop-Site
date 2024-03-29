import React, {Component} from 'react';
import ServerRoutingURLs from "../../../../../constants/routing/server.routing.urls";
import FrontEndResourcesRoutingURLs from "../../../../../constants/front-end.resources.routings";
import './PanePostCreate.css';
import Image from "../../../../other/Image";
import TextAreaWithCounter from "../../../../other/TextAreaWithCounter";
import {PostValidationConstants, UserValidationConstants} from "../../../../../constants/other/validation.constants";
import {withRouter} from "react-router-dom";
import CommentServices from "../../../../../services/comment.services";
import withDispatchers from "../../../../../hocs/with.dispatchers";


class PanePostCreate extends Component {

    constructor(props) {
        super(props);

        this.state = {
            content: '',
            isSuccessful: false
        };

        this.onCreate = this.onCreate.bind(this);
        this.onContentValueChange = this.onContentValueChange.bind(this);
    }


    onContentValueChange(value) {
        this.setState({content: value})
    }

    async onCreate() {
        let {username} = this.props.userProfileInfo;
        let {content} = this.state;

        let response = await CommentServices.createUserProfileComment(username, content);

        if (response.successful) {
            this.props.fetchPosts(username);
            this.setState({content: ''})
            this.setState({isSuccessful: true})
        }
    }

    render() {
        let {username: viewerUsername} = this.props.authenticatedUserInfo;
        let viewerAvatarUrl = ServerRoutingURLs.CORE.DATA_STORAGE.GET_USER_AVATAR(viewerUsername);
        let leftPostCharacters = PostValidationConstants.MAX_CHARACTERS - this.state.content.length;

        return (
            <form>
                <div className="row">

                    <div className="col-md-1">
                        <Image url={viewerAvatarUrl}
                               defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                               title={viewerUsername}
                               className="pane-post-create-avatar"/>
                    </div>

                    <div className="col-lg">

                        <TextAreaWithCounter name="content"
                                             value={this.state.content}
                                             className="pane-post-create-post"
                                             executeDelete={this.state.isSuccessful}
                                             onValueChange={this.onContentValueChange}
                                             maxCharacters={PostValidationConstants.MAX_CHARACTERS}/>
                    </div>
                </div>

                <div className="row">
                    <div className="col-lg">

                        <button type="button"
                                className="btn btn-info btn-sm pane-post-button"
                                onClick={this.onCreate}>
                            Post
                        </button>

                        <small className="pane-post-left-chars">{leftPostCharacters}</small>
                    </div>
                </div>
            </form>
        );
    }
}

export default withRouter(withDispatchers(PanePostCreate));