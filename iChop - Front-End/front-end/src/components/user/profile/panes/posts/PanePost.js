import React, {Component} from 'react';

class PanePost extends Component {

    render() {

        return (
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
    );
    }
    }

    export default Change;