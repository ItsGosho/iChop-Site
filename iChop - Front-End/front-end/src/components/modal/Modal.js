import React from 'react';
import CreateReactClass from "create-react-class";

let Modal = CreateReactClass({


    render() {
        let {id, relationTo, title} = this.props;

        return (
            <div className="modal fade" id={id} tabIndex="-1" role="dialog"
                 aria-labelledby={relationTo} aria-hidden="true">

                <div className="modal-dialog" role="document">
                    <div className="modal-content">


                        <div className="modal-header">
                            <h5 className="modal-title" id={id}>{title}</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                    </div>
                </div>
            </div>
        );
    }

});

export default Modal;