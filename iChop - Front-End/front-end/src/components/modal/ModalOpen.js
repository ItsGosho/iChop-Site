import React from 'react';
import CreateReactClass from "create-react-class";

let ModalOpen = CreateReactClass({


    render() {
        let {relationTo, title} = this.props;

        return (
            <button style={{'all': 'initial'}}
                    data-toggle="modal"
                    data-target={'#' + relationTo}
                    title={title} onClick={(event => event.preventDefault())}>
                {this.props.children}
            </button>
        );
    }

});

export default ModalOpen;