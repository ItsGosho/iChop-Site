import React, {Component, Fragment} from 'react';

class ThreadsAllOptionsDropdown extends Component {

    constructor(props) {
        super(props);

        this.onDelete = this.onDelete.bind(this);
    }


    onDelete() {
        console.log('Delete');
    }

    render() {
        let isAuthenticated = true;
        let hasAtLeastModeratorRole = true;

        return (
            <Fragment>
                {isAuthenticated && hasAtLeastModeratorRole ? (
                    <Fragment>

                        <button className="btn btn-warning btn-sm dropdown-toggle"
                                type="button"
                                data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
                            <small>⚙</small>
                            <span>Options</span>
                        </button>

                        <div className="dropdown-menu">
                            <button type="button"
                                    className="btn btn-light btn-sm thread-delete_button"
                                    onClick={() => {
                                        this.onDelete();
                                    }}>
                                <small>❌</small>
                                <span>Delete</span>
                            </button>
                        </div>

                    </Fragment>
                ) : null}
            </Fragment>
        );
    }

}

export default ThreadsAllOptionsDropdown;