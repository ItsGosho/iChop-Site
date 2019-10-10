import React, {Component, Fragment} from 'react';
import Modal from "../../modal/Modal";
import ModalOpen from "../../modal/ModalOpen";
import ModalTitle from "../../modal/ModalTitle";
import ModalBody from "../../modal/ModalBody";
import ModalFooter from "../../modal/ModalFooter";

class ThreadCreateHelperButton extends Component {

    render() {

        return (
            <Fragment>

                <ModalOpen relationTo={'help'} title={'Help'}>
                    <span>❓</span>
                </ModalOpen>

                <Modal relationTo={'help'}>

                    <ModalTitle>
                        <span>How to show only first X rows from thread on the news page:</span>
                    </ModalTitle>

                    <ModalBody>
                        <span>If you dont want all of the content to be show on the news page /better/ ,place SHOW_TO_NOW whether you want to end.</span>
                    </ModalBody>

                    <ModalFooter>
                        <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                    </ModalFooter>

                </Modal>
            </Fragment>
        );
    }

}

export default ThreadCreateHelperButton;