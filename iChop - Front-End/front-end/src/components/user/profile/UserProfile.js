import React, {Component} from 'react';
import UserProfileLeftSideInformation from "./UserProfileLeftSideInformation";
import './UserProfile.css';

class UserProfile extends Component {


    render() {

        return (
            <div className="container container-user-profile">

                <div className="row">

                    <UserProfileLeftSideInformation/>

                    {/*<th:block th:insert="user/profile/partials/user-profile-left-side-information"></th:block>

                    <th:block th:insert="user/profile/partials/user-profile-central-content"></th:block>
*/}
                </div>
            </div>
        );
    }

}

export default UserProfile;