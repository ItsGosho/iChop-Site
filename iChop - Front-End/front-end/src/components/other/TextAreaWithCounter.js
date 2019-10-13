import React, {Component} from 'react';

class TextAreaWithCounter extends Component {

    constructor(props) {
        super(props);

        this.state = {
            maxStatusCharacters: 0,
            leftStatusCharacters: 0
        };

        this.onStatusChange = this.onStatusChange.bind(this);

        this.textAreaRef = React.createRef();
    }


    onStatusChange(value) {
        let element = this.textAreaRef.current;

        if (value.length > 16) {
            element.style['border-color'] = 'red';

            this.setState((prev) => {
                return {statusMessage: value.slice(0, prev.maxStatusCharacters)}
            });

            return;
        } else {
            element.style['border-color'] = '';
        }

        this.setState((prev) => {
            return {leftStatusCharacters: prev.maxStatusCharacters - value.length}
        }, () => {
            this.props.onCounted(this.state.leftStatusCharacters);
        });
    }

    componentDidMount() {
        this.setState({maxStatusCharacters: this.props.maxCharacters});
        this.setState({leftStatusCharacters: this.props.maxCharacters});
        this.setState({[this.props.name]: this.props.value});
        this.setState((prev) => {
            return {leftStatusCharacters: prev.leftStatusCharacters - this.props.value.length}
        });
    }

    render() {
        let onChange = (event) => {
            this.setState({[event.target.name]: event.target.value}, () => {
                this.onStatusChange(this.state[name])
            });
        };

        let {name, className, onCounted} = this.props;

        return (
            <textarea name={name}
                      className={className}
                      ref={this.textAreaRef}
                      value={this.state[name]}
                      onChange={onChange}/>
        );
    }

}

export default TextAreaWithCounter;