import React from 'react';
import {Link} from "react-router-dom";
import formatDate from "dateformat";
import ReportActionButtons from "./ReportActionButtons";
import RoutingURLs from "../../../constants/routing/routing.constants";
import './ReportTableColumns.css'
import PropTypes, {instanceOf} from 'prop-types'

const REPORTED_ON_DATE = 'dd mmm,yyyy';

const ReportTableColumns = (props) => {
    let {id,index, creatorUsername, reason, reportedOn, type, onDeleteReport} = props;
    let creatorProfile = RoutingURLs.USER.PROFILE.VIEW(creatorUsername);

    return (
        <tr key={index}>

            <td className="reason-td">
                <div className="reason-div">{reason}</div>
            </td>

            <td>
                <Link to={creatorProfile}>{creatorUsername}</Link>
            </td>

            <td>{type}</td>

            <td>{formatDate(reportedOn, REPORTED_ON_DATE)}</td>

            <ReportActionButtons id={id} type={type} onDeleteReport={onDeleteReport}/>
        </tr>
    );
};


export default ReportTableColumns;

ReportTableColumns.propTypes = {
    id: PropTypes.string,
    creatorUsername: PropTypes.string,
    reason: PropTypes.string,
    type: PropTypes.string,
    reportedOn: instanceOf(Date),
    onDeleteReport: PropTypes.func,
};