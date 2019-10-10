import React from 'react';
import CreateReactClass from "create-react-class";

let Modal = CreateReactClass({


    render() {
        let {relationTo, title} = this.props;

        return (
            <div className="modal fade" id={relationTo} tabIndex="-1" role="dialog" aria-hidden="true">

                <div className="modal-dialog" role="document">
                    <div className="modal-content">


                        <div className="modal-header">
                            <h5 className="modal-title">{title}</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                        {this.props.children}

                    </div>
                </div>
            </div>
        );
    }

});

export default Modal;