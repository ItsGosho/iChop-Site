import React, {Component, Fragment} from 'react';
import {Link} from "react-router-dom";

class PlayerProfileNavigation extends Component {


    render() {

        return (
            <Fragment>
                <div className="col-md-auto">
                    <Link to={''} className="btn btn-warning btn-statistics" type="button">Statistics
                    </Link>
                </div>

                <div className="col-md-auto">
                    <div className="row">

                    </div>
                </div>
            </Fragment>
        );
    }

}

export default PlayerProfileNavigation;