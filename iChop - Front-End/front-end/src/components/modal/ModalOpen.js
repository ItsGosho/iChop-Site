import React from 'react';
import CreateReactClass from "create-react-class";
import './ModalOpen.css'

let ModalOpen = CreateReactClass({


    render() {
        let {relationTo, title} = this.props;

        return (
            <button type="button" className="modal-open-button"
                    data-toggle="modal"
                    data-target={'#' + relationTo}
                    title={title} onClick={(event => event.preventDefault())}>

                {this.props.children}

            </button>
        );
    }

});

export default ModalOpen;