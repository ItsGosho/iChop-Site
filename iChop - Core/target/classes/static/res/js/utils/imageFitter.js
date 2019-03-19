<!--This code gets all of the images and fix their size so they can fit where they belong-->

$(`.content`).each(function () {

    let c = this;

    $(c).find(`img`).toArray().forEach(x=>{
        let obj = $(x);

        obj.css(`height`, `100%`);
        obj.css(`width`, `100%`);
        obj.css(`object-fit`, `contain`);
    });
});