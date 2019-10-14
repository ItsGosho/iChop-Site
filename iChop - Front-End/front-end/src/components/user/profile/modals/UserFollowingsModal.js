import React, {Component} from 'react';
import Image from "../../../other/Image";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";

class UserFollowingsModal extends Component {

    constructor(props) {
        super(props);

        this.state = {
            followings: [
                {username: 'Ivan'},
                {username: 'Qncho'},
            ]
        };

        this.unFollow = this.unFollow.bind(this);
        this.iterFollowings = this.iterFollowings.bind(this);
    }

    unFollow(username) {
        console.log(`Unfollow: ${username}`);
    }


    iterFollowings() {
        return this.state.followings.map((following, index) => {
            let {username} = following;
            let profileUrl = RoutingURLs.USER.PROFILE.VIEW.replace(':username', username);

            return (
                <div className="w-100" style={{'marginTop': '10px'}}>

                    <Image url={'/'}
                           defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                           style={{'width': '50px', 'height': '50px', 'marginLeft': '15px'}}/>

                    <span style={{'marginLeft': '15px'}}>
                        <b>
                            <Link to={profileUrl}>{username}</Link>
                        </b>
                    </span>

                    <button className="btn btn-warning btn-sm float-right"
                            style={{
                                'fontFamily': 'Consolas',
                                'marginTop': '15px',
                                'marginRight': '15px'
                            }}
                            onClick={() => {
                                this.unFollow(username)
                            }}>
                        Unfollow
                    </button>
                </div>
            )
        })
    }

    render() {

        return (
            <div className="modal modal-all-following" tabIndex="-1" role="dialog">
                <div className="modal-dialog" role="document">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">All users that <span><b>ItsGosho</b></span> is
                                following:</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        <div id="div-insert_followings-user_followings_all" className="modal-body"
                             style={{'overflow': 'auto', 'maxHeight': '400px'}}>

                            <div className="row">

                                {this.iterFollowings()}

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default UserFollowingsModal;