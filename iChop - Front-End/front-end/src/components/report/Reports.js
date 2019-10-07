import React, {Component, Fragment} from 'react';
import {Link, Route, Switch} from "react-router-dom";
import RoutingURLs from "../../constants/routing.constants";
import ReportsThread from "./ReportsThread";

class Reports extends Component {


    render() {

        return (
            <Fragment>
                <nav className="navbar navbar-expand-lg navbar-light bg-light"
                     style={{'marginLeft': '40px', 'marginRight': '40px'}}>

                    <a className="navbar-brand" href="#">⚠ Reports</a>

                    <button className="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#nav-reports-categories"
                            aria-controls="nav-reports-categories" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"/>
                    </button>

                    <div className="collapse navbar-collapse" id="nav-reports-categories">

                        <ul className="navbar-nav mr-auto">

                            <li className="nav-item active">
                                <Link className="nav-link" to={RoutingURLs.THREAD.REPORT.ALL}>Threads<span
                                    className="sr-only">(current)</span></Link>
                            </li>

                            <li className="nav-item active">
                                <Link className="nav-link" to={RoutingURLs.COMMENT.REPORT.ALL}>Comments<span
                                    className="sr-only">(current)</span></Link>
                            </li>

                            <li className="nav-item active">
                                <Link className="nav-link" to={RoutingURLs.POST.REPORT.ALL}>Posts<span
                                    className="sr-only">(current)</span></Link>
                            </li>

                        </ul>

                        <form className="form-inline my-2 my-lg-0">
                            <input className="form-control mr-sm-2" id="input-searchByUsername-usersAll" type="search"
                                   placeholder="Search by username" aria-label="Search"/>
                        </form>

                    </div>
                </nav>

                <Switch>
                    <Route exact path={RoutingURLs.THREAD.REPORT.ALL} component={() => (<ReportsThread/>)}/>
                    <Route exact path={RoutingURLs.COMMENT.REPORT.ALL} component={() => (<h1>Comment Reports!</h1>)}/>
                    <Route exact path={RoutingURLs.POST.REPORT.ALL} component={() => (<h1>Post Reports!</h1>)}/>
                </Switch>

            </Fragment>
        );
    }

}

export default Reports;