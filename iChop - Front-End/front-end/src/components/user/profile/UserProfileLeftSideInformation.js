import React, {Component} from 'react';
import dateFormat from 'dateformat';
import ServerRoutingURLs from "../../../constants/server.routing.urls";
import SideInformationLastOnline from "./side/SideInformationLastOnline";
import SideInformationJoinedOn from "./side/SideInformationJoinedOn";
import SideInformationMessages from "./side/SideInformationMessages";
import SideInformationTotalLikes from "./side/SideInformationTotalLikes";
import SideInformationTotalDislikes from "./side/SideInformationTotalDislikes";
import SideInformationLocation from "./side/SideInformationLocation";
import FrontEndResourcesRoutingURLs from "../../../constants/front-end.resources.routings";

class UserProfileLeftSideInformation extends Component {

    constructor(props) {
        super(props);

        this.onUserAvatarError = this.onUserAvatarError.bind(this);
    }

    onUserAvatarError(event) {
        event.target.onerror = null;
        event.target.src = FrontEndResourcesRoutingURLs.USER.AVATAR;
    }

    render() {
        let username = 'ItsGosho';
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);
        let totalFollowings = 7;
        let totalFollowers = 5;

        return (
            <div className="col-md-auto">
                <div className="col-md-sm user-info">

                    <div align="center">
                        <img src={userAvatarUrl}
                             onError={this.onUserAvatarError}
                             alt=''
                             className="img-user-avatar"/>
                    </div>


                    <div className="card user-base-info">
                        <div className="card-body user-base-info-body">

                            <SideInformationLastOnline/>
                            <SideInformationJoinedOn/>
                            <SideInformationMessages/>

                        </div>
                    </div>


                    <div className="card reactions-given" style={{'marginTop': '10px'}}>
                        <div className="card-body"
                             style={{'lineHeight': '13px', 'fontFamily': 'Consolas', 'marginLeft': '-20px'}}>

                            <SideInformationTotalLikes/>
                            <SideInformationTotalDislikes/>

                        </div>
                    </div>

                    <div className="card following" style={{'marginTop': '10px'}}>
                        <div align="center">
                            <span>Following: {totalFollowings}</span>
                        </div>
                        <div>

                            <SideInformationFollowings/>

                        </div>

                        {
                            (() => {
                                if (totalFollowings > 4) {
                                    return (
                                        <a href="#" className="d-flex justify-content-center align-items-center"
                                           data-toggle="modal"
                                           data-target=".modal-all-following">See all</a>
                                    );
                                }
                            })()
                        }

                    </div>

                    <div className="card followers" style={{'marginTop': '10px'}}>
                        <div align="center">
                            <span>Followers: {totalFollowers}</span>
                        </div>
                        <div>

                            <SideInformationFollowers/>

                        </div>

                        <th:block th:if="*{totalFollowers > 4}">
                            <a href="#" className="d-flex justify-content-center align-items-center" data-toggle="modal"
                               data-target=".modal-all-followers">See all</a>
                            <th:block th:insert="user/profile/user-followers-all"></th:block>

                            <script th:inline="javascript">
                                let username = /*[[*{username}]]*/ null;
                                $(`*[data-target=".modal-all-followers"]`).click(function () {
                                runUserFollowAllFiller(username, $(`#div-insert_followers-user_followers_all`), URL_API_USER_ALL_FOLLOWERS);
                            });
                            </script>

                        </th:block>
                    </div>

                    <div className="card user-location" style="margin-top: 10px">
                        <div className="card-body"
                             style="line-height: 13px;font-family: Consolas;margin-left: -20px;margin-bottom: -15px;margin-top: -15px">

                            <SideInformationLocation/>

                        </div>

                    </div>
                </div>
                );
                }
                }

                export default UserProfileLeftSideInformation;