import React, {Component, Fragment} from 'react';
import PanePostsCreate from "./posts/PanePostsCreate";
import PaneAllPosts from "./posts/PaneAllPosts";
import './PanePosts.css'

class PanePosts extends Component {

    render() {
        let isAuthenticated = true;

        return (
            <Fragment>
                <div className="dropdown-divider"/>

                <div className="create-post">
                    {isAuthenticated ? (<PanePostsCreate/>) : null}
                </div>

                <div className="dropdown-divider"/>

                <div className="all-posts top-10px">

                    <PaneAllPosts/>

                </div>
            </Fragment>);
    }
}

export default PanePosts;