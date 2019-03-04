let threadId = /*[[*{id}]]*/ null;
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