import React from 'react';
import CreateReactClass from "create-react-class";


let ModalFooter = CreateReactClass({

    render() {

        return (
            <div className="modal-footer">
                {this.props.children}
            </div>
        );
    }

});


export default ModalFooter;