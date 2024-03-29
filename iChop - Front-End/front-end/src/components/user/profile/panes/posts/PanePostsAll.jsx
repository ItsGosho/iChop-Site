import React, {Component, Fragment} from 'react';
import PanePost from "./PanePost";
import withState from "../../../../../hocs/with.state";


class PanePostsAll extends Component {

    constructor(props) {
        super(props);

        this.iteratePosts = this.iteratePosts.bind(this);
    }

    iteratePosts(posts) {
        let authenticatedUser = this.props.authenticatedUserInfo;

        return posts && posts.length > 0 ?
            posts.map((post, index) => {
                let {id, creatorUsername, content, createdOn, userProfileUsername} = post;

                return (<PanePost id={id}
                                  key={id}
                                  creatorUsername={creatorUsername}
                                  isAuthenticated={authenticatedUser.username !== ''}
                                  createdOn={createdOn}
                                  userProfileUsername={userProfileUsername}
                                  content={content}/>);
            }) : <center>There are not any posts!</center>;
    }

    render() {
        let {posts} = this.props.userProfileInfo;

        return (
            <Fragment>
                {this.iteratePosts(posts)}
            </Fragment>
        );
    }
}

export default withState(PanePostsAll);