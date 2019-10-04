import React,{Component} from 'react';

class SideInformationMinecraftAccount extends Component {

    render() {

        return (
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
        );
    }
}

export default SideInformationMinecraftAccount;