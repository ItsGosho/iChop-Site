import React from 'react';
import CreateReactClass from "create-react-class";

let Modal = CreateReactClass({



    render() {
        let {relationTo} = this.props;

        return (
            <div className="modal fade" id={relationTo} tabIndex="-1" role="dialog" aria-hidden="true">
                <div className="modal-dialog" role="document">
                    <div className="modal-content">

                        {this.props.children}

                    </div>
                </div>
            </div>
        );
    }

});

export default Modal;