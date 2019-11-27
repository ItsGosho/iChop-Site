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
        };

        this.onInput = this.onInput.bind(this);
        this.onReport = this.onReport.bind(this);
    }

    onInput(event) {
        let reason = event.target.value;
        this.setState({reason});
    };

    onReport() {
        let {reason} = this.state;
        this.props.onReport(reason);
    }

    render() {
        let {relationTo, value} = this.props;

        return (
            <Modal relationTo={relationTo}>
                <ModalTitle>Report</ModalTitle>

                <ModalBody>
                   <textarea
                       className="report-modal"
                       value={value}
                       onChange={this.onInput}
                       placeholder="Reason..."/>
                </ModalBody>

                <ModalFooter>
                    <button className="btn btn-danger" data-dismiss="modal" onClick={this.onReport}>Report</button>
                    <ModalClose/>
                </ModalFooter>
            </Modal>
        );
    }

}

export default ReportModal;

ReportModal.propTypes = {
    id: PropTypes.string,
    relationTo: PropTypes.string,
    value: PropTypes.string,
    onReport: PropTypes.func,
};