import React from 'react';
import FooterAuthenticatedLinks from "../components/FooterAuthenticatedLinks";
import FooterLinksWrapper from "../components/FooterLinksWrapper";

const UserFooter = () => (
    <FooterLinksWrapper text={'VISIT'}>
        <FooterAuthenticatedLinks/>
    </FooterLinksWrapper>
);

export default UserFooter;