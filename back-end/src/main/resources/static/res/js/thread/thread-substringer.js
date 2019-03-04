function substringHtmlClassContentToKeyWord(clazz,keyword) {
    $(clazz).each(function () {
        let c = this;

        let content = this.innerHTML;
        let substringedContent = content.substring(0,content.indexOf(keyword));
        let isFound = substringedContent !== '';
        if(isFound){
            $(this).empty();
            $(this).append(substringedContent);
        }

    });
}

function removeHtmlClassContentKyWord(clazz,keyword) {

    $(clazz).each(function () {
        let c = this;

        let content = this.innerHTML;
        let substringedContent = content.substring(0,content.indexOf(keyword));
        let isFound = substringedContent !== '';
        if(isFound){
            $(this).empty();

            let result = content.replace(keyword,'');

            $(this).append(result);
        }

    });
}