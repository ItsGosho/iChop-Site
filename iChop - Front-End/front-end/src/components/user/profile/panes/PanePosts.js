import React, {Component, Fragment} from 'react';
import PanePostCreate from "./posts/PanePostCreate";
import PanePostsAll from "./posts/PanePostsAll";
import './PanePosts.css'

class PanePosts extends Component {

    render() {
        let isAuthenticated = true;

        return (
            <Fragment>
                <div className="dropdown-divider"/>

                <div className="create-post">
                    {isAuthenticated ? (<PanePostCreate/>) : null}
                </div>

                <div className="dropdown-divider"/>

                <div className="all-posts top-10px">
                    <PanePostsAll/>
                </div>
            </Fragment>);
    }
}

export default PanePosts;