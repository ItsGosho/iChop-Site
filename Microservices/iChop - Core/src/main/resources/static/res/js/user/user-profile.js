function runFollowingScript(currentLoggedInUserUsername,lookingUserUsername) {
    if(currentLoggedInUserUsername.toLowerCase() !== lookingUserUsername.toLowerCase()){
        let url = URL_API_IS_USER_FOLLOWING_USER.replace("${user1Username}",currentLoggedInUserUsername);
        url = url.replace("${user2Username}",lookingUserUsername);
        $.ajax({
            method: "GET",
            url: url,
            success: function (isFollowing) {
                if(isFollowing){
                    $(`#a-unfollow_user-userProfile`).css(`display`,`inline-block`);
                }else{
                    $(`#a-follow_user-userProfile`).css(`display`,`inline-block`);
                }
            }
        });

        let urlReverse = URL_API_IS_USER_FOLLOWING_USER.replace("${user1Username}",lookingUserUsername);
        urlReverse = urlReverse.replace("${user2Username}",currentLoggedInUserUsername);

        $.ajax({
            method: "GET",
            url: urlReverse,
            success: function (isFollowing) {
                if(isFollowing){
                    $(`#a-is_he_followed_you-userProfile`).text(lookingUserUsername + " is following you.");
                }else{
                    $(`#a-is_he_followed_you-userProfile`).text(lookingUserUsername + " is not following you.");
                }
            }
        })
    }
}

function runPostCreateScript() {
    let minCharacters = POST_MIN_LENGTH;
    let maxCharacters = POST_MAX_LENGTH;

    let textArea = $(`#textarea-createPost-userProfile`);
    let leftCharactersField = $(`#small-leftPostCreationCharacters-userProfile`);

    textArea.on(`keypress keydown keyup`,function () {
        let content = textArea.val();
        let remainingCharacters = maxCharacters-content.length;
        leftCharactersField.text(remainingCharacters);

        if(content.length >= minCharacters && content.length <= maxCharacters){
            textArea.css('border-color','')
            $('#button-createPost-userProfile').attr('type','submit')

        }else{
            textArea.css('border-color','red')
            $('#button-createPost-userProfile').attr('type','button')
        }

        if(remainingCharacters <= 0){
            leftCharactersField.css('color','red')
        }else{
            leftCharactersField.css('color','darkgreen')
        }

    });
}

function hideReportButtonsOfPostIfReportExists(userUsername, postId, isLoggedUser) {
    if (isLoggedUser) {
        let url = URL_API_IS_POST_REPORTED_BY_USER.replace('{postId}', postId);
        url = url.replace('{userUsername}', userUsername);

        $.ajax({
            url: url,
            success: function (isReported) {
                if (isReported) {
                    $(`#button-report_post-${postId}`).hide();
                }
            }
        });
    }
}