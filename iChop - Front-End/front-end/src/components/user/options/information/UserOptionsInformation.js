import React, {Component} from 'react';

class UserOptionsInformation extends Component {


    render() {

        return (
            <form>

                <div className="row">
                    <div className="col-md-auto" style="font-family: Consolas">
                        <span>Status Message:</span>
                    </div>
                </div>

                <div className="row">
                    <div className="col-lg">
                        <textarea name="statusMessage" id="textarea-statusMessage-userOptions"
                                  style="border:1px solid #ccc;border-radius: 3px;height: 60px;overflow: auto;width: 100%;resize: none"
                                  th:text="*{statusMessage}"/>
                    </div>
                </div>
                <div className="row" style="margin-top: 3px">
                    <div className="col-lg">
                        <small id="small-statusMessageCharactersLeft-userOptions"
                               style="float: right;display: inline-block;border-color: #ccc;margin-right: 3px;color: darkgreen;font-size: 13px">
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
                        <img id="img-avatar-userOptionsProfile" style="width: 50px;height: 50px"
                             th:src="@{http://localhost:8001/data/user/{username}/avatar(username=${#authentication.getName()})}"/>
                    </div>
                </div>

                <div className="row" align="center" style="margin-top: 5px">
                    <div className="col-lg">
                        <button type="button" className="btn btn-warning btn-sm"
                                id="button-chooseAvatar-userOptionsProfile">Choose
                        </button>
                        <input id="input-chooseAvatar-userOptionsProfile" type="file" style="display: none"/>
                        <br/>
                        <input id="input-avatarData-userOptionsProfile" type="text" name="avatarBinary"
                               style="display: none"/>
                        <br/>
                        <small id="small-errorFileExtensionAvatar-userOptionsProfile"
                               style="display: none">msg
                        </small>


                        {/*  <script>
                                            runProceedChoosingAvatar();
                                        </script>*/}
                    </div>
                </div>

                <div className="dropdown-divider"/>

                <div className="row" align="center" style="margin-top: 5px">
                    <div className="col-lg">
                        <span>Birthday:</span>
                    </div>
                </div>

                <div className="row" align="center" style="margin-top: 5px">
                    <div className="col-lg">
                        <input th:value="*{birthDate}" name="birthDate" data-provide="datepicker"
                               className="form-control" style="width: 250px" data-date-format="yyyy-mm-dd"/>
                    </div>
                </div>

                <div className="dropdown-divider"/>

                <div className="row">
                    <div className="col-md-auto" style="font-family: Consolas">
                        <span>About you:</span>
                    </div>
                </div>

                <div className="row">
                    <div className="col-lg">
                        <textarea th:text="*{aboutYou}" id="textarea-aboutYou-userOptions" name="aboutYou"
                                  style="border:1px solid;border-color: #ccc;border-radius: 3px;height: 60px;overflow: auto;width: 100%;resize: none"/>
                    </div>
                </div>
                <div className="row" style="margin-top: 3px">
                    <div className="col-lg">
                        <small id="small-aboutYouCharactersLeft-userOptions"
                               style="float: right;display: inline-block;margin-right: 3px;color: darkgreen;font-size: 13px">
                            250
                        </small>
                    </div>
                </div>

                <div className="dropdown-divider"/>

                <div className="row" align="center">
                    <div className="col-lg">
                        <button id="button-saveChanges-userOptionsProfile" type="submit"
                                className="btn btn-warning btn-sm">Save Changes
                        </button>
                    </div>
                </div>
            </form>
    );
    }

    }

    export default UserOptionsInformation;