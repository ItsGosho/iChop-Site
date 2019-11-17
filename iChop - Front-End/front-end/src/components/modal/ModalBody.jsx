import React from 'react';
import CreateReactClass from "create-react-class";

let ModalBody = CreateReactClass({


    render() {

        return (
            <div className="modal-body">
                {this.props.children}
            </div>
        );
    }

});

export default ModalBody;