import React, {Component} from 'react';

class UserProfileLeftSideInformation extends Component {


    render() {

        return (
            <div className="col-md-auto">
                <div className="col-md-sm user-info">

                    <div align="center">
                        <img th:src="@{http://localhost:8001/data/user/{username}/avatar(username=*{username})}"
                             onError="this.onerror = null;this.src = '/res/img/avatar-user.png'"
                             className="img-user-avatar">
                    </div>


                    <div className="card user-base-info">
                        <div className="card-body user-base-info-body">

                            <div className="col-md-auto">
                                <div className="row user-base-info-row">
                                    <div className="col-md-auto user-base-info-last-online-title">
                                        Last online:
                                    </div>
                                    <div className="col-md-auto user-base-info-last-online-content">
                                <span th:text="*{#temporals.format(lastOnline, 'MMM dd,yyyy')}"
                                      th:title="*{#temporals.format(lastOnline, 'HH:mm')}">
                                   Unknown 00,0000
                                </span>
                                    </div>
                                </div>
                            </div>

                            <div className="col-md-auto">
                                <div className="row user-base-info-registration_date-title">
                                    <div className="col-md-auto">
                                        Joined on:
                                    </div>
                                    <div className="col-md-auto" style="column-width: 100px">
                               <span style="display: inline-block;float: right;"
                                     th:text="*{#temporals.format(registrationDate, 'MMM dd,yyyy')}"
                                     th:title="*{#temporals.format(registrationDate, 'HH:mm')}">
                                    Unknown 00,0000
                               </span>
                                    </div>
                                </div>
                            </div>

                            <div className="col-md-auto">
                                <div className="row" style="font-size: 11px">
                                    <div className="col-md-auto" style="column-width: 75px">
                                        Messages:
                                    </div>
                                    <div className="col-md-auto" style="column-width: 100px">
                               <span style="display: inline-block;float: right;" th:text="*{totalMessages}">
                                    00000
                               </span>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>

                    <th:block th:if="*{minecraftAccountName != null}">
                        <div className="card minecraft-user-link" style="margin-top: 10px">
                            <div>
                                <img
                                    th:src="@{https://minotar.net/avatar/{minecraftAccountName}(minecraftAccountName=*{minecraftAccountName})}"
                                    style="width: 30px;height: 30px;margin-left: 5px;margin-top: 2px;margin-bottom: 2px"/>
                                <a th:href="@{/player/{uuid}(uuid=*{minecraftAccountUUID})}"
                                   th:text="*{minecraftAccountName}"></a>
                            </div>
                        </div>
                    </th:block>

                    <div className="card reactions-given" style="margin-top: 10px">
                        <div className="card-body" style="line-height: 13px;font-family: Consolas;margin-left: -20px">

                            <div className="col-md-auto">
                                <div className="row" style="font-size: 15px">
                                    <div className="col-md-auto" style="font-size: 16px;">
                                        👍
                                    </div>
                                    <div className="col-md-auto" style="column-width: 150px">
                               <span style="display: inline-block;float: right;color:darkgreen"
                                     th:text="*{totalDislikes}">
                                    0000
                               </span>
                                    </div>
                                </div>
                                <div className="dropdown-divider" style="width: 100%"></div>
                            </div>

                            <div className="col-md-auto">
                                <div className="row" style="font-size: 15px">
                                    <div className="col-md-auto" style="font-size: 16px">
                                        👎
                                    </div>
                                    <div className="col-md-auto" style="column-width: 150px">
                               <span style="display: inline-block;float: right;color: indianred;"
                                     th:text="*{totalLikes}">
                                    0000
                               </span>
                                    </div>
                                </div>
                                <div className="dropdown-divider" style="width: 100%"></div>
                            </div>

                        </div>
                    </div>

                    <div className="card following" style="margin-top: 10px">
                        <div align="center">
                            <span>Following: <span th:text="*{totalFollowing}">000</span></span>
                        </div>
                        <div>

                            <div className="row d-flex justify-content-center align-items-center"
                                 style="width: 100%;margin-left: 0px">
                                <th:block th:each="following : *{followings}" th:object="${following}">
                                    <th:block th:if="${followingStat.index < 4}">
                                        <a th:href="@{/user/{username}/profile(username=*{username})}">
                                            <img
                                                th:src="@{http://localhost:8001/data/user/{username}/avatar(username=*{username})}"
                                                className="img-user-avatar"
                                                th:title="*{username}"
                                                onError="this.onerror = null;this.src = '/res/img/avatar-user.png'"
                                                style="width: 30px;height: 30px;margin-left: 5px;margin-top: 2px;margin-bottom: 2px">
                                        </a>
                                    </th:block>
                                </th:block>
                            </div>

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

                            <div className="row d-flex justify-content-center align-items-center"
                                 style="width: 100%;margin-left: 0px">
                                <th:block th:each="follower : *{followers}" th:object="${follower}">
                                    <th:block th:if="${followerStat.index < 4}">
                                        <a th:href="@{/user/{username}/profile(username=*{username})}">
                                            <img
                                                th:src="@{http://localhost:8001/data/user/{username}/avatar(username=*{username})}"
                                                className="img-user-avatar"
                                                th:title="*{username}"
                                                onError="this.onerror = null;this.src = '/res/img/avatar-user.png'"
                                                style="width: 30px;height: 30px;margin-left: 5px;margin-top: 2px;margin-bottom: 2px">
                                        </a>
                                    </th:block>
                                </th:block>
                            </div>


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