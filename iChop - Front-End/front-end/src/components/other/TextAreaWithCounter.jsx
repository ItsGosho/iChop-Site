import React, {Component} from 'react';
import PropTypes from 'prop-types';


class TextAreaWithCounter extends Component {

    constructor(props) {
        super(props);

        this.state = {
            maxCharacters: 0,
            leftCharacters: 0,
            initialValue: null
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
        });
    }

    componentWillReceiveProps(nextProps, nextContext) {
        let {maxCharacters, initialValue} = nextProps;

        initialValue = initialValue !== undefined ? initialValue : '';

        this.setState({maxCharacters: maxCharacters});
        this.setState({leftCharacters: maxCharacters});

        if(this.state.initialValue !== initialValue){
            this.setState({initialValue});
            this.setState({[this.props.name]: initialValue});
            this.setState((prev) => {
                return {leftCharacters: prev.leftCharacters - initialValue.length}
            });
        }
    }

    render() {
        let onChange = (event) => {
            this.setState({[event.target.name]: event.target.value}, () => {
                this.onStatusChange(this.state[name]);
                this.props.onValueChange(this.state[name]);
            });
        };

        let {name, className} = this.props;
        let value = this.state[[this.props.name]];

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

TextAreaWithCounter.propTypes = {
    name: PropTypes.string,
    initialValue: PropTypes.string,
    className: PropTypes.string,
    onValueChange: PropTypes.func,
    maxCharacters: PropTypes.number,
};