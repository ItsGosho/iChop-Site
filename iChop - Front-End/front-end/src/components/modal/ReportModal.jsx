import React, {Component} from 'react';
import Modal from "./Modal";
import ModalTitle from "./ModalTitle";
import ModalBody from "./ModalBody";
import ModalFooter from "./ModalFooter";
import ModalClose from "./ModalClose";
import './ReportModal.css';
import PropTypes from "prop-types";

class ReportModal extends Component {

    constructor(props) {
        super(props);

        this.state = {
            reason: ''
        }
    }

    render() {
        let {relationTo, value, onReport} = this.props;

        return (
            <Modal relationTo={relationTo}>
                <ModalTitle>Report</ModalTitle>

                <ModalBody>
                   <textarea
                       className="report-modal"
                       value={value}
                       onChange={(event) => {
                           this.setState({reason: event.target.value});
                       }}
                       placeholder="Reason..."/>
                </ModalBody>

                <ModalFooter>
                    <button className="btn btn-danger" onClick={() => {
                        onReport(this.state.reason)
                    }}>Report
                    </button>
                    <ModalClose/>
                </ModalFooter>
            </Modal>
        );
    }

}

ReportModal.propTypes = {
    id: PropTypes.string,
    relationTo: PropTypes.string,
    value: PropTypes.string,
    onReport: PropTypes.func,
};

export default ReportModal;