import React, {Component, Fragment} from 'react';
import ModalOpen from "../../../../modal/ModalOpen";
import Roles from "../../../../../constants/enums/roles.constants";
import withState from "../../../../../hocs/with.state";
import ReportModal from "../../../../modal/ReportModal";

class ThreadButtonsLeft extends Component {

    constructor(props) {
        super(props);

        this.onReport = this.onReport.bind(this);
    }


    async onReport(reason) {
        let {id} = this.props.threadRead;

        /*TODO: REPORT [THREAD!]*/
    }

    render() {
        let {id} = this.props.threadRead;
        let isAuthenticated = this.props.authenticatedUserInfo.authority !== Roles.GUEST;
        let hasRoleModerator = this.props.authenticatedUserInfo.authority !== Roles.USER;

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

                                    <ModalOpen relationTo={id} title={'Report Thread'}>
                                        <button className="btn btn-sm thread-report_button">
                                            <small>⚠</small>
                                            Report
                                        </button>
                                    </ModalOpen>

                                    <ReportModal relationTo={id}
                                                 onReport={this.onReport}/>

                                </Fragment>
                            );
                        }
                    })()
                }
            </Fragment>
        );
    }

}

export default withState(ThreadButtonsLeft);