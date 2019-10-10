import CreateReactClass from "create-react-class";
import React from "react";

let ModalCloseButton = CreateReactClass({


    render() {

        return (
            <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
        );
    }

});

export default ModalCloseButton;