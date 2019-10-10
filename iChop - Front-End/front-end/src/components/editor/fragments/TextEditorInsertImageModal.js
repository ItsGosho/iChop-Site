import React, {Component} from 'react';
import TextEditorCommands from "../text.editor.commands.constants";
import FormHoc from "../../../hocs/form.hoc";
import CommandExecutorHoc from "../command.executor.hoc";
import Modal from "../../modal/Modal";
import ModalBody from "../../modal/ModalBody";
import ModalFooter from "../../modal/ModalFooter";
import ModalTitle from "../../modal/ModalTitle";
import ModalCloseButton from "../../modal/ModalCloseButton";

class TextEditorInsertImageModal extends Component {

    constructor(props) {
        super(props);

        this.proceedInsertImage = this.proceedInsertImage.bind(this);
    }

    proceedInsertImage(event) {
        event.preventDefault();
        let {link} = this.props.formData;

        document.execCommand(TextEditorCommands.INSERT_IMAGE, false, link);

        try {
            let image = document.querySelector(`img[src='${link}']`);
            image.style['height'] = '100%';
            image.style['width'] = '100%';
            image.style['object-fit'] = 'contain';
        } catch (e) {

        }
    }

    render() {
        let {onChange} = this.props.formMethods;

        return (
            <Modal relationTo={'insertImage'}>

                <ModalTitle>
                    <h5>Image URL</h5>
                </ModalTitle>

                <ModalBody>
                    <div className="input-group mb-3">
                        <input type="text" className="form-control"
                               onChange={onChange} name='link'
                               aria-describedby="basic-addon1" placeholder=""/>
                    </div>
                </ModalBody>

                <ModalFooter>
                    <ModalCloseButton/>
                    <button type="button" data-dismiss="modal" className="btn btn-primary"
                            onClick={this.proceedInsertImage}>Insert
                    </button>
                </ModalFooter>

            </Modal>
        );
    }
}

export default FormHoc(CommandExecutorHoc(TextEditorInsertImageModal));