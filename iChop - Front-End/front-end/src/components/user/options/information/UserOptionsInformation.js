import React, {Component} from 'react';
import ServerRoutingURLs from "../../../../constants/server.routing.urls";
import FrontEndResourcesRoutingURLs from "../../../../constants/front-end.resources.routings";
import dateFormat from 'dateformat';
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";

class UserOptionsInformation extends Component {

    constructor(props) {
        super(props);

        this.state = {
            statusMessage: 'Hi!',
            birthday: Date.now(),
            aboutYou: 'Just me :)'
        };

        this.onUserAvatarError = this.onUserAvatarError.bind(this);
        this.onSaveChanges = this.onSaveChanges.bind(this);
    }

    onUserAvatarError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

    onSaveChanges() {
        let {statusMessage,birthday,aboutYou} = this.state;

        console.log(statusMessage);
        console.log(dateFormat(birthday,'dd/mm/yyyy'));
        console.log(aboutYou);
    }

    render() {
        let onChange = (event) => (this.setState({[event.target.name]: event.target.value}));

        let username = 'ItsGosho';
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);

        return (
            <form>

                <div className="row">
                    <div className="col-md-auto" style={{'fontFamily': 'Consolas'}}>
                        <span>Status Message:</span>
                    </div>
                </div>

                <div className="row">
                    <div className="col-lg">
                        <textarea name="statusMessage" id="textarea-statusMessage-userOptions"
                                  style={{
                                      'border': '1px solid #ccc',
                                      'borderRadius': '3px',
                                      'height': '60px',
                                      'overflow': 'auto',
                                      'width': '100%',
                                      'resize': 'none'
                                  }} value={this.state.statusMessage} onChange={onChange}/>
                    </div>
                </div>
                <div className="row" style={{'marginTop': '3px'}}>
                    <div className="col-lg">
                        <small id="small-statusMessageCharactersLeft-userOptions"
                               style={{
                                   'float': 'right',
                                   'display': 'inline-block',
                                   'borderColor': '#ccc',
                                   'marginRight': '3px',
                                   'color': 'darkgreen',
                                   'fontSize': '13px'
                               }}>
                            16
                        </small>
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
                        <img id="img-avatar-userOptionsProfile" style={{'width': '50px', 'height': '50px'}}
                             src={userAvatarUrl} onError={this.onUserAvatarError}/>
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
                                this.setState({
                                    birthday: date
                                });
                            }}
                        />
                    </div>
                </div>

                <div className="dropdown-divider"/>

                <div className="row">
                    <div className="col-md-auto" style={{'fontFamily': 'Consolas'}}>
                        <span>About you:</span>
                    </div>
                </div>

                <div className="row">
                    <div className="col-lg">
                        <textarea id="textarea-aboutYou-userOptions" name="aboutYou"
                                  style={{
                                      'border': '1px solid',
                                      'borderColor': '#ccc',
                                      'borderRadius': '3px',
                                      'height': '60px',
                                      'overflow': 'auto',
                                      'width': '100%',
                                      'resize': 'none'
                                  }} value={this.state.aboutYou} onChange={onChange}/>
                    </div>
                </div>
                <div className="row" style={{'marginTop': '3px'}}>
                    <div className="col-lg">
                        <small id="small-aboutYouCharactersLeft-userOptions"
                               style={{
                                   'float': 'right',
                                   'display': 'inline-block',
                                   'marginRight': '3px',
                                   'color': 'darkgreen',
                                   'fontSize': '13px'
                               }}>
                            250
                        </small>
                    </div>
                </div>

                <div className="dropdown-divider"/>

                <div className="row" align="center">
                    <div className="col-lg">
                        <button id="button-saveChanges-userOptionsProfile" type="button" onClick={this.onSaveChanges}
                                className="btn btn-warning btn-sm">Save Changes
                        </button>
                    </div>
                </div>
            </form>
        );
    }

}

export default UserOptionsInformation;