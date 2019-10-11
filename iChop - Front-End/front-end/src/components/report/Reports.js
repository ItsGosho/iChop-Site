import React, {Component, Fragment} from 'react';
import {Route, Switch} from "react-router-dom";
import RoutingURLs from "../../constants/routing.constants";
import ReportsThread from "./ReportsThread";
import ReportsPost from "./ReportsPost";
import ReportsComment from "./ReportsComment";
import ReportNav from "./other/ReportNav";

class Reports extends Component {


    render() {

        return (
            <Fragment>
                <ReportNav/>

                <Switch>
                    <Route exact path={RoutingURLs.THREAD.REPORT.ALL} component={() => (<ReportsThread/>)}/>
                    <Route exact path={RoutingURLs.COMMENT.REPORT.ALL} component={() => (<ReportsComment/>)}/>
                    <Route exact path={RoutingURLs.POST.REPORT.ALL} component={() => (<ReportsPost/>)}/>
                </Switch>

            </Fragment>
        );
    }

}

export default Reports;