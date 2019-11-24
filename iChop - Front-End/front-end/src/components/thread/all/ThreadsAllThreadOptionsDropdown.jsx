import React, {Component, Fragment} from 'react';
import Roles from "../../../constants/enums/roles.constants";
import ThreadServices from "../../../services/thread.services";
import threadAllDispatchers from "../../../redux/dispatchers/thread.all.dispatchers";
import withDispatcher from "../../../hocs/with.dispatcher";

class ThreadsAllThreadOptionsDropdown extends Component {

    constructor(props) {
        super(props);

        this.onDelete = this.onDelete.bind(this);
    }


    async onDelete() {
        let {id} = this.props;
        let response = await ThreadServices.deleteById(id);

        if (response.successful) {
            this.props.removeFromAllById(id);
        }
    }

    render() {
        let {authority} = this.props.authenticatedUserInfo;

        return (
            <Fragment>
                {authority !== Roles.GUEST && authority !== Roles.USER ? (
                    <Fragment>

                        <button className="btn btn-warning btn-sm dropdown-toggle"
                                type="button"
                                data-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false">
                            <small>⚙</small>
                            <span>Options</span>
                        </button>

                        <div className="dropdown-menu">
                            <button type="button"
                                    className="btn btn-light btn-sm thread-delete_button"
                                    onClick={this.onDelete}>
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


export default withDispatcher(threadAllDispatchers)(ThreadsAllThreadOptionsDropdown);