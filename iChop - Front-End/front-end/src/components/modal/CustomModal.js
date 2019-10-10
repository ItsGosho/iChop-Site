import React, {Component} from 'react';

class CustomModal extends Component {


    render() {
        let {id, relationTo} = this.props;

        return (
            <div className="modal fade" id={id} tabIndex="-1" role="dialog"
                 aria-labelledby={relationTo} aria-hidden="true">

                <div className="modal-dialog" role="document">
                    <div className="modal-content">

                    </div>
                </div>
            </div>
        );
    }

}

export default CustomModal;