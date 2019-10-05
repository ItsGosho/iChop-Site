import React, {Component, Fragment} from 'react';
import PanePostsCreate from "./posts/PanePostsCreate";
import PanePostReportModal from "./posts/PanePostReportModal";
import PanePostActions from "./posts/PanePostActions";

class PanePosts extends Component {

    render() {
        let isAuthenticated = true;
        let posts = [];

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

                <div className="all-posts" style="margin-top: 10px">

                    <th:block th:each="post : *{posts}" th:object="${post}">

                        <div className="card post" style="margin-top: 10px">
                            <div className="card-body" style="margin-bottom: -15px">

                                <PanePost/>

                                {
                                    (() => {
                                        if (isAuthenticated) {
                                            return (
                                                <div className="row">
                                                    <PanePostActions/>
                                                </div>
                                            );
                                        }
                                    })()
                                }

                            </div>
                        </div>

                    </th:block>

                </div>
                <
                    /Fragment>
                    )
                    ;
                    }
                    }

                    export default PanePosts;