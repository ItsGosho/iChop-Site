import React, {Component} from 'react';
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import dateFormat from 'dateformat';
import DatePicker from "react-datepicker";
import './UserOptionsInformation.css'
import "react-datepicker/dist/react-datepicker.css";
import Image from "../../../other/Image";
import UploadBase64Image from "../../../other/UploadBase64Image";

class UserOptionsInformation extends Component {

    constructor(props) {
        super(props);

        this.state = {
            statusMessage: 'Hi!',
            birthday: Date.now(),
            aboutYou: 'Just me :)',

            username: '',
            userAvatarUrl: ''
        };

        this.onSaveChanges = this.onSaveChanges.bind(this);
        this.onDateChange = this.onDateChange.bind(this);
        this.onBase64 = this.onBase64.bind(this);

        this.userAvatarRef = React.createRef();
    }

    onBase64(data) {
        let withBase64Prefix = 'data:image/png;base64,' + data;

        this.userAvatarRef.current.customImageRef.current.src = withBase64Prefix;
    }

    onDateChange(date) {
        this.setState({
            birthday: date
        });
    }


    /*TODO:
    *  1.Choose avatar
    *  2.Left characters to work
    *  3.Restructure
    *
    * */

    onSaveChanges() {
        let {statusMessage, birthday, aboutYou} = this.state;

        console.log(statusMessage);
        console.log(dateFormat(birthday, 'dd/mm/yyyy'));
        console.log(aboutYou);
    }

    componentDidMount() {
        this.setState({username: 'ItsGosho'})
        this.setState({userAvatarUrl: ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', this.state.username)})
    }

    render() {
        let onChange = (event) => (this.setState({[event.target.name]: event.target.value}));

        return (
            <form>

                <div className="row col-md-auto status">
                    <span>Status Message:</span>
                </div>

                <div className="row col-lg">
                        <textarea name="statusMessage"
                                  className="textarea-status"
                                  value={this.state.statusMessage} onChange={onChange}/>
                </div>

                <div className="row col-lg status-left-chars-row">
                    <small className="status-left-chars">16</small>
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

                        <UploadBase64Image onUpload={this.onBase64}/>

                    </div>
                </div>

                <div className="dropdown-divider"/>

                <div className="row col-lg row-birthdate-title" align="center">
                    <span>Birthday:</span>
                </div>

                <div className="row col-lg row-birthdate-pick" align="center">
                    {/*<input value={this.state.birthday} name="birthDate" data-provide="datepicker" onChange={onChange}
                               className="form-control" style={{'width': '250px'}} data-date-format="yyyy-mm-dd"/>*/}
                    <DatePicker
                        format='Pp'
                        value={this.state.birthday}
                        selected={this.state.birthday}
                        onChange={date => {
                            this.onDateChange(date);
                        }}
                    />
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