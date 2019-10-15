import React, {Component} from 'react';
import Image from "../../../other/Image";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";
import './FollowModal.css'
import ModalTitle from "../../../modal/ModalTitle";
import ModalBody from "../../../modal/ModalBody";
import Modal from "../../../modal/Modal";

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
                <div className="w-100 div-follow-holder">

                    <Image url={'/'}
                           defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                           className="div-follow-image"/>

                    <span className="span-follow-username">
                        <b>
                            <Link to={profileUrl}>{username}</Link>
                        </b>
                    </span>

                    <button className="btn btn-warning btn-sm float-right button-unfollow"
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


            <Modal relationTo="all-followings">

                <ModalTitle>
                    <h5 className="modal-title">All users that <span><b>ItsGosho</b></span> is following:</h5>
                </ModalTitle>

                <ModalBody>
                    <div className="row">
                        {this.iterFollowings()}
                    </div>
                </ModalBody>

            </Modal>

        );
    }
}

export default UserFollowingsModal;