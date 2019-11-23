import React from 'react';
import PropTypes from 'prop-types';


const Modal = ({relationTo, children}) => (
    <div className="modal fade" id={relationTo} tabIndex="-1" role="dialog" aria-hidden="true">
        <div className="modal-dialog" role="document">
            <div className="modal-content">{children}</div>
        </div>
    </div>
);

export default Modal;

Modal.propTypes = {
    relationTo: PropTypes.string
};