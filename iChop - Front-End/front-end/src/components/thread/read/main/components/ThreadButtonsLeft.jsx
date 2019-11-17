import React, {Component, Fragment} from 'react';
import ThreadReportModal from "./ThreadReportModal";
import ModalOpen from "../../../../modal/ModalOpen";

class ThreadButtonsLeft extends Component {


    render() {
        let isAuthenticated = true;
        let hasRoleModerator = true;

        let isReportedThreadAlready = false;
        return (
            <Fragment>
                {
                    (() => {

                        if (isAuthenticated && hasRoleModerator) {
                            return (
                                <button
                                    className="btn btn-secondary btn-sm dropdown-toggle"
                                    type="button"
                                    data-toggle="dropdown" aria-haspopup="true"
                                    aria-expanded="false">
                                    <small>⚙</small>
                                    <span>Options</span>
                                </button>
                            );
                        }

                    })()
                }

                <div className="dropdown-menu">
                    {
                        (() => {
                            if (isAuthenticated && hasRoleModerator) {
                                return (
                                    <button
                                        type="button"
                                        className="btn btn-light btn-sm thread-delete_button">
                                        <small>❌</small>
                                        <span>Delete</span>
                                    </button>
                                );
                            }
                        })()
                    }

                </div>

                {
                    (() => {
                        if (isAuthenticated && !isReportedThreadAlready) {
                            return (
                                <Fragment>

                                    <ModalOpen relationTo={'reportThread'}>
                                        <button className="btn btn-sm thread-report_button">
                                            <small>⚠</small>
                                            Report
                                        </button>
                                    </ModalOpen>


                                    <ThreadReportModal/>

                                </Fragment>
                            );
                        }
                    })()
                }
            </Fragment>
        );
    }

}

export default ThreadButtonsLeft;