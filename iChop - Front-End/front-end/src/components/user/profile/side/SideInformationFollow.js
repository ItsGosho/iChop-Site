import React, {Component, Fragment} from 'react';
import SideInformationFollowings from "./SideInformationFollowings";
import ModalOpen from "../../../modal/ModalOpen";
import UserFollowingsModal from "../modals/UserFollowingsModal";
import SideInformationFollowers from "./SideInformationFollowers";
import UserFollowersModal from "../modals/UserFollowersModal";

class SideInformationFollow extends Component {


    render() {
        let totalFollowings = 7;
        let totalFollowers = 5;

        return (
            <Fragment>
                <div className="card follow-card">

                    <div align="center">
                        <span>Following: {totalFollowings}</span>
                    </div>

                    <div>
                        <SideInformationFollowings/>
                    </div>


                    {totalFollowings > 4 ? (
                        <ModalOpen relationTo="all-followings">
                            <a href='#'>See all</a>
                        </ModalOpen>
                    ) : null}

                    <UserFollowingsModal/>

                </div>

                <div className="card follow-card">

                    <div align="center">
                        <span>Followers: {totalFollowers}</span>
                    </div>

                    <div>
                        <SideInformationFollowers/>
                    </div>

                    {totalFollowers > 4 ? (
                        <ModalOpen relationTo="all-followers">
                            <a href='#'>See all</a>
                        </ModalOpen>
                    ) : null}

                    <UserFollowersModal/>

                </div>
            </Fragment>
        );
    }

}

export default SideInformationFollow;