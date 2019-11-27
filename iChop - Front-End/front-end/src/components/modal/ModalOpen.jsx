import React from 'react';
import PropTypes from 'prop-types';
import './ModalOpen.css'


const ModalOpen = ({relationTo, title, children}) => (
    <button type="button"
            className="modal-open-button"
            data-toggle="modal"
            data-target={'#' + relationTo}
            title={title} onClick={(event => event.preventDefault())}>

        {children}

    </button>
);

export default ModalOpen;

ModalOpen.propTypes = {
    relationTo: PropTypes.string,
    title: PropTypes.string,
};