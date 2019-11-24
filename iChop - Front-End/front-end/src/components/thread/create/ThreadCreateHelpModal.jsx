import React from 'react';
import Modal from "../../modal/Modal";
import ModalTitle from "../../modal/ModalTitle";
import ModalBody from "../../modal/ModalBody";
import ModalFooter from "../../modal/ModalFooter";
import ModalClose from "../../modal/ModalClose";

const ThreadCreateHelpModal = () => (
    <Modal relationTo={'help'}>

        <ModalTitle>
            <span>How to show only first X rows from thread on the news page:</span>
        </ModalTitle>

        <ModalBody>
            <span>If you dont want all of the content to be show on the news page /better/ ,place SHOW_TO_NOW whether you want to end.</span>
        </ModalBody>

        <ModalFooter>
            <ModalClose/>
        </ModalFooter>

    </Modal>
);

export default ThreadCreateHelpModal;