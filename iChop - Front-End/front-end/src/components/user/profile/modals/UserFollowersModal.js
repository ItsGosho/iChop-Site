import React, {Component} from 'react';
import Image from "../../../other/Image";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import RoutingURLs from "../../../../constants/routing.constants";
import {Link} from "react-router-dom";
import './FollowModal.css'
import Modal from "../../../modal/Modal";
import ModalTitle from "../../../modal/ModalTitle";
import ModalBody from "../../../modal/ModalBody";

class UserFollowersModal extends Component {

    constructor(props) {
        super(props);

        this.state = {
            followings: [
                {username: 'Jancho'},
                {username: 'Karancho'},
            ]
        };

        this.iterFollowers = this.iterFollowers.bind(this);
    }


    iterFollowers() {
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
                </div>
            )
        })
    }

    render() {

        return (
            <Modal relationTo="all-followers">
                
                <ModalTitle>
                    <h5 className="modal-title">All users that are following <span><b>ItsGosho</b></span>:</h5>
                </ModalTitle>

                <ModalBody>
                    <div className="row">
                        {this.iterFollowers()}
                    </div>
                </ModalBody>

            </Modal>
        );
    }
}

export default UserFollowersModal;