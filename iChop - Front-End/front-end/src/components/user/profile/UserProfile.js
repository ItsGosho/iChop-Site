import React, {Component} from 'react';
import UserProfileLeftSideInformation from "./UserProfileLeftSideInformation";
import './UserProfile.css';
import UserProfileCentralContent from "./UserProfileCentralContent";

class UserProfile extends Component {


    render() {

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

export default UserProfile;