import React, {Component, Fragment} from 'react';
import ModalOpen from "../../../../modal/ModalOpen";
import Roles from "../../../../../constants/enums/roles.constants";
import withState from "../../../../../hocs/with.state";
import ReportModal from "../../../../modal/ReportModal";
import ReportServices from "../../../../../services/report.services";
import {Redirect} from "react-router-dom";
import RoutingURLs from "../../../../../constants/routing/routing.constants";
import ThreadServices from "../../../../../services/thread.services";

class ThreadButtonsLeft extends Component {

    constructor(props) {
        super(props);

        this.state = {
            hasReported: false,
            isDeleteSuccessful: false
        };

        this.onReport = this.onReport.bind(this);
        this.onDelete = this.onDelete.bind(this);
    }

    async componentWillReceiveProps(nextProps, nextContext) {
        let {username: authenticatedUsername} = this.props.authenticatedUserInfo;
        let {id} = this.props.threadRead;
        let hasReported = await ReportServices.hasReportedThread(authenticatedUsername, id);
        this.setState({hasReported});
    }

    async onDelete() {
        let {id} = this.props.threadRead;

        let response = await ThreadServices.deleteById(id);
        this.setState({isDeleteSuccessful: response.successful})
    }

    async onReport(reason) {
        let {id} = this.props.threadRead;

        let response = await ReportServices.reportThread(id, reason);

        if (response.successful) {
            this.setState({hasReported: true})
        }
    }

    render() {
        let {id} = this.props.threadRead;
        let {hasReported, isDeleteSuccessful} = this.state;
        let isAuthenticated = this.props.authenticatedUserInfo.authority !== Roles.GUEST;
        let hasRoleModerator = this.props.authenticatedUserInfo.authority !== Roles.USER;

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
                                        className="btn btn-light btn-sm thread-delete_button" onClick={this.onDelete}>
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
                        if (isAuthenticated && !hasReported) {
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

                {isDeleteSuccessful ? (<Redirect to={RoutingURLs.HOME} push/>) : null}

            </Fragment>
        );
    }

}

export default withState(ThreadButtonsLeft);