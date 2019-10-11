import CreateReactClass from "create-react-class";
import React from "react";

let ModalClose = CreateReactClass({


    render() {

        return (
            <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
        );
    }

});

export default ModalClose;