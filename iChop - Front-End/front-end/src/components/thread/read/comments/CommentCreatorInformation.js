import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";

class CommentCreatorInformation extends Component {

    constructor(props) {
        super(props);

        this.onUserAvatarError = this.onUserAvatarError.bind(this);
    }

    onUserAvatarError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

    render() {
        let {username,totalComments,minecraftAccountName} = this.props;

        return (
            <Fragment>
                <div>
                    <img
                        src={creatorAvatarUrl}
                        alt=' '
                        onError={this.onUserAvatarError}
                        style={{'width':'50px','height':'50px','maxWidth':'100%'}}
                        className="card-img-top thread-comment-creator_avatar"/>
                </div>
                <div>
                    <small>
                        👤<Link to={creatorProfileUrl}>{creatorUsername}</Link>
                    </small>
                </div>
                <div>
                    <small>
                        <small>💬</small>
                        <span
                            className="totalComments">{creatorTotalComments} total comments</span>
                    </small>
                </div>

                <div>

                    {
                        (() => {
                            if (creatorMinecraftAccountName !== undefined) {
                                return (
                                    <small>
                                        <Link to={creatorMinecraftPorfileUrl}>
                                            <img
                                                src={creatorMinecraftAvatarUrl}
                                                className="card-img-top"
                                                style={{'width':'15px','height':'15px','maxWidth':'100%'}}
                                                alt=' '/>
                                            {creatorMinecraftAccountName}
                                        </Link>
                                    </small>
                                );
                            }
                        })()
                    }

                </div>
            </Fragment>
        );
    }

}

export default CommentCreatorInformation;