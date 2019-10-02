import React, {Component, Fragment} from 'react';

class UserOptionsMinecraft extends Component {


    render() {

        return (
            <Fragment>
                <th:block th:if="${playerData.playerName == null}">

                    <div className="dropdown-divider"/>

                    <div className="row" align="center">
                        <div className="col-lg">
                            <span>You havent linked account yet!</span>
                        </div>
                    </div>

                    <div className="dropdown-divider"/>

                    <div className="row" align="center">
                        <div className="col-lg">
                                <span>To link your account you must be logged in the site with the profile that you want to link your account
                                ,then in the game type <span><b>/linkaccount</b></span> ,then a <span><b>link</b></span> will be generated ,click
                                    it and a page <span><b>in the site</b></span> will open
                                with confirmation to link your account.Note that if you link your account you cant link another until <span><b>the
                                previous is unlinked</b></span>.</span>
                        </div>
                    </div>

                    <div className="dropdown-divider"/>

                </th:block>

                <th:block th:if="${playerData.playerName != null}" th:object="${playerData}">
                    <div className="row" align="center">
                        <div className="col-lg">
                            <span><b><a th:href="@{/player/{uuid}(uuid=*{playerUUID})}" th:text="*{playerName}">Player Name</a></b></span>
                        </div>
                    </div>

                    <div className="dropdown-divider"/>

                    <div align="center">
                        <img th:src="@{https://crafatar.com/renders/body/{uuid}(uuid=*{playerUUID})}"
                             style="width: 95px;height: 185px"/>
                    </div>

                    <div className="dropdown-divider"/>

                    <div align="center" style="margin-top: 10px">
                        <form style="all:initial" method="post" action="/user/my-profile/options/minecraft/unlink">
                            <button type="submit" className="btn btn-warning">Unlink</button>
                        </form>
                    </div>
                </th:block>
            </Fragment>
        );
    }

}

export default UserOptionsMinecraft;