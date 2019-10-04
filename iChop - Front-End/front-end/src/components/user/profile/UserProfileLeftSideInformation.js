import React, {Component} from 'react';
import dateFormat from 'dateformat';
import ServerRoutingURLs from "../../../constants/server.routing.urls";
import SideInformationLastOnline from "./side/SideInformationLastOnline";
import SideInformationJoinedOn from "./side/SideInformationJoinedOn";
import SideInformationMessages from "./side/SideInformationMessages";
import SideInformationTotalLikes from "./side/SideInformationTotalLikes";
import SideInformationTotalDislikes from "./side/SideInformationTotalDislikes";

class UserProfileLeftSideInformation extends Component {


    render() {
        let username = 'ItsGosho';
        let userAvatarUrl = ServerRoutingURLs.DATA.USER.AVATAR.GET.replace(':username', username);
        let lastOnline = new Date();

        return (
            <div className="col-md-auto">
                <div className="col-md-sm user-info">

                    <div align="center">
                        <img th:src="@{http://localhost:8001/data/user/{username}/avatar(username=*{username})}"
                             onError="this.onerror = null;this.src = '/res/img/avatar-user.png'"
                             className="img-user-avatar"/>
                    </div>


                    <div className="card user-base-info">
                        <div className="card-body user-base-info-body">

                            <SideInformationLastOnline/>
                            <SideInformationJoinedOn/>
                            <SideInformationMessages/>

                        </div>
                    </div>


                    <div className="card reactions-given" style="margin-top: 10px">
                        <div className="card-body" style="line-height: 13px;font-family: Consolas;margin-left: -20px">

                            <SideInformationTotalLikes/>
                            <SideInformationTotalDislikes/>

                        </div>
                    </div>

                    <div className="card following" style="margin-top: 10px">
                        <div align="center">
                            <span>Following: <span th:text="*{totalFollowing}">000</span></span>
                        </div>
                        <div>

                            <SideInformationFollowings/>

                        </div>
                        <th:block th:if="*{totalFollowing > 4}">
                            <a href="#" className="d-flex justify-content-center align-items-center" data-toggle="modal"
                               data-target=".modal-all-following">See all</a>
                            <th:block th:insert="user/profile/user-following-all"></th:block>

                            <script th:inline="javascript">
                                let username = /*[[*{username}]]*/ null;
                                $(`*[data-target=".modal-all-following"]`).click(function () {
                                runUserFollowAllFiller(username, $(`#div-insert_followings-user_followings_all`), URL_API_USER_ALL_FOLLOWINGS);
                            });
                            </script>

                        </th:block>
                    </div>

                    <div className="card followers" style="margin-top: 10px">
                        <div align="center">
                            Followers: <span th:text="*{totalFollowers}">000</span>
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

                            <div className="col-md-auto">
                                <div className="row" style="font-size: 11px">
                                    <div className="col-md-auto" style="column-width: 75px">
                                        Location:
                                    </div>
                                    <div className="col-md-auto" style="column-width: 100px">
                                    <span style="display: inline-block;float: right;">
                                         <span th:text="*{location}"></span>
                                    </span>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        );
    }
}

export default UserProfileLeftSideInformation;