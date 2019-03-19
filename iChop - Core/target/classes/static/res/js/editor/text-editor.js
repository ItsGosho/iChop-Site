let button_formatBold = $(`#button-formatBold-textEditor`);
let button_formatItalic = $(`#button-formatItalic-textEditor`);
let button_formatUnderline = $(`#button-formatUnderline-textEditor`);

let button_color_white = $(`#button-color_white-textEditor`);
let button_color_black = $(`#button-color_black-textEditor`);
let button_color_gray = $(`#button-color_grey-textEditor`);
let button_color_red = $(`#button-color_red-textEditor`);
let button_color_redDark = $(`#button-color_redDark-textEditor`);
let button_color_redIndian = $(`#button-color_redIndian-textEditor`);
let button_color_green = $(`#button-color_green-textEditor`);
let button_color_greenDark = $(`#button-color_greenDark-textEditor`);
let button_color_greenYellow = $(`#button-color_greenYellow-textEditor`);
let button_color_blue = $(`#button-color_blue-textEditor`);
let button_color_blueDark = $(`#button-color_blueDark-textEditor`);
let button_color_blueDeepSky = $(`#button-color_blueDeepSky-textEditor`);
let button_color_brown = $(`#button-color_brown-textEditor`);
let button_color_brownSaddle = $(`#button-color_brownSaddle-textEditor`);
let button_color_brownSandy = $(`#button-color_brownSandy-textEditor`);
let button_color_pink = $(`#button-color_pink-textEditor`);
let button_color_pinkDeep = $(`#button-color_pinkDeep-textEditor`);
let button_color_pinkHot = $(`#button-color_pinkHot-textEditor`);
let button_color_yellow = $(`#button-color_yellow-textEditor`);
let button_color_purple = $(`#button-color_purple-textEditor`);
let button_color_purpleMedium = $(`#button-color_purpleMedium-textEditor`);
let button_color_purpleRebeca = $(`#button-color_purpleRebeca-textEditor`);

let button_fontSize_1 = $(`#button-fontSize_1-textEditor`);
let button_fontSize_2 = $(`#button-fontSize_2-textEditor`);
let button_fontSize_3 = $(`#button-fontSize_3-textEditor`);
let button_fontSize_4 = $(`#button-fontSize_4-textEditor`);
let button_fontSize_5 = $(`#button-fontSize_5-textEditor`);
let button_fontSize_6 = $(`#button-fontSize_6-textEditor`);
let button_fontSize_7 = $(`#button-fontSize_7-textEditor`);

let button_fontFamily_arial = $(`#button-fontFamily_arial-textEditor`);
let button_fontFamily_bookAntiqua = $(`#button-fontFamily_bookAntiqua-textEditor`);
let button_fontFamily_courierNew = $(`#button-fontFamily_courierNew-textEditor`);
let button_fontFamily_georgia = $(`#button-fontFamily_georgia-textEditor`);
let button_fontFamily_tahoma = $(`#button-fontFamily_tahoma-textEditor`);
let button_fontFamily_timesNewRoman = $(`#button-fontFamily_timesNewRoman-textEditor`);
let button_fontFamily_trebuchetMS = $(`#button-fontFamily_trebuchetMS-textEditor`);
let button_fontFamily_verdana = $(`#button-fontFamily_verdana-textEditor`);

let modal_insertLink = $(`#modal-insertLink-textEditor`);
let button_proceedInsertLink = $(`#button-proceedInsertLink-textEditor`);
let input_insertLink = $(`#input-insertLink-textEditor`);

let modal_insertImage = $(`#modal-insertImage-textEditor`);
let button_proceedInsertImage = $(`#button-proceedInsertImage-textEditor`);
let input_insertImage = $(`#input-insertImage-textEditor`);

let button_undo = $(`#button-undo-textEditor`);
let button_redo = $(`#button-redo-textEditor`);
let button_removeFormatting = $(`#button-removeFormatting-textEditor`);

let button_alignLeft = $(`#button-alignLeft-textEditor`);
let button_alignCenter = $(`#button-alignCenter-textEditor`);
let button_alignRight = $(`#button-alignRight-textEditor`);


button_alignLeft.on(`click`, function () {
    document.execCommand(`justifyLeft`)
});

button_alignCenter.on(`click`, function () {
    document.execCommand(`justifyCenter`)
});

