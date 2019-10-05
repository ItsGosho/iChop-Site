import React, {Component} from 'react';
import PanePostActions from "./PanePostActions";

class PaneAllPosts extends Component {

    render() {
        let isAuthenticated = true;

        return (
        <div className="card post" style="margin-top: 10px">
            <div className="card-body" style="margin-bottom: -15px">

                <div className="row" style="margin-top: -15px">
                    <div className="col-md-1" style="margin-top: 9px">
                        <img
                            th:src="@{http://localhost:8001/data/user/{username}/avatar(username=*{creatorUsername})}"
                            alt="example"
                            onError="this.onerror = null;this.src = '/res/img/avatar-user.png'"
                            style="width: 30px;height: 30px">
                    </div>
                    <div className="col-lg" style="width: 150px">
                        <a href="" style="color: #775322;font-size: 13px"><b
                            th:text="*{creatorUsername}">Creator
                            Username...</b> </a>
                        <small th:text="*{content}">Content...</small>
                    </div>
                </div>

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

    );
    }
    }

    export default PanePost;