import React, {Component, Fragment} from 'react';
import TextEditorCommands from "./text.editor.commands.constants";
import FormHoc from "../../hocs/form.hoc";
import CommandExecutorHoc from "./command.executor.hoc";
import Modal from "../modal/Modal";
import ModalBody from "../modal/ModalBody";
import ModalFooter from "../modal/ModalFooter";
import ModalTitle from "../modal/ModalTitle";

class TextEditorInsertImageModal extends Component {

    constructor(props) {
        super(props);

        this.state = {
            modal: ''
        };

        this.proceedInsertImage = this.proceedInsertImage.bind(this);
    }

    proceedInsertImage(event) {
        event.preventDefault();
        let {link} = this.props.formData;

        document.execCommand(TextEditorCommands.INSERT_IMAGE, false, link);
        this.setState({'modal': 'hide'});

        try {
            let image = document.querySelector(`img[src='${link}']`);
            image.style['height'] = '100%';
            image.style['width'] = '100%';
            image.style['object-fit'] = 'contain';
        } catch (e) {

        }
    }

    render() {
        let {preventDefault} = this.props;
        let {onChange} = this.props.formMethods;

        return (
            <Fragment>

                <a href="#" data-toggle="modal"
                   data-target="#insertImage"
                   title="Insert Image" onClick={preventDefault}>
                    <i className="material-icons">photo</i>
                </a>

                <Modal relationTo={'insertImage'}>


                    <ModalTitle>
                        <h5>Image URL</h5>
                    </ModalTitle>

                    <ModalBody>
                        <div className="input-group mb-3">
                            <input id="input-insertImage-textEditor" type="text" className="form-control"
                                   onChange={onChange} name='link'
                                   aria-describedby="basic-addon1" placeholder=""/>
                        </div>
                    </ModalBody>

                    <ModalFooter>
                        <button type="button" className="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <button type="button" data-dismiss="modal"
                                className="btn btn-primary" onClick={this.proceedInsertImage}>Insert
                        </button>
                    </ModalFooter>

                </Modal>

            </Fragment>
        );
    }
}

export default FormHoc(CommandExecutorHoc(TextEditorInsertImageModal));