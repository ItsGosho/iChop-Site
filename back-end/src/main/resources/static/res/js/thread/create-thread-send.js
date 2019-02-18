let buttonCreateThread = $(`#button-proceedCreateThread-createThread`);
let contentDiv= $(`#textarea-content-createThread`);
let titleField = $(`#input-title-threadCreate`);

buttonCreateThread.on(`click`,function () {
    $.ajax({
        method: "POST",
        url: URL_API_CREATE_THREAD,
        data: {title:titleField.val(),content:contentDiv.html()},
        success: function (isSuccessful) {
            if(isSuccessful){
                window.location.href="/";
            }
        }
    });
});