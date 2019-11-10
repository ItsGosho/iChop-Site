import React, {Component, Fragment} from 'react';
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
            user: undefined,
            isLoading: true
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

        this.setState({isLoading: false})
    }

    render() {
        let {user, isLoading} = this.state;
        console.log(user);

        return (
            <Fragment>
                {user !== undefined && !isLoading ? (
                    <div className="container container-user-profile">
                        <div className="row">
                            <UserProfileLeftSideInformation user={user}/>
                            <UserProfileCentralContent user={user}/>
                        </div>
                    </div>
                ) : null}
            </Fragment>
        );
    }

}

export default withRouter(UserProfile);