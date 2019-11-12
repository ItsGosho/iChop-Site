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
        let {relationTo,value} = this.props;

        let onChange = (event) => {
            this.setState({[event.target.name]: event.target.value}, () => {
                this.props.onValueChange(this.state.reason);
            });
        };

        return (
            <Modal relationTo={relationTo}>
                <ModalTitle>Report</ModalTitle>

                <ModalBody>
                   <textarea
                       className="report-modal"
                       name="reason"
                       value={value}
                       onChange={onChange}
                       placeholder="Reason..."/>
                </ModalBody>

                <ModalFooter>
                    <button className="btn btn-danger">Report</button>
                    <ModalClose/>
                </ModalFooter>
            </Modal>
        );
    }

}

ReportModal.propTypes = {
    relationTo: PropTypes.string,
    value: PropTypes.string,
    onValueChange: PropTypes.func
};

export default ReportModal;