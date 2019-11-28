import React from 'react';
import {Link} from "react-router-dom";
import formatDate from "dateformat";
import ReportActionButtons from "./ReportActionButtons";
import CreateReactClass from "create-react-class";
import RoutingURLs from "../../../constants/routing/routing.constants";
import './ReportTableColumns.css'
import PropTypes, {instanceOf} from 'prop-types'

const REPORTED_ON_DATE = 'dd mmm,yyyy';

let ReportTableColumns = CreateReactClass({

    render() {
        let {index, creatorUsername, reason, reportedOn, type, onDeleteReport} = this.props;
        let creatorProfile = RoutingURLs.USER.PROFILE.VIEW(creatorUsername);

        return (
            <tr key={index}>

                {this.props.children}

                <td className="reason-td">
                    <div className="reason-div">{reason}</div>
                </td>

                <td>
                    <Link to={creatorProfile}>{creatorUsername}</Link>
                </td>

                <td>{type}</td>

                <td>{formatDate(reportedOn, REPORTED_ON_DATE)}</td>

                <ReportActionButtons onDeleteReport={onDeleteReport}/>
            </tr>
        );
    }

});


export default ReportTableColumns;

ReportTableColumns.propTypes = {
    creatorUsername: PropTypes.string,
    reason: PropTypes.string,
    type: PropTypes.string,
    reportedOn: instanceOf(Date),
};