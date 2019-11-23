import React from 'react';


const ModalTitle = ({children}) => (
    <div className="modal-header">

        {children}

        <button type="button"
                className="close"
                data-dismiss="modal"
                aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>

    </div>
);


export default ModalTitle;