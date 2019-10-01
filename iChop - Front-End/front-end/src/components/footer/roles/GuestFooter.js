import React, {Component, Fragment} from 'react';

class GuestFooter extends Component {


    render() {

        return (
            <Fragment>

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
            </Fragment>
        );
    }

}

export default GuestFooter;