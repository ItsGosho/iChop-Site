
const PARAMETER_LOGIN_REQUIRE = "?login=require";
const PARAMETER_LOGIN_ERROR = "?login=error";
const PARAMETER_REGISTER_REQUIRE = "?register=require";

const URL_API_USERNAME_AVAILABILITY = "/api/user/exists?username=";
const URL_API_EMAIL_AVAILABILITY = "/api/user/exists?email=";

const URL_API_USER_SEND_EMAIL_RESET_PASSWORD = "user/send-email/reset/password";
const URL_API_IS_USER_FOLLOWING_USER = "/api/user/is-following?user1Username=${user1Username}&user2Username=${user2Username}";

const URL_API_CREATE_THREAD = "/thread/create";
const URL_API_COMMENT_THREAD = "/thread/{id}/comment/create";
const URL_READ_THREAD = "/thread/{id}/read";