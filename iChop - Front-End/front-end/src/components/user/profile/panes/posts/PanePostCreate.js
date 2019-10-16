import React, {Component} from 'react';
import ServerRoutingURLs from "../../../../../constants/server.routing.urls";
import FrontEndResourcesRoutingURLs from "../../../../../constants/front-end.resources.routings";
import './PanePostCreate.css';
import Image from "../../../../other/Image";
import TextAreaWithCounter from "../../../../other/TextAreaWithCounter";
import {PostValidationConstants} from "../../../../../constants/validation.constants";

class PanePostCreate extends Component {

    constructor(props) {
        super(props);

        this.state = {
            content: '',

            leftCharacters: PostValidationConstants.MAX_CHARACTERS
        };

        this.onCreate = this.onCreate.bind(this);
        this.onContentCountedCharacters = this.onContentCountedCharacters.bind(this);
        this.onContentValueChange = this.onContentValueChange.bind(this);
    }

    onContentCountedCharacters(leftCharacters) {
        this.setState({leftCharacters: leftCharacters})
    }

    onContentValueChange(value) {
        this.setState({content: value})
    }

    onCreate() {
        let {content} = this.state;
        console.log('Create!');
    }

    render() {
        let username = 'ItsGosho';
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);

        return (
            <form>
                <div className="row">

                    <div className="col-md-1">
                        <Image url={userAvatarUrl}
                               defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                               className="pane-post-create-avatar"/>
                    </div>

                    <div className="col-lg">

                        <TextAreaWithCounter name="content"
                                             value={this.state.content}
                                             className="pane-post-create-post"
                                             onCounted={this.onContentCountedCharacters}
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

                        <small className="pane-post-left-chars">{this.state.leftCharacters}</small>
                    </div>
                </div>
            </form>
        );
    }
}

export default PanePostCreate;