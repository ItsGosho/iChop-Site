import React, {Component} from 'react';

const CommandExecutorHoc = (Comp) => {
    return class extends Component {

        constructor(props) {
            super(props);


            this.preventDefault = this.preventDefault.bind(this);
            this.execCommand = this.execCommand.bind(this);
        }

        preventDefault(event) {
            event.preventDefault();
        }

        execCommand(command) {
            return (event) => {
                this.preventDefault(event);
                document.execCommand(command);
            }
        }

        render() {
            return (
                <Comp
                    preventDefault={this.preventDefault}
                    execCommand={this.execCommand}
                    {...this.props}/>
            );
        }
    }
};

export default CommandExecutorHoc;