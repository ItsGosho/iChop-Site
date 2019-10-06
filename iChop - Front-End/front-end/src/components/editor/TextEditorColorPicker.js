import React, {Component} from 'react';
import TextEditorCommands from "./text.editor.commands.constants";
import CommandExecutorHoc from "./command.executor.hoc";

class TextEditorColorPicker extends Component {

   constructor(props) {
      super(props);

      this.autoColorProceeder = this.autoColorProceeder.bind(this);
   }


   autoColorProceeder(event) {
        let element = event.target;
        let colorHex = getHexColor(element);

        console.log(colorHex);

        function getHexColor(element) {
           console.log(element);

            //return rgb2hex($(element.children()[0]).css(`color`));

            function rgb2hex(rgb) {
                rgb = rgb.match(/^rgba?[\s+]?\([\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?/i);
                return (rgb && rgb.length === 4) ? "#" +
                    ("0" + parseInt(rgb[1], 10).toString(16)).slice(-2) +
                    ("0" + parseInt(rgb[2], 10).toString(16)).slice(-2) +
                    ("0" + parseInt(rgb[3], 10).toString(16)).slice(-2) : '';
            }
        }

        function formatColor(event) {
            document.execCommand(TextEditorCommands.SET_COLOR, false, event.data.colorHex);
        }
    }

    render() {
       let {preventDefault,execCommand} = this.props;

        return (
            <span>

               <a href="#" id="button-color-textEditor" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false" title="Color"
                  onClick={preventDefault}><i className="material-icons">invert_colors</i></a>

               <div id="div-textColorChoose-textEditor" className="col-md-12 dropdown-menu"
                    aria-labelledby="button-color-textEditor">

                  <a href="#" id="button-color_white-textEditor" title="White" onClick={this.autoColorProceeder}>
                     <i className="material-icons" style={{'color': 'white'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_black-textEditor" title="Black" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'black'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_grey-textEditor" title="Grey" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'gray'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_red-textEditor" title="Red" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'red'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_redDark-textEditor" title="Dark Red" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'darkred'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_redIndian-textEditor" title="Indian Red" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'indianred'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_green-textEditor" title="Green" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'green'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_greenDark-textEditor" title="Dark Green" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'darkgreen'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_greenYellow-textEditor" title="Yellow Green"
                     onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'greenyellow'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_blue-textEditor" title="Blue" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'blue'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_blueDark-textEditor" title="Dark Blue" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'darkblue'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_blueDeepSky-textEditor" title="Deep Sky Blue"
                     onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'deepskyblue'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_brown-textEditor" title="Brown" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'brown'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_brownSaddle-textEditor" title="Saddle Brown"
                     onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'saddlebrown'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_brownSandy-textEditor" title="Sandy Brown" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'sandybrown'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_pink-textEditor" title="Pink" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'pink'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_pinkDeep-textEditor" title="Deep Pink" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'deeppink'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_pinkHot-textEditor" title="Hot Pink" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'hotpink'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_yellow-textEditor" title="Yellow" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'yellow'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_purple-textEditor" title="Purple" onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'purple'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_purpleMedium-textEditor" title="Medium Purple"
                     onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'mediumpurple'}}>format_paint</i>
                  </a>

                  <a href="#" id="button-color_purpleRebeca-textEditor" title="Rebeca Purple"
                     onClick={this.preventDefault}>
                     <i className="material-icons" style={{'color': 'rebeccapurple'}}>format_paint</i>
                  </a>

               </div>
            </span>
        );
    }
}

export default CommandExecutorHoc(TextEditorColorPicker);