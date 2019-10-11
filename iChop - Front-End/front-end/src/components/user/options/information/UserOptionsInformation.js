import React, {Component} from 'react';
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import dateFormat from 'dateformat';
import DatePicker from "react-datepicker";
import './UserOptionsInformation.css'
import "react-datepicker/dist/react-datepicker.css";
import Image from "../../../other/Image";

class UserOptionsInformation extends Component {

    constructor(props) {
        super(props);

        this.state = {
            statusMessage: 'Hi!',
            birthday: Date.now(),
            aboutYou: 'Just me :)'
        };

        this.onSaveChanges = this.onSaveChanges.bind(this);
        this.onDateChange = this.onDateChange.bind(this);
    }

    onDateChange(date) {
        this.setState({
            birthday: date
        });
    }

    onSaveChanges() {
        let {statusMessage, birthday, aboutYou} = this.state;

        console.log(statusMessage);
        console.log(dateFormat(birthday, 'dd/mm/yyyy'));
        console.log(aboutYou);
    }

    render() {
        let onChange = (event) => (this.setState({[event.target.name]: event.target.value}));

        let username = 'ItsGosho';
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);

        return (
            <form>

                <div className="row">
                    <div className="col-md-auto status">
                        <span>Status Message:</span>
                    </div>
                </div>

                <div className="row">
                    <div className="col-lg">
                        <textarea name="statusMessage"
                                  className="textarea-status"
                                  value={this.state.statusMessage} onChange={onChange}/>
                    </div>
                </div>
                <div className="row status-left-chars-row">
                    <div className="col-lg">
                        <small className="status-left-chars">16</small>
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

                        <Image url={userAvatarUrl}
                               defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                               className={'user-img'}/>

                    </div>
                </div>

                <div className="row" align="center" style={{'marginTop': '5px'}}>
                    <div className="col-lg">
                        <button type="button" className="btn btn-warning btn-sm"
                                id="button-chooseAvatar-userOptionsProfile">Choose
                        </button>
                        <input id="input-chooseAvatar-userOptionsProfile" type="file" style={{'display': 'none'}}/>
                        <br/>
                        <input id="input-avatarData-userOptionsProfile" type="text" name="avatarBinary"
                               style={{'display': 'none'}}/>
                        <br/>
                        <small id="small-errorFileExtensionAvatar-userOptionsProfile"
                               style={{'display': 'none'}}>msg
                        </small>
                    </div>
                </div>

                <div className="dropdown-divider"/>

                <div className="row" align="center" style={{'marginTop': '5px'}}>
                    <div className="col-lg">
                        <span>Birthday:</span>
                    </div>
                </div>

                <div className="row" align="center" style={{'marginTop': '5px'}}>
                    <div className="col-lg">
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
                </div>

                <div className="dropdown-divider"/>

                <div className="row">
                    <div className="col-md-auto about-you-title">
                        <span>About you:</span>
                    </div>
                </div>

                <div className="row">
                    <div className="col-lg">
                        <textarea name="aboutYou"
                                  className="textarea-about-you"
                                  value={this.state.aboutYou} onChange={onChange}/>
                    </div>
                </div>
                <div className="row about-you-title">
                    <div className="col-lg">
                        <small className="about-you-left-chars">250</small>
                    </div>
                </div>

                <div className="dropdown-divider"/>

                <div className="row" align="center">
                    <div className="col-lg">

                        <button type="button"
                                onClick={this.onSaveChanges}
                                className="btn btn-warning btn-sm">
                            Save Changes
                        </button>

                    </div>
                </div>
            </form>
        );
    }

}

export default UserOptionsInformation;