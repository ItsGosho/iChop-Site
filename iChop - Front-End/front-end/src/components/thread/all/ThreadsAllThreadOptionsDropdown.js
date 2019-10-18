import React, {Component, Fragment} from 'react';

class ThreadsAllThreadOptionsDropdown extends Component {

    constructor(props) {
        super(props);

        this.onDelete = this.onDelete.bind(this);
    }


    onDelete(id) {
        console.log('Delete -> ' + id);
    }

    render() {
        let {id} = this.props;

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
                                        this.onDelete(id);
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

export default ThreadsAllThreadOptionsDropdown;