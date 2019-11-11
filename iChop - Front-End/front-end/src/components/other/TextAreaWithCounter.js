import React, {Component} from 'react';

class TextAreaWithCounter extends Component {

    constructor(props) {
        super(props);

        this.state = {
            maxCharacters: 0,
            leftCharacters: 0
        };

        this.onStatusChange = this.onStatusChange.bind(this);

        this.textAreaRef = React.createRef();
    }


    onStatusChange(value) {
        let element = this.textAreaRef.current;

        if (value.length > this.state.maxCharacters) {
            element.style['border-color'] = 'red';

            this.setState((prev) => {
                return {[this.props.name]: value.slice(0, prev.maxCharacters)}
            });

            return;
        } else {
            element.style['border-color'] = '';
        }

        this.setState((prev) => {
            return {leftCharacters: prev.maxCharacters - value.length}
        }, () => {
            this.props.onCounted(this.state.leftCharacters);
        });
    }

    componentDidMount() {
        this.setState({maxCharacters: this.props.maxCharacters});
        this.setState({leftCharacters: this.props.maxCharacters});

        this.setState({[this.props.name]: this.props.value});
        this.setState((prev) => {
            return {leftCharacters: prev.leftCharacters - this.props.value.length}
        });
    }

    render() {

        let onChange = (event) => {
            this.setState({[event.target.name]: event.target.value}, () => {
                this.onStatusChange(this.state[name]);
                this.props.onValueChange(this.state[name]);
            });
        };

        let {name, className,value} = this.props;

        return (
            <textarea name={name}
                      className={className}
                      ref={this.textAreaRef}
                      value={value}
                      onChange={onChange}/>
        );
    }

}

export default TextAreaWithCounter;