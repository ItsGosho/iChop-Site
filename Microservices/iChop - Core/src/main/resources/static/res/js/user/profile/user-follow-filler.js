function runUserFollowAllFiller(username,elementToAppend,apiURL) {
    let template = " <div class=\"row\">\n" +
        "                    <div class=\"w-100\">\n" +
        "                        <object data=\"{avatarURL}\"\n" +
        "                                                type=\"image/png\"\n" +
        "                                                style=\"width: 50px;height: 50px\">\n" +
        "                                            <img src=\"/res/img/avatar-user.png\" alt=\"example\" class=\"img-user-avatar\" style=\"width: 50px;height: 50px\">\n" +
        "                                        </object>\n" +
        "                        <span><b><a href=\"{profileURL}\">{username}</a></b></span>\n" +
        "                        <span class=\"float-right\" style=\"font-family: Consolas\">{role}</span>\n" +
        "                    </div>\n" +
        "                </div>";


    let url = apiURL.replace('{username}',username);
    let resultToAppend = [];
    $.ajax({
        url: url,
        success: function (result) {
            result.forEach(x=>{
                let resultTemplate = template.replace('{avatarURL}','http://localhost:8001/data/user/'+x.username+'/avatar');
                resultTemplate = resultTemplate.replace('{profileURL}','/user/'+x.username+'/profile');
                resultTemplate = resultTemplate.replace('{username}',x.username);
                resultTemplate = resultTemplate.replace('{role}',x.role);
                resultToAppend.push(resultTemplate);
            });

            resultToAppend.forEach(x=>{
                elementToAppend.append(x);
            })

        }
    })
}