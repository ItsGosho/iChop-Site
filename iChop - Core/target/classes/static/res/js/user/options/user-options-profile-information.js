function runProceedChoosingAvatar() {
    $('#button-chooseAvatar-userOptionsProfile').on('click', function() {
        $('#input-chooseAvatar-userOptionsProfile').trigger('click');
    });


    let extensionError = $(`#small-errorFileExtensionAvatar-userOptionsProfile`);
    let buttonSaveChanges = $(`#button-saveChanges-userOptionsProfile`);
    let inputChooseAvatar = $(`#input-chooseAvatar-userOptionsProfile`);
    let inputAvatarData = $(`#input-avatarData-userOptionsProfile`);

    inputChooseAvatar.on(`change`,function (event) {
        let file = event.target.files[0];
        let type = file.type; /*image/png*/
        let size = file.size;
        let reader = new FileReader();

        if(type === 'image/png'){

            if(size > 1048576){
                extensionError.text('Image must be less than 1MB');
                extensionError.show();
                buttonSaveChanges.prop(`type`,`button`);
                return;
            }

            getBase64(file).then(data => {
                inputAvatarData.val(data);
            });

            reader.readAsDataURL(file);

            reader.onload = function(e){
                let content = e.target.result;
                $(`#img-avatar-userOptionsProfile`).attr('src',content);

            };

            buttonSaveChanges.prop(`type`,`submit`);

            extensionError.hide();
        }else{
            extensionError.text('Image must be in format [PNG]');
            extensionError.show();
            buttonSaveChanges.prop(`type`,`button`);
        }

    });

    function getBase64(file) {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => {
                let encoded = reader.result.replace(/^data:(.*;base64,)?/, '');
                if ((encoded.length % 4) > 0) {
                    encoded += '='.repeat(4 - (encoded.length % 4));
                }
                resolve(encoded);
            };
            reader.onerror = error => reject(error);
        });
    }
}

function runValidatorsOfFields() {
    let statusMessageCharactersMAX = 16;
    let aboutYouCharactersMAX = 250;
    let statusMessageField = $(`#textarea-statusMessage-userOptions`);
    let statusMessageCharactersLeft = $(`#small-statusMessageCharactersLeft-userOptions`);
    let aboutYouField = $(`#textarea-aboutYou-userOptions`);
    let aboutYouCharactersLeft = $(`#small-aboutYouCharactersLeft-userOptions`);
    let buttonSaveChanges = $(`#button-saveChanges-userOptionsProfile`);

    magic(statusMessageCharactersMAX, statusMessageField, statusMessageCharactersLeft);
    magic(aboutYouCharactersMAX, aboutYouField, aboutYouCharactersLeft);

    function magic(maxCharacters, field, leftCharactersField) {
        work();
        field.on(`keypress keydown keyup`, function () {
            work();
        });

        function work() {
            let text = field.val();
            let charLeft = maxCharacters - text.length;

            leftCharactersField.text(charLeft);

            if (charLeft < 0) {
                field.css(`border-color`, `red`);
                leftCharactersField.css(`color`, `red`);
                buttonSaveChanges.prop(`type`,`button`);
            } else {
                leftCharactersField.css(`color`, `darkgreen`);
                field.css(`border-color`, `#ccc`);
                buttonSaveChanges.prop(`type`,`submit`);
            }
        }

    }
}