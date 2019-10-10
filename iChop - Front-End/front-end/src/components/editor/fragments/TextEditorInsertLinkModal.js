import React, {Component} from 'react';
import CommandExecutorHoc from "../command.executor.hoc";
import FormHoc from "../../../hocs/form.hoc";
import TextEditorCommands from "../text.editor.commands.constants";
import Modal from "../../modal/Modal";
import ModalTitle from "../../modal/ModalTitle";
import ModalBody from "../../modal/ModalBody";
import ModalFooter from "../../modal/ModalFooter";
import ModalCloseButton from "../../modal/ModalCloseButton";

class TextEditorInsertLinkModal extends Component {

    constructor(props) {
        super(props);

        this.proceedInsertLink = this.proceedInsertLink.bind(this);
    }

    proceedInsertLink(event) {
        event.preventDefault();
        let {link} = this.props.formData;

        document.execCommand(TextEditorCommands.CREATE_LINK, false, link);
    }

    render() {
        let {onChange} = this.props.formMethods;

        return (
            <Modal relationTo={'insertLink'}>

                <ModalTitle>
                    <span>Insert Link</span>
                </ModalTitle>

                <ModalBody>
                    <div className="input-group mb-3">
                        <input type="text" className="form-control"
                               aria-describedby="basic-addon1"
                               onChange={onChange} name='link'
                               placeholder="Example: https://youtube.com..."/>
                    </div>
                </ModalBody>

                <ModalFooter>
                    <ModalCloseButton/>
                    <button  type="button" data-dismiss="modal"
                            className="btn btn-primary" onClick={this.proceedInsertLink}>Insert
                    </button>
                </ModalFooter>

            </Modal>
        );
    }
}

export default FormHoc(CommandExecutorHoc(TextEditorInsertLinkModal));