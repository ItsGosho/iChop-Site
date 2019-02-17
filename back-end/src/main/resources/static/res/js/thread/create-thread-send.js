let buttonCreateThread = $(`#button-proceedCreateThread-createThread`);
let contentDiv= $(`#textarea-content-createThread`);
let titleField = $(`#input-title-threadCreate`);
let rows = $(`#input-rowsForNewsPage-threadCreate`);

buttonCreateThread.on(`click`,function () {
    $.ajax({
        method: "POST",
        url: URL_API_CREATE_THREAD,
        data: {title:titleField.val(),content:contentDiv.html(),rowsForNewsPage:rows.val()},
        success: function (isSuccessful) {
            if(isSuccessful){
                window.location.href="/";
            }
        }
    });
});