button_alignRight.on(`click`, function () {
    document.execCommand(`justifyRight`)
});


button_proceedInsertLink.on(`click`, function () {
    let link = input_insertLink.val();
    document.execCommand(`createLink`, false, link);
    modal_insertLink.modal(`hide`);
});

button_proceedInsertImage.on(`click`, function () {
    let url = input_insertImage.val();
    document.execCommand(`insertImage`, false, url);

    let image = $(`img[src="` + url + `"`);
    image.css(`height`, `100%`);
    image.css(`width`, `100%`);
    image.css(`object-fit`, `contain`);

    modal_insertImage.modal(`hide`);
});

button_undo.on(`click`, function () {
    document.execCommand(`undo`);
});

button_redo.on(`click`, function () {
    document.execCommand(`redo`);
});

button_removeFormatting.on(`click`, function () {
    document.execCommand(`removeFormat`);
});


//Proceed styles -----------------------------------------------

button_formatBold.on(`click`, formatTextBold);
button_formatItalic.on(`click`, formatTextItalic);
button_formatUnderline.on(`click`, formatTextUnderline);

function formatTextBold() {
    document.execCommand(`bold`);
}

function formatTextItalic() {
    document.execCommand(`italic`);
}

function formatTextUnderline() {
    document.execCommand(`underline`);
}

//Proceed colors ------------------------------------------------

bindColorListener(button_color_white);
bindColorListener(button_color_black);
bindColorListener(button_color_black);
bindColorListener(button_color_gray);
bindColorListener(button_color_red);
bindColorListener(button_color_redDark);
bindColorListener(button_color_redIndian);
bindColorListener(button_color_green);
bindColorListener(button_color_greenDark);
bindColorListener(button_color_greenYellow);
bindColorListener(button_color_blue);
bindColorListener(button_color_blueDark);
bindColorListener(button_color_blueDeepSky);
bindColorListener(button_color_brown);
bindColorListener(button_color_brownSaddle);
bindColorListener(button_color_brownSandy);
bindColorListener(button_color_pink);
bindColorListener(button_color_pinkDeep);
bindColorListener(button_color_pinkHot);
bindColorListener(button_color_yellow);
bindColorListener(button_color_purple);
bindColorListener(button_color_purpleMedium);
bindColorListener(button_color_purpleRebeca);

function bindColorListener(element) {
    element.on(`click`, {colorHex: getHexColor(element)}, formatColor);

    function getHexColor(element) {
        return rgb2hex($(element.children()[0]).css(`color`));

        function rgb2hex(rgb) {
            rgb = rgb.match(/^rgba?[\s+]?\([\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?/i);
            return (rgb && rgb.length === 4) ? "#" +
                ("0" + parseInt(rgb[1], 10).toString(16)).slice(-2) +
                ("0" + parseInt(rgb[2], 10).toString(16)).slice(-2) +
                ("0" + parseInt(rgb[3], 10).toString(16)).slice(-2) : '';
        }
    }

    function formatColor(event) {
        document.execCommand(`foreColor`, false, event.data.colorHex);
    }
}

//Proceed fontSize -----------------------------------------------

bindFontSizeListener(button_fontSize_1);
bindFontSizeListener(button_fontSize_2);
bindFontSizeListener(button_fontSize_3);
bindFontSizeListener(button_fontSize_4);
bindFontSizeListener(button_fontSize_5);
bindFontSizeListener(button_fontSize_6);
bindFontSizeListener(button_fontSize_7);

function bindFontSizeListener(element) {
    element.on(`click`, function () {
        document.execCommand(`fontSize`, false, Number(element.attr(`data-content`)));
    });
}

//Proceed fontFamily ----------------------------------------------

bindFontFamilyListener(button_fontFamily_arial);
bindFontFamilyListener(button_fontFamily_bookAntiqua);
bindFontFamilyListener(button_fontFamily_courierNew);
bindFontFamilyListener(button_fontFamily_georgia);
bindFontFamilyListener(button_fontFamily_tahoma);
bindFontFamilyListener(button_fontFamily_timesNewRoman);
bindFontFamilyListener(button_fontFamily_trebuchetMS);
bindFontFamilyListener(button_fontFamily_verdana);

function bindFontFamilyListener(element) {
    element.on(`click`, function () {
        document.execCommand(`fontName`, false, element.attr(`data-content`));
    })
}