import React, {Component} from 'react';
import UserProfileLeftSideInformation from "./UserProfileLeftSideInformation";
import './UserProfile.css';
import UserProfileCentralContent from "./UserProfileCentralContent";
import {withRouter} from "react-router-dom";

class UserProfile extends Component {


    render() {
        let {username} = this.props.match.params;
        console.log(username);

        return (
            <div className="container container-user-profile">

                <div className="row">

                    <UserProfileLeftSideInformation/>
                    <UserProfileCentralContent/>

                </div>
            </div>
        );
    }

}

export default withRouter(UserProfile);