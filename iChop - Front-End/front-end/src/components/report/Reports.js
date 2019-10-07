import React, {Component, Fragment} from 'react';

class Reports extends Component {


    render() {

        return (
            <Fragment>
                <nav className="navbar navbar-expand-lg navbar-light bg-light"
                     style="margin-left: 40px;margin-right: 40px">
                    <a className="navbar-brand" href="#">⚠Reports</a>
                    <button className="navbar-toggler" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item active">
                                <a className="nav-link" href="/thread/reports/all">Threads<span
                                    className="sr-only">(current)</span></a>
                            </li>
                            <li className="nav-item active">
                                <a className="nav-link" href="/comment/reports/all">Comments<span
                                    className="sr-only">(current)</span></a>
                            </li>
                            <li className="nav-item active">
                                <a className="nav-link" href="/post/reports/all">Posts<span
                                    className="sr-only">(current)</span></a>
                            </li>
                        </ul>
                        <form className="form-inline my-2 my-lg-0">
                            <input className="form-control mr-sm-2" id="input-searchByUsername-usersAll" type="search"
                                   placeholder="Search by username" aria-label="Search">
                        </form>
                    </div>
                </nav>

                {/*TODO: insert here*/}
            </Fragment>
        );
    }

}

export default Reports;