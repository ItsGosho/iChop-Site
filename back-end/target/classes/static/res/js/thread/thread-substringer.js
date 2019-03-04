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