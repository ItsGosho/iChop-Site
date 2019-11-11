import React, {Component, Fragment} from 'react';
import PanePostCreate from "./posts/PanePostCreate";
import PanePostsAll from "./posts/PanePostsAll";
import './PanePosts.css'
import withState from "../../../../hocs/with.state";

class PanePosts extends Component {

    render() {
        let authenticatedUser = this.props.authenticatedUserInfo;


        return (
            <Fragment>
                <div className="dropdown-divider"/>

                <div className="create-post">
                    {authenticatedUser.username !== '' ? (<PanePostCreate/>) : null}
                </div>

                <div className="dropdown-divider"/>

                <div className="all-posts top-10px">
                    <PanePostsAll/>
                </div>
            </Fragment>);
    }
}

export default withState(PanePosts);