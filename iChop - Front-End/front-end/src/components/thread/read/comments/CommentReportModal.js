import React, {Component} from 'react';
import Modal from "../../../modal/Modal";
import ModalTitle from "../../../modal/ModalTitle";
import ModalBody from "../../../modal/ModalBody";
import ModalFooter from "../../../modal/ModalFooter";
import ModalCloseButton from "../../../modal/ModalCloseButton";

class CommentReportModal extends Component {


    render() {

        return (
            <Modal relationTo={'reportComment'}>
                <ModalTitle>Report</ModalTitle>

                <ModalBody>
                   <textarea
                       className="thread-comments-modal"
                       name="reason"
                       placeholder="Reason..."/>
                </ModalBody>

                <ModalFooter>
                    <button
                        className="btn btn-danger">Report
                    </button>
                    <ModalCloseButton/>
                </ModalFooter>
            </Modal>
        );
    }

}

export default CommentReportModal;