function bindShowCommentBox(button, commentBox) {
    button.on(`click`, function () {
        commentBox.show();
    });
}

function bindHideCommentBox(button, commentBox) {
    button.on(`click`, function () {
        commentBox.hide();
    });
}

function createComment(threadId) {
    let buttonCreateComment = $(`#button-createCommentThread-readThreadPage`);
    let contentDiv = $(`#textarea-content-createComment`);
    let url = URL_API_COMMENT_THREAD.replace('{id}', threadId);
    let successfulRedirectUrl = URL_READ_THREAD.replace('{id}', threadId);


    buttonCreateComment.on(`click`, function () {
        $.ajax({
            method: "POST",
            url: url,
            data: {content: contentDiv.html()},
            success: function (isSuccessful) {
                if (isSuccessful) {
                    window.location.href = successfulRedirectUrl;
                }
            }
        });
    });
}

function hideReactionButtonsIfAlreadyReacted(userUsername, commentId, isLoggedUser) {
    if (isLoggedUser) {
        let url = URL_API_IS_COMMENT_REACTED_BY_USER.replace('{commentId}', commentId);
        url = url.replace('{userUsername}', userUsername);

        $.ajax({
            url: url,
            success: function (isReacted) {
                if (isReacted) {
                    $(`#thread-comment_react_buttons-${commentId}`).hide();
                }
            }
        });
    }
}

function hideReportButtonsOfThreadIfReportExists(userUsername, threadId, isLoggedUser) {
    if (isLoggedUser) {
        let url = URL_API_IS_THREAD_REPORTED_BY_USER.replace('{threadId}', threadId);
        url = url.replace('{userUsername}', userUsername);

        $.ajax({
            url: url,
            success: function (isReported) {
                if (isReported) {
                    $(`#thread-report_button-${threadId}`).hide();
                }
            }
        });
    }
}

function hideReportButtonsOfCommentIfReportExists(userUsername, commentId, isLoggedUser) {
    if (isLoggedUser) {
        let url = URL_API_IS_COMMENT_REPORTED_BY_USER.replace('{commentId}', commentId);
        url = url.replace('{userUsername}', userUsername);

        $.ajax({
            url: url,
            success: function (isReported) {
                if (isReported) {
                    $(`#thread-comment-report_button-${commentId}`).hide();
                }
            }
        });
    }
}