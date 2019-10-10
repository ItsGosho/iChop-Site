import React, {Component} from 'react';
import TextEditorCommands from "./text.editor.commands.constants";
import CommandExecutorHoc from "./command.executor.hoc";
import MaterialIcons from "../../constants/material.icons.types.constants";

class TextEditorColorPicker extends Component {

    constructor(props) {
        super(props);

        this.autoColorProceed = this.autoColorProceed.bind(this);
        this.getHexColor = this.getHexColor.bind(this);
    }


    autoColorProceed(event) {
        let element = event.target;
        let colorHex = this.getHexColor(element);

        document.execCommand(TextEditorCommands.SET_COLOR, false, colorHex);
    }

    getHexColor(element) {
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
        let {preventDefault} = this.props;

        return (
            <span>

               <a href="#" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false" title="Color"
                  onClick={preventDefault}><i className="material-icons">{MaterialIcons.INVERT_COLORS}</i>
               </a>

               <div className="col-md-12 dropdown-menu"
                    aria-labelledby="button-color-textEditor">

                  <a href="#" title="White" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'white'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Black" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'black'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Grey" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'gray'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Red" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'red'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Dark Red" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'darkred'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Indian Red" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'indianred'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Green" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'green'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Dark Green" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'darkgreen'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Yellow Green"
                     onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'greenyellow'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Blue" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'blue'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Dark Blue" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'darkblue'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Deep Sky Blue"
                     onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'deepskyblue'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Brown" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'brown'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Saddle Brown"
                     onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'saddlebrown'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Sandy Brown" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'sandybrown'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Pink" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'pink'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Deep Pink" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'deeppink'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Hot Pink" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'hotpink'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Yellow" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'yellow'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Purple" onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'purple'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Medium Purple"
                     onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'mediumpurple'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

                  <a href="#" title="Rebeca Purple"
                     onClick={this.autoColorProceed}>
                     <i className="material-icons" style={{'color': 'rebeccapurple'}}>{MaterialIcons.FORMAT_PAINT}</i>
                  </a>

               </div>
            </span>
        );
    }
}

export default CommandExecutorHoc(TextEditorColorPicker);