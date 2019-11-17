import React, {Component} from 'react';
import './FollowModal.css'
import ModalTitle from "../../../modal/ModalTitle";
import ModalBody from "../../../modal/ModalBody";
import Modal from "../../../modal/Modal";
import UserFollowModalBaseRow from "./UserFollowModalBaseRow";
import withState from "../../../../hocs/with.state";

class UserFollowingsModal extends Component {

    constructor(props) {
        super(props);

        this.unFollow = this.unFollow.bind(this);
        this.iterFollowings = this.iterFollowings.bind(this);
    }

    unFollow(username) {
        console.log(`Soon!`);
    }


    iterFollowings() {
        return this.props.userProfileInfo.followings.map((following, index) => {
            let {username} = following;

            return (
                <UserFollowModalBaseRow username={username}>
                    <button className="btn btn-warning btn-sm float-right button-unfollow"
                            onClick={() => {
                                this.unFollow(username)
                            }}>
                        Soon!
                    </button>
                </UserFollowModalBaseRow>
            )
        })
    }

    render() {

        return (


            <Modal relationTo="all-followings">

                <ModalTitle>
                    <h5 className="modal-title">All users that <span><b>{this.props.userProfileInfo.username}</b></span> is following:</h5>
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

export default withState(UserFollowingsModal);