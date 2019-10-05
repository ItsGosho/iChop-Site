import React, {Component, Fragment} from 'react';
import PanePostsCreate from "./posts/PanePostsCreate";
import PaneAllPosts from "./posts/PaneAllPosts";

class PanePosts extends Component {

    render() {
        let isAuthenticated = true;

        return (
            <Fragment>
                <div className="dropdown-divider"/>

                {
                    (() => {
                        if (isAuthenticated) {
                            return (
                                <div className="create-post">
                                    <PanePostsCreate/>
                                </div>
                            )
                        }
                    })()
                }

                <div className="dropdown-divider"/>

                <div className="all-posts" style={{'marginTop': '10px'}}>

                    <PaneAllPosts/>

                </div>
            </Fragment>);
    }
}

export default PanePosts;