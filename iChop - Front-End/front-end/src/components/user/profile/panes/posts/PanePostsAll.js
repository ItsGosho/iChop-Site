import React, {Component, Fragment} from 'react';
import PanePost from "./PanePost";

class PanePostsAll extends Component {

    constructor(props) {
        super(props);

        this.iteratePosts = this.iteratePosts.bind(this);
    }

    iteratePosts(posts) {
        let isAuthenticated = true;

        return posts.map((post, index) => {
            let {creatorUsername, content} = post;

            return (<PanePost creatorUsername={creatorUsername}
                              isAuthenticated={isAuthenticated}
                              content={content}/>);
        })
    }

    render() {
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
                {this.iteratePosts(posts)}
            </Fragment>
        );
    }
}

export default PanePostsAll;