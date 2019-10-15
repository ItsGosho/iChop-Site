import React, {Component} from 'react';
import './FollowModal.css'
import ModalTitle from "../../../modal/ModalTitle";
import ModalBody from "../../../modal/ModalBody";
import Modal from "../../../modal/Modal";
import UserFollowModalBaseRow from "./UserFollowModalBaseRow";

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

            return (
                <UserFollowModalBaseRow username={username}>
                    <button className="btn btn-warning btn-sm float-right button-unfollow"
                            onClick={() => {
                                this.unFollow(username)
                            }}>
                        Unfollow
                    </button>
                </UserFollowModalBaseRow>
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