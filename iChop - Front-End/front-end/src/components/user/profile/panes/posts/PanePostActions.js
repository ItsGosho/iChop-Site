import React, {Component, Fragment} from 'react';
import dateFormat from 'dateformat';
import Roles from "../../../../../constants/roles.constants";
import './PanePostActions.css'
import PropTypes from "prop-types";
import PanePost from "./PanePost";

class PanePostActions extends Component {

    constructor(props) {
        super(props);

        this.onDelete = this.onDelete.bind(this);
        this.onReport = this.onReport.bind(this);
    }


    onDelete(post) {
        console.log('Delete!');
    }

    onReport(post) {
        console.log('Report!');
    }

    render() {
        let username = 'ItsGosho';

        let postUserUsername = 'Roki';
        let postCreatorUsername = 'Joni';
        let role = Roles.MODERATOR;

        let post = null;

        return (
            <Fragment>
                {
                    (() => {
                        let isPostCreator = postCreatorUsername === username;
                        let isPostOnHisProfile = postUserUsername === username;
                        let isModerator = role === Roles.MODERATOR;

                        if (isPostCreator || isPostOnHisProfile || isModerator) {
                            return (
                                <button className="control-button" onClick={() => {
                                    this.onDelete(post)
                                }}>❌Delete</button>
                            );
                        }
                    })()
                }

                <button className="control-button" onClick={() => {
                    this.onReport(post)
                }}>🎌Report
                </button>
            </Fragment>
        );
    }
}

export default PanePostActions;