import React, {Component} from 'react';
import UserProfileLeftSideInformation from "./UserProfileLeftSideInformation";
import './UserProfile.css';
import UserProfileCentralContent from "./UserProfileCentralContent";
import {withRouter} from "react-router-dom";
import UserServices from "../../../services/user.services";
import NotificationHelper from "../../../helpers/notification.helper";

class UserProfile extends Component {

    constructor(props) {
        super(props);

        this.state = {
            user: undefined
        }
    }

    async componentDidMount() {
        let {username} = this.props.match.params;
        let user = await UserServices.findByUsername(username);

        if (!user) {
            NotificationHelper.showErrorNotification(`User wasn't found`)
        } else {
            this.setState({user: user})
        }
    }

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

export default withRouter(UserProfile);