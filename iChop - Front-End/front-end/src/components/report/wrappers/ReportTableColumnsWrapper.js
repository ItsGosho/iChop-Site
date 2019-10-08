import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";
import formatDate from "dateformat";
import ReportTableActionButtons from "../ReportTableActionButtons";
import CreateReactClass from "create-react-class";
import RoutingURLs from "../../../constants/routing.constants";


let ReportTableColumnsWrapper = CreateReactClass({
    render() {
        let {entityName,index,reason, creatorUsername, reportDate} = this.props;
        let creatorProfile = RoutingURLs.USER.PROFILE.VIEW.replace(':username', creatorUsername);

        return (
            <tr key={index}>

                {this.props.children}

                <td width="300px">
                    <div style={{
                        'overflow': 'scroll',
                        'width': '100%',
                        "maxHeight": '100px'
                    }}>{reason}</div>
                </td>

                <td>
                    <Link to={creatorProfile}>{creatorUsername}</Link>
                </td>

                <td>{formatDate(reportDate, 'dd mmm,yyyy')}</td>

                <ReportTableActionButtons entityName={entityName} onDeleteEntity={null}
                                          onDeleteReport={null}/>
            </tr>
        );
    }
});


export default ReportTableColumnsWrapper;