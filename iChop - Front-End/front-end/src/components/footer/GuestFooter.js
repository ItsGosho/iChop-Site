import React, {Component, Fragment} from 'react';

class CHANGE extends Component {


    render() {

        return (
            <Fragment>
                <div className="col-md-2 mx-auto">

                    <!-- Links -->
                    <h5 className="font-weight-bold text-uppercase mt-3 mb-4">VISIT</h5>

                    <ul className="list-unstyled">
                        <li>
                            <a href="?login=require">
                                <small>🔐</small>
                                Login</a>
                        </li>
                        <li>
                            <a href="?register=require">
                                <small>🗝️</small>
                                Register</a>
                        </li>
                        <li>
                            <a href="?forgotten-password=require">
                                <small>🏷️</small>
                                Forgotten Password</a>
                        </li>
                    </ul>

                </div>
            </Fragment>
        );
    }

}

export default CHANGE;