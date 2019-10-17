function runMenuSelectColorizer() {
    $(`.control-option`).toArray().forEach(x => {
        let element = $(x);

        element.on('mouseover', function () {
            element.css(`background-color`, `gray`)
        });

        element.on('mouseout', function () {
            element.css(`background-color`, ``)
        })
    })
}