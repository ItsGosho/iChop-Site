import React, {Component} from 'react';
import './FollowModal.css'
import Modal from "../../../modal/Modal";
import ModalTitle from "../../../modal/ModalTitle";
import ModalBody from "../../../modal/ModalBody";
import UserFollowModalBaseRow from "./UserFollowModalBaseRow";
import withState from "../../../../hocs/with.state";

class UserFollowersModal extends Component {

    constructor(props) {
        super(props);

        this.iterFollowers = this.iterFollowers.bind(this);
    }


    iterFollowers() {
        return this.props.userProfileInfo.followers.map((following, index) => {
            let {username} = following;

            return (
                <UserFollowModalBaseRow username={username} key={username}/>
            )
        })
    }

    render() {

        return (
            <Modal relationTo="all-followers">
                
                <ModalTitle>
                    <h5 className="modal-title">All users that are following <span><b>{this.props.userProfileInfo.username}</b></span>:</h5>
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

export default withState(UserFollowersModal);

