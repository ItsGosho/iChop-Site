import React, {Component, Fragment} from 'react';
import Modal from "../../modal/Modal";
import ModalOpen from "../../modal/ModalOpen";

class ThreadCreateHelperButton extends Component {

    constructor(props) {
        super(props);

        this.state = {
            helpTitle: 'How to show only first X rows from thread on the news page:',
            helpText: 'If you dont want all of the content to be show on the news page /better/ ,place SHOW_TO_NOW whether you want to end.'
        }
    }


    render() {

        return (
            <Fragment>

                <a href="" type="button" data-toggle="modal" data-target="#exampleModal">
                    ❓
                </a>

                <ModalOpen relationTo={'help'} title={'Help'}>

                </ModalOpen>

                <Modal>



                </Modal>

                <div className="modal fade" id="exampleModal" tabIndex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">

                            <div className="modal-header">
                                <h5 className="modal-title" id="exampleModalLabel">{this.state.helpTitle}</h5>
                                <button type="button" className="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>

                            <div className="modal-body">
                                {this.state.helpText}
                            </div>

                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </Fragment>
        );
    }

}

export default ThreadCreateHelperButton;