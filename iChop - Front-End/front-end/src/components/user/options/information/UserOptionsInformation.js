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
            statusMessage: 'Hi!',
            birthday: Date.now(),
            aboutYou: 'Just me :)',

            username: '',
            userAvatarUrl: '',

            isFormValid: true,
            maxStatusCharacters: 16,
            leftStatusCharacters: 16
        };

        this.onSaveChanges = this.onSaveChanges.bind(this);
        this.onDateChange = this.onDateChange.bind(this);
        this.onBase64Upload = this.onBase64Upload.bind(this);

        this.userAvatarRef = React.createRef();
        this.aboutYouRef = React.createRef();
        this.onStatusCountedCharacters = this.onStatusCountedCharacters.bind(this);
    }

    onBase64Upload(data) {
        this.userAvatarRef.current.customImageRef.current.src = 'data:image/png;base64,' + data;
    }

    onDateChange(date) {
        this.setState({
            birthday: date
        });
    }

    onStatusCountedCharacters(leftChars) {
        this.setState({leftStatusCharacters: leftChars})
    }

    onSaveChanges() {
        let {statusMessage, birthday, aboutYou, isFormValid} = this.state;

        console.log(statusMessage);
        console.log(dateFormat(birthday, 'dd/mm/yyyy'));
        console.log(aboutYou);

        console.log(isFormValid);
    }

    componentDidMount() {
        this.setState({username: 'ItsGosho'});
        this.setState({userAvatarUrl: ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', this.state.username)});
        this.setState((prev) => {
            return {leftStatusCharacters: prev.leftStatusCharacters - 'Hi!'.length}
        });
    }

    render() {
        let onChange = (event) => (this.setState({[event.target.name]: event.target.value}));

        return (
            <form>

                <div className="row col-md-auto status">
                    <span>Status Message:</span>
                </div>

                <div className="row">
                    <div className="col-lg">
                        {/*<textarea name="statusMessage"
                                  className="textarea-status"
                                  ref={this.statusMessageRef}
                                  value={this.state.statusMessage}
                                  onChange={async (event) => {
                                      await onChange(event);
                                      this.onStatusChange(event);
                                  }}/>*/}

                        <TextAreaWithCounter name={'statusMessage'}
                                             className={'textarea-status'}
                                             maxCharacters={16}
                                             value={this.state.statusMessage}
                                             onCounted={this.onStatusCountedCharacters}/>
                    </div>
                </div>

                <div className="row status-left-chars-row">
                    <div className="col-lg">
                        <small className="status-left-chars">{this.state.leftStatusCharacters}</small>
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

                        <textarea name="aboutYou"
                                  className="textarea-about-you"
                                  value={this.state.aboutYou}
                                  ref={this.aboutYouRef}
                                  onChange={onChange}/>

                    </div>
                </div>

                <div className="row about-you-title">
                    <div className="col-lg">
                        <small className="about-you-left-chars">250</small>
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