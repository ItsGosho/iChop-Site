import React, {Component} from 'react';
import ServerRoutingURLs from "../../../../constants/routing/server.routing.urls";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import dateFormat from 'dateformat';
import DatePicker from "react-datepicker";
import './UserOptionsInformation.css'
import "react-datepicker/dist/react-datepicker.css";
import Image from "../../../other/Image";
import UploadBase64Image from "../../../other/UploadBase64Image";
import TextAreaWithCounter from "../../../other/TextAreaWithCounter";
import {UserValidationConstants} from "../../../../constants/other/validation.constants";
import withState from "../../../../hocs/with.state";
import authenticatedUserInfoReducer from "../../../../redux/reducers/authenticated.user.info.reducer";
import UserServices from "../../../../services/user.services";
import {connect} from "react-redux";
import {compose} from "redux";
import authenticatedUserInfoDispatchers from "../../../../redux/dispatchers/authenticated.user.info.dispatchers";

class UserOptionsInformation extends Component {

    constructor(props) {
        super(props);

        this.state = {
            username: '',

            statusMessage: '',
            userAvatarUrl: '',
            birthDate: 0,
            aboutYou: '',

            uploadedUserAvatar: '',
        };

        this.onSaveChanges = this.onSaveChanges.bind(this);
        this.onDateChange = this.onDateChange.bind(this);
        this.onBase64Upload = this.onBase64Upload.bind(this);

        this.onStatusMessageChange = this.onStatusMessageChange.bind(this);

        this.onAboutYouChange = this.onAboutYouChange.bind(this);
        this.proceedStateUpdate = this.proceedStateUpdate.bind(this);

        this.userAvatarRef = React.createRef();
    }

    onBase64Upload(data) {
        let avatarAsBase64 = 'data:image/png;base64,' + data;

        this.userAvatarRef.current.customImageRef.current.src = avatarAsBase64;
        this.setState({uploadedUserAvatar: avatarAsBase64})
    }

    onDateChange(date) {
        this.setState({
            birthDate: date
        });
    }

    onStatusMessageChange(status) {
        this.setState({statusMessage: status})
    }

    onAboutYouChange(status) {
        this.setState({aboutYou: status})
    }

    async onSaveChanges() {
        let {username} = this.props.authenticatedUserInfo;
        let {statusMessage, birthDate, aboutYou, uploadedUserAvatar} = this.state;

        let response = await UserServices.updateInformation(username,
            statusMessage,
            dateFormat(birthDate, 'dd/mm/yyyy'),
            aboutYou,
            uploadedUserAvatar);

        if (response.successful) {
            this.props.fetchAuthenticatedUserInfo();
        }
    }

    componentWillReceiveProps(nextProps, nextContext) {
        this.proceedStateUpdate(nextProps.authenticatedUserInfo);
    }

    componentWillMount() {
        this.proceedStateUpdate(this.props.authenticatedUserInfo);
    }

    proceedStateUpdate(source) {
        let {username, statusMessage, birthDate, aboutYou} = source;
        let avatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);

        statusMessage = statusMessage !== undefined ? statusMessage : '';
        aboutYou = aboutYou !== undefined ? aboutYou : '';

        this.setState({username: username});
        this.setState({statusMessage: statusMessage});
        this.setState({userAvatarUrl: avatarUrl});
        this.setState({birthDate: Date.parse(birthDate)});
        this.setState({aboutYou: aboutYou});
    }

    render() {
        let leftStatusMessageCharacters = UserValidationConstants.MAX_STATUS_MESSAGE_CHARACTERS - this.state.statusMessage.length;
        let leftAboutYouCharacters = UserValidationConstants.MAX_ABOUT_YOU_CHARACTERS - this.state.aboutYou.length;

        return (
            <form>

                <div className="row col-md-auto status">
                    <span>Status Message:</span>
                </div>

                <div className="row">
                    <div className="col-lg">

                        <TextAreaWithCounter name={'statusMessage'}
                                             className={'textarea-status'}
                                             maxCharacters={UserValidationConstants.MAX_STATUS_MESSAGE_CHARACTERS}
                                             value={this.state.statusMessage}
                                             onValueChange={this.onStatusMessageChange}/>
                    </div>
                </div>

                <div className="row status-left-chars-row">
                    <div className="col-lg">
                        <small className="status-left-chars">{leftStatusMessageCharacters}</small>
                    </div>
                </div>

                <div className="dropdown-divider"/>

                <div className="row" align="center">
                    <div className="col-lg">
                        <span>Avatar:</span>
                    </div>
                </div>

                <div className="row" align="center">
                    <div className="col-lg">

                        <Image url={this.state.userAvatarUrl}
                               defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                               ref={this.userAvatarRef}
                               className={'user-img'}/>
                    </div>
                </div>

                <div className="row row-choose-avatar" align="center">
                    <div className="col-lg">

                        <UploadBase64Image onUpload={this.onBase64Upload}/>

                    </div>
                </div>

                <div className="dropdown-divider"/>

                <div className="row row-birthdate-title" align="center">
                    <div className="col-lg">
                        <span>Birthdate:</span>
                    </div>
                </div>

                <div className="row row-birthdate-pick" align="center">
                    <div className="col-lg">

                        <DatePicker
                            className="form-control"
                            format='Pp'
                            dateFormat='dd/MM/yyyy'
                            value={this.state.birthDate}
                            selected={this.state.birthDate}
                            onChange={date => {
                                this.onDateChange(date);
                            }}/>

                    </div>
                </div>

                <div className="dropdown-divider"/>

                <div className="row about-you-title">
                    <div className="col-md-auto">
                        <span>About you:</span>
                    </div>
                </div>

                <div className="row">
                    <div className="col-lg">

                        <TextAreaWithCounter name={'aboutYou'}
                                             className={'textarea-about-you'}
                                             maxCharacters={UserValidationConstants.MAX_ABOUT_YOU_CHARACTERS}
                                             value={this.state.aboutYou}
                                             onValueChange={this.onAboutYouChange}/>

                    </div>
                </div>

                <div className="row about-you-title">
                    <div className="col-lg">
                        <small className="about-you-left-chars">{leftAboutYouCharacters}</small>
                    </div>
                </div>

                <div className="dropdown-divider"/>

                <div className="row col-lg">

                    <button type="button"
                            onClick={this.onSaveChanges}
                            className="btn btn-warning btn-sm">
                        Save Changes
                    </button>
                </div>

            </form>
        );
    }

}

let mapState = (state) => {
    return {...state};
};

export default connect(mapState, authenticatedUserInfoDispatchers)(UserOptionsInformation);