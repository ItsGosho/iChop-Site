import React, {Component} from 'react';

class TextEditor extends Component {

    constructor(props) {
        super(props);

        this.preventDefault = this.preventDefault.bind(this);
    }


    preventDefault(event) {
        event.preventDefault();
    }

    render() {

        return (
            <div className="col-md-8">
                <a href="#" id="button-formatBold-textEditor" title="Bold" onClick="event.preventDefault()"><i
                    className="material-icons">format_bold</i></a>
                <a href="#" id="button-formatItalic-textEditor" title="Italic" onClick={}><i
                    className="material-icons">format_italic</i></a>
                <a href="#" id="button-formatUnderline-textEditor" title="Underline" onClick="event.preventDefault()"><i
                    className="material-icons">format_underlined</i></a>

                <span>
                                    <a href="#" id="button-color-textEditor" data-toggle="dropdown"
                                       aria-haspopup="true" aria-expanded="false" title="Color"
                                       onClick="event.preventDefault()"><i className="material-icons">invert_colors</i></a>

                                    <div id="div-textColorChoose-textEditor" className="col-md-12 dropdown-menu"
                                         aria-labelledby="button-color-textEditor">
                                        <a href="#" id="button-color_white-textEditor" title="White"
                                           onClick="event.preventDefault()"><i
                                            className="material-icons">format_paint</i></a>
                                        <a href="#" id="button-color_black-textEditor" title="Black"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: black">format_paint</i></a>
                                        <a href="#" id="button-color_grey-textEditor" title="Grey"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: gray">format_paint</i></a>
                                        <a href="#" id="button-color_red-textEditor" title="Red"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: red">format_paint</i></a>
                                        <a href="#" id="button-color_redDark-textEditor" title="Dark Red"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: darkred">format_paint</i></a>
                                        <a href="#" id="button-color_redIndian-textEditor" title="Indian Red"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: indianred">format_paint</i></a>
                                        <a href="#" id="button-color_green-textEditor" title="Green"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: green">format_paint</i></a>
                                        <a href="#" id="button-color_greenDark-textEditor" title="Dark Green"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: darkgreen">format_paint</i></a>
                                        <a href="#" id="button-color_greenYellow-textEditor" title="Yellow Green"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: greenyellow">format_paint</i></a>
                                        <a href="#" id="button-color_blue-textEditor" title="Blue"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: blue">format_paint</i></a>
                                        <a href="#" id="button-color_blueDark-textEditor" title="Dark Blue"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: darkblue">format_paint</i></a>
                                        <a href="#" id="button-color_blueDeepSky-textEditor" title="Deep Sky Blue"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: deepskyblue">format_paint</i></a>
                                        <a href="#" id="button-color_brown-textEditor" title="Brown"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: brown">format_paint</i></a>
                                        <a href="#" id="button-color_brownSaddle-textEditor" title="Saddle Brown"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: saddlebrown">format_paint</i></a>
                                        <a href="#" id="button-color_brownSandy-textEditor" title="Sandy Brown"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: sandybrown">format_paint</i></a>
                                        <a href="#" id="button-color_pink-textEditor" title="Pink"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: pink">format_paint</i></a>
                                        <a href="#" id="button-color_pinkDeep-textEditor" title="Deep Pink"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: deeppink">format_paint</i></a>
                                        <a href="#" id="button-color_pinkHot-textEditor" title="Hot Pink"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: hotpink">format_paint</i></a>
                                        <a href="#" id="button-color_yellow-textEditor" title="Yellow"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: yellow">format_paint</i></a>
                                        <a href="#" id="button-color_purple-textEditor" title="Purple"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: purple">format_paint</i></a>
                                        <a href="#" id="button-color_purpleMedium-textEditor" title="Medium Purple"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: mediumpurple">format_paint</i></a>
                                        <a href="#" id="button-color_purpleRebeca-textEditor" title="Rebeca Purple"
                                           onClick="event.preventDefault()"><i className="material-icons"
                                                                               style="color: rebeccapurple">format_paint</i></a>
                                    </div>
                                </span>

                <span>
                                        <a href="#" id="button-fontSize-textEditor" className="dropdown-toggle"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                           title="Font Size" onClick="event.preventDefault()"><i
                                            className="material-icons">format_size</i></a>
                                        <ul className="dropdown-menu scrollable-menu" role="menu"
                                            style="min-height: 100px;max-height: 100px;overflow: auto">
                                        <a id="button-fontSize_1-textEditor" data-content="1" href="#"
                                           onClick="event.preventDefault()"><li style="font-size: 9px;">1</li></a>
                                        <a id="button-fontSize_2-textEditor" data-content="2" href="#"
                                           onClick="event.preventDefault()"><li style="font-size: 10px;">2</li></a>
                                        <a id="button-fontSize_3-textEditor" data-content="3" href="#"
                                           onClick="event.preventDefault()"><li style="font-size: 12px;">3</li></a>
                                        <a id="button-fontSize_4-textEditor" data-content="4" href="#"
                                           onClick="event.preventDefault()"><li style="font-size: 15px;">4</li></a>
                                        <a id="button-fontSize_5-textEditor" data-content="5" href="#"
                                           onClick="event.preventDefault()"><li style="font-size: 18px;">5</li></a>
                                        <a id="button-fontSize_6-textEditor" data-content="6" href="#"
                                           onClick="event.preventDefault()"><li style="font-size: 22px;">6</li></a>
                                        <a id="button-fontSize_7-textEditor" data-content="7" href="#"
                                           onClick="event.preventDefault()"><li style="font-size: 26px;">7</li></a>
                                    </ul>
                                </span>

                <span>
                                        <a href="#" id="button-fontFamily-textEditor" className="dropdown-toggle"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                           title="Font Family" onClick="event.preventDefault()"><i
                                            className="material-icons">font_download</i></a>
                                        <ul className="dropdown-menu scrollable-menu" role="menu"
                                            style="min-height: 100px;max-height: 100px;overflow: auto">
                                        <a id="button-fontFamily_arial-textEditor" data-content="Arial" href="#"
                                           onClick="event.preventDefault()"><li style="font-family: 'Arial'">Arial</li></a>
                                        <a id="button-fontFamily_bookAntiqua-textEditor" data-content="Book Antiqua"
                                           href="#" onClick="event.preventDefault()"><li
                                            style="font-family: 'Book Antiqua'">Book Antiqua</li></a>
                                        <a id="button-fontFamily_courierNew-textEditor" data-content="Courier New"
                                           href="#" onClick="event.preventDefault()"><li
                                            style="font-family: 'Courier New'">Courier New</li></a>
                                        <a id="button-fontFamily_georgia-textEditor" data-content="Georgia" href="#"
                                           onClick="event.preventDefault()"><li
                                            style="font-family: 'Georgia'">Georgia</li></a>
                                        <a id="button-fontFamily_tahoma-textEditor" data-content="Tahoma" href="#"
                                           onClick="event.preventDefault()"><li
                                            style="font-family: 'Tahoma'">Tahoma</li></a>
                                        <a id="button-fontFamily_timesNewRoman-textEditor"
                                           data-content="Times New Roman" href="#" onClick="event.preventDefault()"><li
                                            style="font-family: 'Times New Roman'">Times New Roman</li></a>
                                        <a id="button-fontFamily_trebuchetMS-textEditor" data-content="Trebuchet MS"
                                           href="#" onClick="event.preventDefault()"><li
                                            style="font-family: 'Trebuchet MS'">Trebuchet MS</li></a>
                                        <a id="button-fontFamily_verdana-textEditor" data-content="Verdana" href="#"
                                           onClick="event.preventDefault()"><li
                                            style="font-family: 'Verdana'">Verdana</li></a>
                                    </ul>
                                </span>

                <a href="#" id="button-insertLink-textEditor" data-toggle="modal"
                   data-target="#modal-insertLink-textEditor"
                   title="Insert Link" onClick="event.preventDefault()"><i
                    className="material-icons">insert_link</i></a>
                <div className="modal fade" id="modal-insertLink-textEditor" tabIndex="-1" role="dialog"
                     aria-labelledby="modalLabelInsertLink" aria-hidden="true">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title" id="modalLabelInsertLink">Insert Link</h5>
                                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div className="modal-body">
                                <div className="input-group mb-3">
                                    <input id="input-insertLink-textEditor" type="text" className="form-control"
                                           aria-describedby="basic-addon1"
                                           placeholder="Example: https://youtube.com..."/>
                                </div>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                <button id="button-proceedInsertLink-textEditor" type="button"
                                        className="btn btn-primary">Insert
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <a href="#" id="button-insertImage-textEditor" data-toggle="modal"
                   data-target="#modal-insertImage-textEditor"
                   title="Insert Image" onClick="event.preventDefault()"><i className="material-icons">photo</i></a>
                <div className="modal fade" id="modal-insertImage-textEditor" tabIndex="-1" role="dialog"
                     aria-labelledby="modalLabelInsertImage" aria-hidden="true">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title" id="modalLabelInsertImage">Image URL</h5>
                                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div className="modal-body">
                                <div className="input-group mb-3">
                                    <input id="input-insertImage-textEditor" type="text" className="form-control"
                                           aria-describedby="basic-addon1" placeholder=""/>
                                </div>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                <button id="button-proceedInsertImage-textEditor" type="button"
                                        className="btn btn-primary">Insert
                                </button>
                            </div>
                        </div>
                    </div>
                </div>


                <a href="#" id="button-undo-textEditor" title="Undo" onClick="event.preventDefault()"><i
                    className="material-icons">undo</i></a>
                <a href="#" id="button-redo-textEditor" title="Redo" onClick="event.preventDefault()"><i
                    className="material-icons">redo</i></a>
                <a href="#" id="button-removeFormatting-textEditor" title="Remove Formatting"
                   onClick="event.preventDefault()"><i
                    className="material-icons">format_clear</i></a>

                <span>
                                        <a href="#" id="button-textAlign-textEditor" className="dropdown-toggle"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                           title="Text Align" onClick="event.preventDefault()"><i
                                            className="material-icons">format_align_justify</i></a>
                                    <ul className="dropdown-menu scrollable-menu" role="menu">
                                        <a id="button-alignLeft-textEditor" href="#" onClick="event.preventDefault()"><li><i
                                            className="material-icons">format_align_left</i>Align Left</li></a>
                                        <a id="button-alignCenter-textEditor" href="#" onClick="event.preventDefault()"><li><i
                                            className="material-icons">format_align_center</i>Align Center</li></a>
                                        <a id="button-alignRight-textEditor" href="#" onClick="event.preventDefault()"><li><i
                                            className="material-icons">format_align_right</i>Align Right</li></a>
                                    </ul>
                                </span>


            </div>
        );
    }
}

export default TextEditor;