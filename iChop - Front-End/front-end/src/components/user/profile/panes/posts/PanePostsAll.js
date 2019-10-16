import React, {Component, Fragment} from 'react';
import PanePostActions from "./PanePostActions";
import ServerRoutingURLs from "../../../../../constants/server.routing.urls";
import FrontEndResourcesRoutingURLs from "../../../../../constants/front-end.resources.routings";
import PanePost from "./PanePost";

class PanePostsAll extends Component {

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
        let posts = [
            {
                creatorUsername: 'Salamcho',
                content: 'Nice profile!'
            },
            {
                creatorUsername: 'Ivancho',
                content: 'Woooho!'
            }
        ];

        return (
            <Fragment>
                {posts.map((post, index) => {
                    let {creatorUsername, content} = post;

                    return (<PanePost creatorUsername={creatorUsername}
                                      isAuthenticated={true}
                                      content={content}/>);
                })}
            </Fragment>
        );
    }
}

export default PanePostsAll;