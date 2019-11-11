import React, {Component, Fragment} from 'react';
import dateFormat from 'dateformat';
import Roles from "../../../../../constants/roles.constants";
import './PanePostActions.css'
import PropTypes from "prop-types";
import PanePost from "./PanePost";
import withState from "../../../../../hocs/with.state";

class PanePostActions extends Component {

    constructor(props) {
        super(props);

        this.onDelete = this.onDelete.bind(this);
        this.onReport = this.onReport.bind(this);
    }


    onDelete() {
        let {id} = this.props;
        console.log(`Delete post with ID: ${id}`);
    }

    onReport() {
        let {id} = this.props;

        console.log(`Report post with ID: ${id}`);
    }

    render() {
        let isPostCreator = this.props.authenticatedUserInfo.username === this.props.creatorUsername;
        let isPostOnCreatorProfile = this.props.authenticatedUserInfo.username === this.props.userProfileUsername;
        let isModerator = this.props.authenticatedUserInfo.authority === Roles.MODERATOR;

        return (
            <Fragment>

                {isPostCreator || isPostOnCreatorProfile || isModerator ? (
                    <button className="control-button" onClick={this.onDelete}>❌Delete</button>
                ) : null}

                <button className="control-button" onClick={this.onReport}>🎌Report</button>

            </Fragment>
        );
    }
}

export default withState(PanePostActions);