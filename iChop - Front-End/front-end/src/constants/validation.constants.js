const ValidationConstants = {
    USER: {
        MAX_STATUS_MESSAGE_CHARACTERS: 16,
        MAX_ABOUT_YOU_CHARACTERS: 250,
    },

    POST: {
        MAX_CHARACTERS: 150,
    }
};

const UserValidationConstants = ValidationConstants.USER;
const PostValidationConstants = ValidationConstants.POST;

export {
    UserValidationConstants,
    PostValidationConstants
};