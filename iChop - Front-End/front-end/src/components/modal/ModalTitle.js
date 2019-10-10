import React from 'react';
import CreateReactClass from "create-react-class";


let ModalTitle = CreateReactClass({

    render() {

        return (

            <div className="modal-header">
                {this.props.children}
                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        );
    }

});


export default ModalTitle;