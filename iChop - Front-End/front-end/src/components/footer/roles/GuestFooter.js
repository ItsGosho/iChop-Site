import React, {Component, Fragment} from 'react';
import FooterSideListWrapper from "../wrappers/FooterSideListWrapper";

class GuestFooter extends Component {


    render() {

        return (
            <Fragment>

                <FooterSideListWrapper text={'VISIT'}>
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

                </FooterSideListWrapper>
            </Fragment>
        );
    }

}

export default GuestFooter;