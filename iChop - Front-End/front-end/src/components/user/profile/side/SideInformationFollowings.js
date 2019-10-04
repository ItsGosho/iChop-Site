import React,{Component} from 'react';

class SideInformationFollowings extends Component {

    render() {

        return (
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
        );
    }
}

export default SideInformationFollowings;