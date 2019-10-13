import React, {Component} from 'react';
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import dateFormat from 'dateformat';
import DatePicker from "react-datepicker";
import './UserOptionsInformation.css'
import "react-datepicker/dist/react-datepicker.css";
import Image from "../../../other/Image";
import UploadBase64Image from "../../../other/UploadBase64Image";
import TextAreaWithCounter from "../../../other/TextAreaWithCounter";

class UserOptionsInformation extends Component {

    constructor(props) {
        super(props);

        this.state = {
            username: '',

            statusMessage: '',
            userAvatarUrl: '',
            birthday: 0,
            aboutYou: '',

            uploadedUserAvatar: '',

            maxStatusCharacters: 16,
            maxAboutYouCharacters: 250,

            leftStatusMessageCharacters: 16,
            leftAboutYouCharacters: 250
        };

        this.onSaveChanges = this.onSaveChanges.bind(this);
        this.onDateChange = this.onDateChange.bind(this);
        this.onBase64Upload = this.onBase64Upload.bind(this);

        this.onStatusMessageCountedCharacters = this.onStatusMessageCountedCharacters.bind(this);
        this.onStatusMessageChange = this.onStatusMessageChange.bind(this);

        this.onAboutYouCountedCharacters = this.onAboutYouCountedCharacters.bind(this);
        this.onAboutYouChange = this.onAboutYouChange.bind(this);

        this.userAvatarRef = React.createRef();
    }

    onBase64Upload(data) {
        let avatarAsBase64 = 'data:image/png;base64,' + data;

        this.userAvatarRef.current.customImageRef.current.src = avatarAsBase64;
        this.setState({uploadedUserAvatar: avatarAsBase64})
    }

    onDateChange(date) {
        this.setState({
            birthday: date
        });
    }

    onStatusMessageChange(status) {
        this.setState({statusMessage: status})
    }

    onStatusMessageCountedCharacters(leftChars) {
        this.setState({leftStatusMessageCharacters: leftChars});
    }

    onAboutYouChange(status) {
        this.setState({aboutYou: status})
    }

    onAboutYouCountedCharacters(leftChars) {
        this.setState({leftAboutYouCharacters: leftChars})
    }

    onSaveChanges() {
        let {statusMessage, birthday, aboutYou, uploadedUserAvatar} = this.state;

        console.log(statusMessage);
        console.log(dateFormat(birthday, 'dd/mm/yyyy'));
        console.log(aboutYou);
        console.log(uploadedUserAvatar);

    }

    componentWillMount() {
        /*REQUIRED DATA*/
        let username = 'ItsGosho';
        let avatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);
        let statusMessage = 'Hi!';
        let birthday = new Date();
        let aboutYou = 'Just me :)';

        this.setState({username: username});
        this.setState({statusMessage: statusMessage});
        this.setState({userAvatarUrl: avatarUrl});
        this.setState({birthday: birthday});
        this.setState({aboutYou: aboutYou});


        this.setState((prev) => ({leftStatusMessageCharacters: prev.leftStatusMessageCharacters - statusMessage.length}));
        this.setState((prev) => ({leftAboutYouCharacters: prev.leftAboutYouCharacters - aboutYou.length}));
    }

    render() {

        return (
            <form>

                <div className="row col-md-auto status">
                    <span>Status Message:</span>
                </div>

                <div className="row">
                    <div className="col-lg">

                        <TextAreaWithCounter name={'statusMessage'}
                                             className={'textarea-status'}
                                             maxCharacters={this.state.maxStatusCharacters}
                                             value={this.state.statusMessage}
                                             onValueChange={this.onStatusMessageChange}
                                             onCounted={this.onStatusMessageCountedCharacters}/>
                    </div>
                </div>

                <div className="row status-left-chars-row">
                    <div className="col-lg">
                        <small className="status-left-chars">{this.state.leftStatusMessageCharacters}</small>
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
                        <span>Birthday:</span>
                    </div>
                </div>

                <div className="row row-birthdate-pick" align="center">
                    <div className="col-lg">

                        <DatePicker
                            className="form-control"
                            format='Pp'
                            value={this.state.birthday}
                            selected={this.state.birthday}
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
                                             maxCharacters={this.state.maxAboutYouCharacters}
                                             value={this.state.aboutYou}
                                             onValueChange={this.onAboutYouChange}
                                             onCounted={this.onAboutYouCountedCharacters}/>

                    </div>
                </div>

                <div className="row about-you-title">
                    <div className="col-lg">
                        <small className="about-you-left-chars">{this.state.leftAboutYouCharacters}</small>
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

export default UserOptionsInformation;