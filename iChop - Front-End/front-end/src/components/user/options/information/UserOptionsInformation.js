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
            aboutYou: 'Just me :)',

            username: '',
            userAvatarUrl: ''
        };

        this.onSaveChanges = this.onSaveChanges.bind(this);
        this.onDateChange = this.onDateChange.bind(this);
        this.onAvatarFileChange = this.onAvatarFileChange.bind(this);
        this.getBase64 = this.getBase64.bind(this);

        this.inputFileRef = React.createRef();
    }

    onDateChange(date) {
        this.setState({
            birthday: date
        });
    }

    onAvatarFileChange(event) {

        let file = event.target.files[0];

        let type = file.type; /*image/png*/
        let size = file.size;
        let reader = new FileReader();

        if (type === 'image/png') {

            if (size > 1048576) {
                /*TODO: show error*/
                return;
            }

            this.getBase64(file).then(data => {
                /*TODO: Without base64*/
                console.log(':');
                console.log(data);
            });

            reader.readAsDataURL(file);


            reader.onload = function (e) {
                /*TODO: With base 64*/
                let content = e.target.result;

                console.log(content);
            };

        } else {
            /*TODO: show error*/
        }
    }

    getBase64(file) {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => {
                let encoded = reader.result.replace(/^data:(.*;base64,)?/, '');
                if ((encoded.length % 4) > 0) {
                    encoded += '='.repeat(4 - (encoded.length % 4));
                }
                resolve(encoded);
            };
            reader.onerror = error => reject(error);
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

                        <Image url={this.state.userAvatarUrl}
                               defaultUrl={FrontEndResourcesRoutingURLs.USER.AVATAR}
                               className={'user-img'}/>

                    </div>
                </div>

                <div className="row row-choose-avatar" align="center">
                    <div className="col-lg">

                        <button type="button"
                                className="btn btn-warning btn-sm"
                                onClick={() => {
                                    this.inputFileRef.current.click()
                                }}>
                            Choose
                        </button>

                        <input type="file"
                               className="dont-display"
                               ref={this.inputFileRef}
                               onChange={this.onAvatarFileChange}/>

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