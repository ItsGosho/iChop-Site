import React, {Component} from 'react';
import TextEditorCommands from "../text.editor.commands.constants";
import MaterialIcons from "../../../constants/other/material.icons.types.constants";

class TextEditorColorPicker extends Component {

    static autoColorProceed(event) {
        let element = event.target;
        let colorHex = TextEditorColorPicker.getHexColor(element);

        document.execCommand(TextEditorCommands.SET_COLOR, false, colorHex);
    }

    static getHexColor(element) {
        let colorAsRGB = window.getComputedStyle(element).color;
        return rgb2hex(colorAsRGB);

        function rgb2hex(rgb) {
            rgb = rgb.match(/^rgba?[\s+]?\([\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?/i);
            return (rgb && rgb.length === 4) ? "#" +
                ("0" + parseInt(rgb[1], 10).toString(16)).slice(-2) +
                ("0" + parseInt(rgb[2], 10).toString(16)).slice(-2) +
                ("0" + parseInt(rgb[3], 10).toString(16)).slice(-2) : '';
        }
    }

    render() {

        return (
            <span>

               <a href="#" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false" title="Color"
                  onClick={(event) => {
                      event.preventDefault();
                  }}><i className="material-icons">{MaterialIcons.INVERT_COLORS}</i>
               </a>

               <div className="col-md-12 dropdown-menu"
                    aria-labelledby="button-color-textEditor">

                   <Color title="White" color="white"/>
                   <Color title="Black" color="black"/>
                   <Color title="Grey" color="grey"/>
                   <Color title="Red" color="red"/>

                   <Color title="Dark Red" color="darkred"/>
                   <Color title="Indian Red" color="indianred"/>
                   <Color title="Green" color="green"/>
                   <Color title="Dark Green" color="darkgreen"/>

                   <Color title="Yellow Green" color="greenyellow"/>
                   <Color title="Blue" color="blue"/>
                   <Color title="Dark Blue" color="darkblue"/>
                   <Color title="Deep Sky Blue" color="deepskyblue"/>

                   <Color title="Brown" color="brown"/>
                   <Color title="Saddle Brown" color="saddlebrown"/>
                   <Color title="Sandy Brown" color="sandybrown"/>
                   <Color title="Pink" color="pink"/>

                   <Color title="Deep Pink" color="deeppink"/>
                   <Color title="Hot Pink" color="hotpink"/>
                   <Color title="Yellow" color="yellow"/>
                   <Color title="Purple" color="purple"/>
                   <Color title="Medium Purple" color="mediumpurple"/>
                   <Color title="Rebeca Purple" color="rebeccapurple"/>

               </div>
            </span>
        );
    }
}

const Color = (props) => {
    let {title, color} = props;

    return (
        <a href='#' title={title} onClick={TextEditorColorPicker.autoColorProceed}>
            <i className="material-icons" style={{'color': color}}>{MaterialIcons.FORMAT_PAINT}</i>
        </a>
    );
};


export default TextEditorColorPicker;