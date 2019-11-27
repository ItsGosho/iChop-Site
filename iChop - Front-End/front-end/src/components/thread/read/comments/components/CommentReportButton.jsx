import React, {Component, Fragment} from 'react';
import ModalOpen from "../../../../modal/ModalOpen";
import ReportModal from "../../../../modal/ReportModal";
import ReportServices from "../../../../../services/report.services";
import withState from "../../../../../hocs/with.state";
import Roles from "../../../../../constants/enums/roles.constants";
import PropTypes from 'prop-types';


class CommentReportButton extends Component {

    constructor(props) {
        super(props);

        this.state = {
            hasReported: false
        };

        this.onReport = this.onReport.bind(this);
    }

    async componentWillReceiveProps(nextProps, nextContext) {
        let {username: authenticatedUsername} = this.props.authenticatedUserInfo;
        let {id} = this.props;
        let hasReported = await ReportServices.hasReportedThreadComment(authenticatedUsername, id);
        this.setState({hasReported});
    }

    async onReport(reason) {
        let {id} = this.props;

        let response = await ReportServices.reportThreadComment(id, reason);

        if (response.successful) {
            this.setState({hasReported: true})
        }
    }

    render() {
        let {authority: authenticatedAuthority} = this.props.authenticatedUserInfo;
        let {id} = this.props;
        let {hasReported} = this.state;

        return (
            <Fragment>

                {authenticatedAuthority !== Roles.GUEST && !hasReported ? (
                    <div
                        className="thread-comments-button_report">

                        <ModalOpen relationTo={id} title={'Report Comment'}>
                            <button
                                className="btn btn-sm">
                                <small>⚠</small>
                                <span>Report</span>
                            </button>
                        </ModalOpen>

                        <ReportModal relationTo={id}
                                     onReport={this.onReport}/>
                    </div>
                ) : null}

            </Fragment>
        );
    }

}

export default withState(CommentReportButton);

CommentReportButton.propTypes = {
    id: PropTypes.string,
};